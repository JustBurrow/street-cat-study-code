package kr.lul.street.cat.study.api.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.lul.street.cat.study.api.ApiTestConfiguration;
import kr.lul.street.cat.study.api.data.Cat;
import kr.lul.street.cat.study.api.rest.controller.request.AddRequest;
import kr.lul.street.cat.study.api.service.CatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = JoinRestController.class)
@ContextConfiguration(classes = ApiTestConfiguration.class)
public class JoinRestControllerTest {
  private static final Logger log = getLogger(JoinRestControllerTest.class);

  @Autowired
  private MockMvc mock;

  @MockBean
  private CatService catService;

  private ObjectMapper mapper;
  private LocalDateTime before;

  @Before
  public void setUp() throws Exception {
    this.mapper = new ObjectMapper();
    this.before = LocalDateTime.now().withNano(0);
  }

  @Test
  public void test_add() throws Exception {
    UUID chipId = randomUUID();
    UUID deviceId = randomUUID();
    AddRequest request = new AddRequest(chipId, deviceId);
    log.info("GIVEN - request={}", request);

    Cat cat = new Cat(chipId, deviceId, "test cat");
    setField(cat, "id", 1234L);
    setField(cat, "createdAt", this.before);
    setField(cat, "updatedAt", this.before);
    when(this.catService.add(chipId, deviceId))
        .thenReturn(cat);

    this.mock.perform(post("/join/add")
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(this.mapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceId").value(deviceId.toString()))
        .andExpect(jsonPath("$.chipId").value(chipId.toString()))
        .andExpect(jsonPath("$.createdAt").isNotEmpty())
        .andDo(print());
  }
}

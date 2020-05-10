package kr.lul.street.cat.study.api.rest.controller;

import kr.lul.street.cat.study.api.data.Cat;
import kr.lul.street.cat.study.api.rest.controller.request.AddRequest;
import kr.lul.street.cat.study.api.rest.controller.response.AddResponse;
import kr.lul.street.cat.study.api.service.CatService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@RestController
@RequestMapping
public class JoinRestController {
  private static final Logger log = getLogger(JoinRestController.class);

  @Autowired
  private CatService service;

  @PostMapping("/join/add")
  @ResponseBody
  public AddResponse add(@RequestBody AddRequest request) {
    if (log.isTraceEnabled())
      log.trace("#add request={}", request);

    Cat cat = this.service.add(request.getChipId(), request.getDeviceId());
    AddResponse response = new AddResponse(cat.getDeviceId(), cat.getChipId(), cat.getCreatedAt());

    if (log.isTraceEnabled())
      log.trace("#add return : {}", response);
    return response;
  }
}

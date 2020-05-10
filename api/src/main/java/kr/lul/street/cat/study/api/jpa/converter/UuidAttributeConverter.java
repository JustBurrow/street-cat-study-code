package kr.lul.street.cat.study.api.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@Converter(autoApply = true)
public class UuidAttributeConverter implements AttributeConverter<UUID, String> {
  @Override
  public String convertToDatabaseColumn(UUID attribute) {
    return null == attribute
               ? null
               : attribute.toString();
  }

  @Override
  public UUID convertToEntityAttribute(String dbData) {
    return null == dbData
               ? null
               : UUID.fromString(dbData);
  }
}

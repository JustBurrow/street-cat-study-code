package kr.lul.street.cat.study.batch.data;

/**
 * @author justburrow
 * @since 2020/05/12
 */
public enum DeviceType {
  FOOD,
  WATER,
  WEIGHT,
  TIME;

  public static DeviceType valueOf(int index) {
    return values()[index];
  }
}

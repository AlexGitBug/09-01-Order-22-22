package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDto {

    Integer id;
    String userInfoId;
    String roomId;
    String beginTimeOfTheOrder;
    String endTimeOfTheOrder;
    String condition;
    String message;

}
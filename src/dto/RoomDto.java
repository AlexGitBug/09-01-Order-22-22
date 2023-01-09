package dto;

import entity.CategoryRoom;
import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import entity.QuantityBed;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomDto {

    Integer id;
    NumberRoomEnum number;
    QuantityBed quantityBedId;
    CategoryRoom categoryRoomId;
    FloorEnum floor;
    Integer dayPrice;
    RoomStatusEnum status;
    String image;
}
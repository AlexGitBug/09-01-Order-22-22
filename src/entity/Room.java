package entity;

import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import lombok.Builder;

import lombok.Data;

@Data
@Builder
public class Room {
    private Integer id;
    private NumberRoomEnum number;
    private QuantityBed quantityBedId;
    private CategoryRoom categoryRoomId;
    private FloorEnum floor;
    private Integer dayPrice;
    private RoomStatusEnum status;
    private String image;

}
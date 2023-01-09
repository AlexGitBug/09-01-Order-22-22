package mapper;

import dao.CategoryRoomDao;
import dao.QuantityBedDao;
import dto.CreateDto.CreateRoomDto;

import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import entity.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRoomMapper implements Mapper<CreateRoomDto, Room> {

    private static final CreateRoomMapper INSTANCE = new CreateRoomMapper();
    private static final String IMAGE_FOLDER = "users/";
    private final QuantityBedDao quantityBedDao = QuantityBedDao.getInstance();
    private final CategoryRoomDao categoryRoomDao = CategoryRoomDao.getInstance();
    @Override
    public Room mapFrom(CreateRoomDto object) {
        return Room.builder()
                .number(NumberRoomEnum.valueOf(object.getNumber()))
                .quantityBedId(quantityBedDao.findById(Integer.parseInt(object.getQuantityBedId())).get())
                .categoryRoomId(categoryRoomDao.findById(Integer.parseInt(object.getCategoryRoomId())).get())
                .floor(FloorEnum.valueOf(object.getFloor()))
                .dayPrice(Integer.parseInt(object.getDayPrice()))
                .status(RoomStatusEnum.valueOf(object.getStatus()))
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .build();
    }


    public static CreateRoomMapper getInstance() {
        return INSTANCE;
    }
}
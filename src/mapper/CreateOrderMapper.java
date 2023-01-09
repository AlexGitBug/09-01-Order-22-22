package mapper;


import dao.RoomDao;
import dao.UserInfoDao;
import dto.OrderDto;
import entity.Enum.ConditionEnum;
import entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderMapper implements Mapper<OrderDto, Order> {

    private static final CreateOrderMapper INSTANCE = new CreateOrderMapper();
    private final UserInfoDao userInfoDao = UserInfoDao.getInstance();
    private final RoomDao roomDao = RoomDao.getInstance();

    @Override
    public Order mapFrom(OrderDto object) {
        return Order.builder()
                .userInfoId(userInfoDao.findById(Integer.parseInt(object.getUserInfoId())).get())
                .room(roomDao.findById(Integer.parseInt(object.getRoomId())).get())
                .beginTimeOfTheOrder(LocalDateFormatter.format(object.getBeginTimeOfTheOrder()))
                .endTimeOfTheOrder(LocalDateFormatter.format(object.getEndTimeOfTheOrder()))
                .condition(ConditionEnum.valueOf(object.getCondition()))
                .message(object.getMessage())
                .build();
    }

    public static CreateOrderMapper getInstance() {
        return INSTANCE;
    }
}
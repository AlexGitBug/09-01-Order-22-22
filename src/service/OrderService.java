package service;

import dao.OrderDao;

import dao.RoomDao;
import dao.UserInfoDao;
import dto.OrderDto;
import entity.Enum.ConditionEnum;
import entity.Enum.RoomStatusEnum;
import entity.Order;
import entity.Room;
import lombok.NoArgsConstructor;
import mapper.CreateOrderMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderService {

    private static final OrderService INSTANCE = new OrderService();
    private final OrderDao orderDao = OrderDao.getInstance();
    private final UserInfoDao userInfoDao = UserInfoDao.getInstance();
    RoomDao roomDao = RoomDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
//    private final CreateOrderValidator createOrderValidator = CreateOrderValidator.getInstance();

    public Integer create(OrderDto orderDto) {
        var orderEntity = createOrderMapper.mapFrom(orderDto);
        orderDao.save(orderEntity);
        return orderEntity.getId();
    }

    public List<OrderDto> findOrdersByUserId(Integer userId) {
        var orderDtos = findAll();
        String userIdString = userId.toString();
        return orderDtos.stream()
                .filter(orderDto -> orderDto.getUserInfoId().equals(userIdString))
                .collect(Collectors.toList());
    }


    public List<OrderDto> findAll() {
        return orderDao.findAll().stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .userInfoId(order.getUserInfoId().toString())
                        .roomId(order.getRoom().toString())
                        .beginTimeOfTheOrder(order.getEndTimeOfTheOrder().toString())
                        .endTimeOfTheOrder(order.getEndTimeOfTheOrder().toString())
                        .condition(order.getCondition().name())
                        .message(order.getMessage())
                        .build())
                .collect(toList());
    }

    public Order findOrderById(Integer id) {
        return orderDao.findById(id).orElseThrow();

    }

    public void checkAndConfirmOrder(Order order) {
        LocalDate beginTimeOfTheOrder = order.getBeginTimeOfTheOrder();
        LocalDate endTimeOfTheOrder = order.getEndTimeOfTheOrder();

        if (order.getCondition().equals(ConditionEnum.WANT_TO_RESERVE)) {
            String correctPeriodOfTheOrderMessage = "";
            if (isNotCorrectPeriodOfTheOrder(beginTimeOfTheOrder, endTimeOfTheOrder)) {
                correctPeriodOfTheOrderMessage = "Incorrect period of the order. Please check dates";
            }

            String final_message = "Everything is OK! ";
            ConditionEnum conditionEnum = ConditionEnum.APPROVED;

            if (isNotCorrectPeriodOfTheOrder(beginTimeOfTheOrder, endTimeOfTheOrder)) {
                conditionEnum = ConditionEnum.REJECTED;
                final_message = String.format("%s", correctPeriodOfTheOrderMessage);

            } else {
                order.getRoom().setStatus(RoomStatusEnum.Booked);
                roomDao.update(order.getRoom());
            }
            order.setCondition(conditionEnum);
            order.setMessage(final_message);
            orderDao.update(order);

            }
        }



    private boolean isNotCorrectPeriodOfTheOrder(LocalDate beginTimeOfTheOrder, LocalDate endTimeOfTheOrder) {
        return (beginTimeOfTheOrder.isAfter(endTimeOfTheOrder));
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}


//    public boolean delete(int id) {
//        return orderDao.delete(id);
//
//    }
//
//    public static OrderService getInstance() {
//        return INSTANCE;
//    }
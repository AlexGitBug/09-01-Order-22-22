package service;

import dao.OrderDao;
import dto.OrderDto;

import java.util.Optional;

public class InfoOrderService {
    private static final InfoOrderService INSTANCE = new InfoOrderService();
    private static OrderDao orderDao = OrderDao.getInstance();

    public Optional<OrderDto> findOrderOrderDto(Integer id) {
        return orderDao.findById(id)
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .userInfoId(order.getId().toString())
                        .roomId(order.getRoom().toString())
                        .beginTimeOfTheOrder(order.getBeginTimeOfTheOrder().toString())
                        .endTimeOfTheOrder(order.getEndTimeOfTheOrder().toString())
                        .condition(order.getCondition().name())
                        .message(order.getMessage())
                        .build());

    }



    public static InfoOrderService getInstance() {
        return INSTANCE;
    }
}
package service;

import dao.UserInfoDao;
import dto.OrderDto;
import entity.UserInfo;

import java.util.List;
import java.util.Optional;

public class AdminReportService {
    private static final AdminReportService INSTANCE = new AdminReportService();
    private final OrderService orderService = OrderService.getInstance();
    private final UserInfoDao userInfoDao = UserInfoDao.getInstance();

    public String createUserReport() {
        List<OrderDto> orderDtoList = orderService.findAll();
        StringBuilder userReport = new StringBuilder();
        for (OrderDto orderDto : orderDtoList) {
            Integer id = Integer.valueOf(orderDto.getUserInfoId());
            userInfoDao.findUserId(id).orElseThrow();
            String userOrders = String.format("Order Id: %s, User id: %s, Room id: %s, Begin time: %s, End time: %s, Condition: %s, Message: %s" + System.lineSeparator(),
                    orderDto.getId(), orderDto.getUserInfoId(), orderDto.getRoomId(), orderDto.getBeginTimeOfTheOrder(), orderDto.getEndTimeOfTheOrder(), orderDto.getCondition(), orderDto.getMessage());
            userReport.append(userOrders);

        }
        return userReport.toString();
    }


    public static AdminReportService getInstance() {
        return INSTANCE;
    }
}
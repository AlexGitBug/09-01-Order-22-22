package service;

import dao.UserInfoDao;
import dto.OrderDto;

import java.util.List;

public class UserReportService {
    private static final UserReportService INSTANCE = new UserReportService();
    private final OrderService orderService = OrderService.getInstance();
    private final UserInfoDao userInfoDao = UserInfoDao.getInstance();

    public String createUserReport(Integer userId) {
        List<OrderDto> orderDtoList = orderService.findAll();
        StringBuilder userReport = new StringBuilder();
        for (OrderDto orderDto : orderDtoList) {
            if (userInfoDao.findById(userId).get().getId().toString().equals(orderDto.getUserInfoId())) {
                String userOrders = String.format("Order Id: %s, User id: %s, Room id: %s, Begin time: %s, End time: %s, Condition: %s, Message: %s" + System.lineSeparator(),
                        orderDto.getId(), orderDto.getUserInfoId(), orderDto.getRoomId(), orderDto.getBeginTimeOfTheOrder(), orderDto.getEndTimeOfTheOrder(), orderDto.getCondition(), orderDto.getMessage());
                userReport.append(userOrders);
            }
        }
        return userReport.toString();
    }


    public static UserReportService getInstance() {
        return INSTANCE;
    }
}
package servlet;

import dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.InfoOrderService;
import service.OrderService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.USER_ORDER_LIST;

@WebServlet(USER_ORDER_LIST)
public class UserOrdersServlet extends HttpServlet {
    private final OrderService orderService = OrderService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var user = (UserInfoDto) req.getSession().getAttribute("user");
        var orderByUserId = orderService.findOrdersByUserId(user.getId());

        req.setAttribute("userorderlist", orderByUserId);

        req.getRequestDispatcher(JspHelper.getPath("userorderlist"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("mainpage"))
                .forward(req, resp);
    }
}
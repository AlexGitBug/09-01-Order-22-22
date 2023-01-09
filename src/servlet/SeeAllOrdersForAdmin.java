package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.SEE_ALL_ORDERS_FOR_ADMIN;

@WebServlet(SEE_ALL_ORDERS_FOR_ADMIN)
public class SeeAllOrdersForAdmin extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("order", orderService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("seeallordersforadmin"))
                .forward(req, resp);
    }
}
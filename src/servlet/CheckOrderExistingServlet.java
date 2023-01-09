package servlet;

import dto.OrderDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.InfoOrderService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.CHECK_ORDER_EXISTING;

@WebServlet(CHECK_ORDER_EXISTING)
public class CheckOrderExistingServlet extends HttpServlet {

    private final InfoOrderService infoOrderService = InfoOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId = Integer.valueOf(req.getParameter("orderId"));

        infoOrderService.findOrderOrderDto(orderId).ifPresentOrElse(orderDto -> {
            forwardOrderDto(req, resp, orderDto);
        }, () -> {
            sendError(resp);
        });
    }


    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        try {
            resp.sendError(400, "Booking does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void forwardOrderDto(HttpServletRequest req, HttpServletResponse resp, OrderDto orderDto) {
        req.setAttribute("order", orderDto);
        try {
            req.getRequestDispatcher(JspHelper.getPath("checkorder"))
                    .forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
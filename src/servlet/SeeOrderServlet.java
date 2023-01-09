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

import static util.UrlPath.SEE_INFO_ABOUT_ORDER;

@WebServlet(SEE_INFO_ABOUT_ORDER)
public class SeeOrderServlet extends HttpServlet {
    private final InfoOrderService infoOrderService = InfoOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        var id = Integer.valueOf(req.getParameter("id"));
        infoOrderService.findOrderOrderDto(id).ifPresentOrElse(orderDto -> {
            forwardOrderDto(req, resp, orderDto);
        }, () -> {
            sendError(resp);
        });
        req.getRequestDispatcher(JspHelper.getPath("userorderlist"))
                .forward(req, resp);
    }


    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        try {
            resp.sendError(400, "Order does not exist :(");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void forwardOrderDto(HttpServletRequest req, HttpServletResponse resp, OrderDto orderDto) {
        req.setAttribute("order", orderDto);
        try {
            req.getRequestDispatcher(JspHelper.getPath("seeinfoaboutorder"))
                    .forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
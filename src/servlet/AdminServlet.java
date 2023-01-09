package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.*;


@WebServlet(ADMIN_PAGE)
public class AdminServlet extends HttpServlet {

    private final RoomService roomService = RoomService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roomlist", roomService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("adminpage"))
                .forward(req, resp);
        req.getRequestDispatcher(JspHelper.getPath("roomfindall"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var name = req.getParameter("name");
        resp.sendRedirect(ADD_ROOM);
        resp.sendRedirect(MAIN_PAGE);

    }

}
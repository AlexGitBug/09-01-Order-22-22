package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.FIND_ALL_ROOMS;

@WebServlet(FIND_ALL_ROOMS)
public class FindAllRoomsServlet extends HttpServlet {
    private final RoomService roomService = RoomService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roomlist", roomService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("findallrooms"))
                .forward(req, resp);
    }
}
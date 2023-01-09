package servlet.RoomServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/room")
public class RoomServletFindById extends HttpServlet {

    private final RoomService roomService = RoomService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("roomlist", roomService.findById(id));
        req.getRequestDispatcher(JspHelper.getPath("roombyid"))
                .forward(req, resp);

    }

}
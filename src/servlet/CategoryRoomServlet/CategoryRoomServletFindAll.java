
package servlet.CategoryRoomServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryRoomService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/categoryroomfindall")
public class CategoryRoomServletFindAll extends HttpServlet {
    private final CategoryRoomService categoryRoomService = CategoryRoomService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("categoryroomlist", categoryRoomService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("categoryroomfindall"))
                .forward(req, resp);
    }
}
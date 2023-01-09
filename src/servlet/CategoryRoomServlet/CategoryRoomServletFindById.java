package servlet.CategoryRoomServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryRoomService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/categoryroom")
public class CategoryRoomServletFindById extends HttpServlet {

    private final CategoryRoomService categoryRoomService = CategoryRoomService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = Integer.parseInt(req.getParameter("id"));
        //       var kind = req.getParameter("kind");
        req.setAttribute("categoryroom", categoryRoomService.findById(id));

        //       req.setAttribute("categoryroomfindall", categoryRoomService.findAll());

        //    req.setAttribute("categoryroomlist", categoryRoomService.findAll());


        req.getRequestDispatcher(JspHelper.getPath("categoryroombyid"))
                .forward(req, resp);
        //    req.getRequestDispatcher(JspHelper.getPath("categoryroomfindall"))
        //          .forward(req, resp);


//
//        categoryRoomService.delete(id);
//        categoryRoomService.findAll();
        //    categoryRoomService.findById(id);
        //       categoryRoomService.saveCategoryRoom(kind);
//        categoryRoomService.updateRoom(id, kind);

    }

}
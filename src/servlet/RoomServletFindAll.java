package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryRoomService;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

//    @WebServlet("/roomfindall")
//    public class RoomServletFindAll extends HttpServlet {
//
//        private final RoomService roomService = RoomService.getInstance();
//
//        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//            req.setAttribute("roomlist", roomService.findAll());
//
//
//            req.getRequestDispatcher(JspHelper.getPath("roomfindall"))
//                    .forward(req, resp);
//        }
//    }

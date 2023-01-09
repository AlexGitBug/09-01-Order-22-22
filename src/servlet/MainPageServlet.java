package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;
import java.io.IOException;

import static util.UrlPath.*;


@WebServlet(MAIN_PAGE)
public class MainPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(ORDER))
                .forward(req, resp);
        req.getRequestDispatcher(JspHelper.getPath(USER_ORDER_LIST))
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(MAIN_PAGE))
                .forward(req, resp);
        req.getRequestDispatcher(JspHelper.getPath(ADMIN_PAGE))
                .forward(req, resp);
    }

}
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;
import util.UrlPath;

import java.io.IOException;

import static util.UrlPath.ORDER_DONE;

@WebServlet(ORDER_DONE)
public class OrderDoneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(ORDER_DONE))
                .forward(req, resp);
    }
}
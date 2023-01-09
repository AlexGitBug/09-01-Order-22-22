package servlet;

import dao.RoleDao;
import dto.UserInfoDto;
import entity.Enum.RoleEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserInfoService;
import util.JspHelper;

import java.io.IOException;
import java.sql.SQLException;

import static util.UrlPath.*;


@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserInfoService userInfoService = UserInfoService.getInstance();
    private final RoleDao roleDao = RoleDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userInfoService.login(req.getParameter("email"), req.getParameter("password"))
                    .ifPresentOrElse(
                            user -> onLoginSuccess(user, req, resp),
                            () -> onLoginFail(req, resp)
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(LOGIN + "?error&email=" + req.getParameter("login"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onLoginSuccess(UserInfoDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        if (userInfoService.findUserId(user.getId()).isPresent()) {
//            if (user.getRole().getId() == 2){
            if (user.getRole().getRank().equals(RoleEnum.ADMIN.name())) {
                try {
                    resp.sendRedirect(ADMIN_PAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    resp.sendRedirect(ORDER);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            try {
                resp.sendRedirect(LOGIN);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserInfoService;

@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {

    private final UserInfoService userInfoService = UserInfoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        int id = Integer.parseInt(req.getParameter("id"));

        var firstName = req.getParameter("firstName");
        var lastName = req.getParameter("lastName");
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        var telephone = req.getParameter("telephone");
        var birthday = req.getParameter("birthday");

//        userInfoService.delete(id);
//        userInfoService.findById(id);
//        userInfoService.findAll();
//        userInfoService.save(firstName, lastName, email, password, telephone, birthday);
//        userInfoService.update(id, firstName, lastName, email, password, telephone);
    }
}


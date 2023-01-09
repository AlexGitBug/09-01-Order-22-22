package servlet;

import dto.CreateDto.CreateUserDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoleService;
import service.UserInfoService;
import util.JspHelper;

import java.io.IOException;
import static util.UrlPath.REGISTRATION;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserInfoService userInfoService = UserInfoService.getInstance();
    private final RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var createUserDto = CreateUserDto.builder()
                .firstName(req.getParameter("name"))
                .lastName(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .telephone(req.getParameter("telephoneNumber"))
                .birthday(req.getParameter("birthday"))
                .build();

        try {
            String telephone = createUserDto.getTelephone();
            String email = createUserDto.getEmail();
            String regexTelephone = "\\+375 \\(\\d{2}\\) \\d{3}-\\d{2}-\\d{2}";
            String regexEmail = "[a-zA-Z0-9]\\w*@\\w{3,}\\.(org|com|ru)";
            if (telephone.matches(regexTelephone) && email.matches(regexEmail)) {
                userInfoService.create(createUserDto);
                resp.sendRedirect("/login");
            } else {
                resp.sendRedirect(REGISTRATION + "?error&telephoneNumber&email=" + req.getParameter("registration"));
            }
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
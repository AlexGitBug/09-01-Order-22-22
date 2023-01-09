
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoleService;

import java.io.IOException;
@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    private final RoleService roleService = RoleService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String rank = req.getParameter("rank");

        roleService.delete(id);
        roleService.findAll();
        roleService.findById(id);
        roleService.save(rank);
        roleService.update(id, rank);

    }
}
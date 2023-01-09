package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.QuantityBedService;

import java.io.IOException;

@WebServlet("/quantitybed")
public class QuantityBedServlet extends HttpServlet {
    private final QuantityBedService quantityBedService = QuantityBedService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int capacity = Integer.parseInt(req.getParameter("capacity"));

        quantityBedService.delete(id);
        quantityBedService.findAll();
        quantityBedService.findById(id);
        quantityBedService.saveQuantityBed(capacity);
        quantityBedService.updateQuantityBed(id, capacity);

    }

}

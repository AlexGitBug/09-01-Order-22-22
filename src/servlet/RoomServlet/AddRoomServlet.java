package servlet.RoomServlet;

import dto.CreateDto.CreateRoomDto;
import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryRoomService;
import service.QuantityBedService;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(ADD_ROOM)
public class AddRoomServlet extends HttpServlet {

    private final RoomService roomService = RoomService.getInstance();
    private final QuantityBedService quantityBedService = QuantityBedService.getInstance();
    private final CategoryRoomService categoryRoomService = CategoryRoomService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("numbers", NumberRoomEnum.values());
        req.setAttribute("floors", FloorEnum.values());
        req.setAttribute("quantitybed", quantityBedService.findAll());
        req.setAttribute("categoryroom", categoryRoomService.findAll());
        req.setAttribute("statuses", RoomStatusEnum.values());
        req.setAttribute("roomlist", roomService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("addroom"))
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateRoomDto createRoomDto = CreateRoomDto.builder()
                .number(req.getParameter("number_room"))
                .quantityBedId(req.getParameter("quantity_bed_id"))
                .categoryRoomId(req.getParameter("category_room_id"))
                .floor(req.getParameter("floor"))
                .dayPrice(req.getParameter("day_price"))
                .status(req.getParameter("status"))
                .image(req.getPart("image"))
                .build();

        roomService.create(createRoomDto);
        resp.sendRedirect(ADMIN_PAGE);
    }
}
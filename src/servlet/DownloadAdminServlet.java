package servlet;

import dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AdminReportService;
import service.UserReportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static util.UrlPath.*;

@WebServlet(DOWNLOAD_ADMIN_REPORT)
public class DownloadAdminServlet extends HttpServlet {
    private final AdminReportService adminReportService = AdminReportService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Disposition", "attachment; filename=\"admin_report.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var userReport = adminReportService.createUserReport();
        Files.writeString(ADMIN_REPORT_FULL_PATH, userReport);

        try (PrintWriter writer = resp.getWriter()) {
            Files.createDirectories(ADMIN_REPORT_FULL_PATH.getParent());
            writer.write(Files.readString(ADMIN_REPORT_FULL_PATH));
        }

    }
}
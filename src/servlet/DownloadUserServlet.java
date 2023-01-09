
package servlet;

import dto.UserInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserReportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static util.UrlPath.DOWNLOAD_USER_REPORT;
import static util.UrlPath.USER_REPORT_FULL_PATH;

@WebServlet(DOWNLOAD_USER_REPORT)
public class DownloadUserServlet extends HttpServlet {
    private final UserReportService userReportService = UserReportService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserInfoDto) req.getSession().getAttribute("user");
        resp.setHeader("Content-Disposition", "attachment; filename=\"user_report.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var userReport = userReportService.createUserReport(user.getId());
        Files.writeString(USER_REPORT_FULL_PATH, userReport);

        try (PrintWriter writer = resp.getWriter()) {
            Files.createDirectories(USER_REPORT_FULL_PATH.getParent());
            writer.write(Files.readString(USER_REPORT_FULL_PATH));
        }

    }
}
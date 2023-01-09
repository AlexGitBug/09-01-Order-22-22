package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.ImageService;
import util.UrlPath;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(UrlPath.IMAGES + "/*")
public class ImageServlet extends HttpServlet {

    private final ImageService imageService= ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace("/users", "");

        imageService.get(imagePath)
                .ifPresentOrElse(image -> {
                    resp.setContentType("application/octet-stream");
                    try {
                        writeImage(image, resp);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }, () -> resp.setStatus(404));
    }
    //    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) throws IOException {
        try(image; var outputStream = resp.getOutputStream()){
            int currentByte;
            while((currentByte = image.read()) != -1){
                outputStream.write(currentByte);
            }
        }
    }
}
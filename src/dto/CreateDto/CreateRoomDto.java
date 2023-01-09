package dto.CreateDto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateRoomDto {
    //    Integer id;
    String number;
    String quantityBedId;
    String categoryRoomId;
    String floor;
    String dayPrice;
    String status;
    Part image;
}
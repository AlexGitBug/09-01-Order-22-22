package dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryRoomDto {

    Integer id;
    String kind;


}
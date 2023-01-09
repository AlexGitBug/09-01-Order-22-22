package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;


@Data
@Builder
public class QuantityBedDto {

    Integer id;
    Integer capacity;

}
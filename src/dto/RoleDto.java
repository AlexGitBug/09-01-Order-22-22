package dto;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class RoleDto {

    Integer id;
    String rank;

}
package entity;

import entity.Enum.RoleEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder

public class Role {

    Integer id;
    String rank;

}
package dto;

import entity.Enum.RoleEnum;
import entity.Role;
import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class UserInfoDto {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    Role role;
    String telephone;
    LocalDate birthday;
}
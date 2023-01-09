
package dto.CreateDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {


    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    String role;
    String telephone;
    String birthday;
//    String image;
}
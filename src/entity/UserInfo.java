package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Integer id;
    private String firstName;
    private String lastName;
    private String  email;
    private String password;
    private Role role;
    private String telephone;
    private LocalDate birthday;

}
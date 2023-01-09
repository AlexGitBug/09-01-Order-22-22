package mapper;

import dao.RoleDao;
import dto.CreateDto.CreateUserDto;
import dto.UserInfoDto;
import entity.Enum.RoleEnum;
import entity.UserInfo;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, UserInfo> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private final RoleDao roleDao = RoleDao.getInstance();
    @Override
    public UserInfo mapFrom(CreateUserDto object) {
        return UserInfo.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(roleDao.findById(Integer.parseInt(object.getRole())).get())
                .telephone(object.getTelephone())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
package mapper;

import dao.RoleDao;
import dao.UserInfoDao;
import dto.CreateDto.CreateUserDto;
import dto.UserInfoDto;
import entity.Role;
import entity.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoMapper implements Mapper<UserInfo, UserInfoDto> {

    private static final UserInfoMapper INSTANCE = new UserInfoMapper();
    @Override
    public UserInfoDto mapFrom(UserInfo object) {
        return UserInfoDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .role(object.getRole())
                .telephone(object.getTelephone())
                .birthday(object.getBirthday())
                .build();

    }

    public static UserInfoMapper getInstance() {
        return INSTANCE;
    }
}
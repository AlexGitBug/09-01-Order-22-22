package entity.Enum;

import entity.Role;

import java.util.Arrays;
import java.util.Optional;

public enum RoleEnum {
    USER,
    ADMIN;
// просмотреть запись  от 20.12.2022 и реализовать константу VALUES
    public static Optional<RoleEnum> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}

package service;

import dao.RoleDao;
import dto.RoleDto;
import entity.Role;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoleService {
    private static final RoleService INSTANCE = new RoleService();

    private final RoleDao roleDao = RoleDao.getInstance();

    public List<RoleDto> findAll() {
        return roleDao.findAll().stream()
                .map(role -> RoleDto.builder()
                        .id(role.getId())
                        .rank(role.getRank())
                        .build())
                .collect(toList());

    }

    public List<RoleDto> findById(int id) {
        return roleDao.findById(id).stream()
                .map(role -> RoleDto.builder()
                        .id(role.getId())
                        .rank(role.getRank())
                        .build())
                .collect(toList());

    }

    public void update(int id, String rank) {
        var roleUpdate = roleDao.findById(id);
        roleUpdate.ifPresent(role -> {
            role.setRank(rank);
            roleDao.update(role);
        });
    }
    public void save(String rank) {
        var role = Role.builder()
                .rank(rank)
                .build();

        roleDao.save(role);
    }
    public boolean delete(int id) {
        return roleDao.delete(id);

    }

    public static RoleService getInstance() {
        return INSTANCE;
    }
}
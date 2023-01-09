package service;

import dao.CategoryRoomDao;
import dto.CategoryRoomDto;
import entity.CategoryRoom;

import java.util.List;

import static java.util.stream.Collectors.toList;
public class CategoryRoomService {
    private static final CategoryRoomService INSTANCE = new CategoryRoomService();

    private final CategoryRoomDao categoryRoomDao = CategoryRoomDao.getInstance();

    public List<CategoryRoomDto> findAll() {

        return categoryRoomDao.findAll().stream()
                .map(categoryRoom -> CategoryRoomDto.builder()
                        .id(categoryRoom.getId())
                        .kind(categoryRoom.getKind())
                        .build())
                .collect(toList());

    }

    public List<CategoryRoomDto> findById(int id) {
        return categoryRoomDao.findById(id).stream()
                .map(categoryRoom -> CategoryRoomDto.builder()
                        .id(categoryRoom.getId())
                        .kind(categoryRoom.getKind())
                        .build())
                .collect(toList());

    }

    public void updateRoom(int id, String kind) {
        var category = categoryRoomDao.findById(id);
        category.ifPresent(categoryRoom -> {
            categoryRoom.setKind(kind);
            categoryRoomDao.update(categoryRoom);
        });
    }
    public void saveCategoryRoom(String kind) {
        var categoryRoom = CategoryRoom.builder()
                .kind(kind)
                .build();

        categoryRoomDao.save(categoryRoom);
    }
    public boolean delete(int id) {
        return categoryRoomDao.delete(id);

    }

    public static CategoryRoomService getInstance() {
        return INSTANCE;
    }
}
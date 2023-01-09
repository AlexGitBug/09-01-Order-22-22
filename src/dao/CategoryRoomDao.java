package dao;

import entity.CategoryRoom;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRoomDao {

    private static final CategoryRoomDao INSTANCE = new CategoryRoomDao();
    private static final String DELETE_SQL = """
            DELETE FROM category_room
            WHERE id  = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO category_room (kind)
            VALUES (?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE category_room
            SET kind = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
                    kind
                FROM category_room
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private CategoryRoomDao() {
    }

    public List<CategoryRoom> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<CategoryRoom> categoryRooms = new ArrayList<>();
            while (resultSet.next()) {
                categoryRooms.add(buildCategoryRoom(resultSet));
            }
            return categoryRooms;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
    public Optional<CategoryRoom> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            CategoryRoom categoryRoom = null;
            if (resultSet.next()) {
                categoryRoom = buildCategoryRoom(resultSet);
            }
            return Optional.ofNullable(categoryRoom);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static CategoryRoom buildCategoryRoom(ResultSet resultSet) throws SQLException {
        return CategoryRoom.builder()
                .id(resultSet.getObject("id", Integer.class))
                .kind(resultSet.getObject("kind", String.class))
                .build();
    }

    public void update(CategoryRoom categoryRoom) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, categoryRoom.getKind());
            preparedStatement.setInt(2, categoryRoom.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public CategoryRoom save(CategoryRoom categoryRoom) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, categoryRoom.getKind());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                categoryRoom.setId(generatedKeys.getInt("id"));
            }
            return categoryRoom;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static CategoryRoomDao getInstance() {
        return INSTANCE;
    }

}
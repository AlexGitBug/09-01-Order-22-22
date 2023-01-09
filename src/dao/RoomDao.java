package dao;

import entity.CategoryRoom;
import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import entity.QuantityBed;
import entity.Room;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {

    private static final RoomDao INSTANCE = new RoomDao();
    private static final QuantityBedDao quantityBedDao = QuantityBedDao.getInstance();
    private static final CategoryRoomDao categoryRoomDao = CategoryRoomDao.getInstance();

    private static final String FIND_ALL_SQL = """
            SELECT room.id, number_room, quantity_bed_id, category_room_id, floor, day_price, status, image
                FROM room
            """;

    private static final String FIND_ALL_FREE_SQL = """
            SELECT room.id, number_room, quantity_bed_id, category_room_id, floor, day_price, status, image
                FROM room
                WHERE status LIKE 'Free'
            """;
    private static final String FIND_ALL_FREE_BY_ID_SQL = """
            SELECT room.id, number_room, quantity_bed_id, category_room_id, floor, day_price, status, image
                FROM room
                WHERE status LIKE 'Free' AND id = ?
            """;

//    qb.id,
//    qb.capacity,
//    cr.id,
//    cr.kind,
//    join quantity_bed qb on qb.id = room.quantity_bed_id
//    join category_room cr on cr.id = room.category_room_id

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE room.id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO room (number_room, quantity_bed_id, category_room_id, floor, day_price, status, image)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
                DELETE FROM room
                WHERE id  = ?
                """;

    private static final String UPDATE_SQL = """
            UPDATE room
            SET number_room = ?,
                quantity_bed_id = ?,
                category_room_id = ?,
                floor = ?,
                day_price = ?,
                status = ?,
                image = ?
            WHERE id = ?
            """;

    public List<Room> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(buildRoom(resultSet));
            }
            return rooms;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Room> findAllFreeRoom() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_FREE_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(buildRoom(resultSet));
            }
            return rooms;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Room> findAllFreeRoomById(Integer roomId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_FREE_BY_ID_SQL)) {
            preparedStatement.setInt(1, roomId);

            var resultSet = preparedStatement.executeQuery();
            Room room = null;
            if (resultSet.next()) {
                room = buildRoom(resultSet);
            }
            return Optional.ofNullable(room);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Room buildRoom(ResultSet resultSet) throws SQLException {
        return Room.builder()
                .id(resultSet.getObject("id", Integer.class))
                .number(NumberRoomEnum.valueOf(resultSet.getObject("number_room", String.class)))
                .quantityBedId(quantityBedDao.findById(Integer.parseInt(resultSet.getObject("quantity_bed_id", Integer.class).toString())).get())
                .categoryRoomId(categoryRoomDao.findById(Integer.parseInt(resultSet.getObject("category_room_id", Integer.class).toString())).get())
                .floor(FloorEnum.valueOf(resultSet.getObject("floor", String.class)))
                .dayPrice(resultSet.getObject("day_price", Integer.class))
                .status(RoomStatusEnum.valueOf(resultSet.getObject("status", String.class)))
                .image(resultSet.getObject("image", String.class))
                .build();
    }

    public Optional<Room> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Room room = null;
            if (resultSet.next()) {
                room = buildRoom(resultSet);
            }
            return Optional.ofNullable(room);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    public Room save(Room room) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, room.getNumber().name());
            preparedStatement.setObject(2, room.getQuantityBedId().getId());
            preparedStatement.setObject(3, room.getCategoryRoomId().getId());
            preparedStatement.setObject(4, room.getFloor().name());
            preparedStatement.setObject(5, room.getDayPrice());
            preparedStatement.setObject(6, room.getStatus().name());
            preparedStatement.setObject(7, room.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                room.setId(generatedKeys.getInt("id"));
            }
            return room;
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
    public void update(Room room) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, room.getNumber().name());
            preparedStatement.setObject(2, room.getQuantityBedId().getId());
            preparedStatement.setObject(3, room.getCategoryRoomId().getId());
            preparedStatement.setObject(4, room.getFloor().name());
            preparedStatement.setObject(5, room.getDayPrice());
            preparedStatement.setObject(6, room.getStatus().name());
            preparedStatement.setObject(7, room.getImage());
            preparedStatement.setObject(8, room.getId());


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static RoomDao getInstance() {
        return INSTANCE;
    }
}
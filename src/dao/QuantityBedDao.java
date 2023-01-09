package dao;

import entity.QuantityBed;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuantityBedDao {
    private static final QuantityBedDao INSTANCE = new QuantityBedDao();
    private static final String DELETE_SQL = """
            DELETE FROM quantity_bed
            WHERE id  = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO quantity_bed (capacity)
            VALUES (?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE quantity_bed
            SET capacity = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
                    capacity
                FROM quantity_bed
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private QuantityBedDao() {
    }

    public List<QuantityBed> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<QuantityBed> quantityBeds = new ArrayList<>();
            while (resultSet.next()) {
                quantityBeds.add(buildQuantityBed(resultSet));
            }
            return quantityBeds;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<QuantityBed> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            QuantityBed quantityBeds = null;
            if (resultSet.next()) {
                quantityBeds = buildQuantityBed(resultSet);
            }
            return Optional.ofNullable(quantityBeds);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static QuantityBed buildQuantityBed(ResultSet resultSet) throws SQLException {
        return QuantityBed.builder()
                .id(resultSet.getInt("id"))
                .capacity(resultSet.getInt("capacity"))
                .build();

    }

    public void update(QuantityBed quantityBed) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, quantityBed.getCapacity());
            preparedStatement.setInt(2, quantityBed.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public QuantityBed save(QuantityBed quantityBed) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, quantityBed.getCapacity());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                quantityBed.setId(generatedKeys.getInt("id"));
            }
            return quantityBed;
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

    public static QuantityBedDao getInstance() {
        return INSTANCE;
    }
}
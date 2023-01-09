package dao;

import entity.Role;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao {
    private static final RoleDao INSTANCE = new RoleDao();
    private static final String DELETE_SQL = """
            DELETE FROM role
            WHERE id  = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO role (rank)
            VALUES (?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE role
            SET rank = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
                   rank
                FROM role
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private RoleDao() {
    }

    public List<Role> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(buildRole(resultSet));
            }
            return roles;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
    public Optional<Role> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Role roles = null;
            if (resultSet.next()) {
                roles = buildRole(resultSet);
            }
            return Optional.ofNullable(roles);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static Role buildRole(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getInt("id"))
                .rank(resultSet.getString("rank"))
                .build();

    }

    public void update(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, role.getRank());
            preparedStatement.setInt(2, role.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Role save(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRank());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getInt("id"));
            }
            return role;
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

    public static RoleDao getInstance() {
        return INSTANCE;
    }

}
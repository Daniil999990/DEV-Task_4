import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public void populateClient(String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateProject(Date startDate, Integer clientId, Date finishDate, String projectName) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project (start_date, client_id, finish_date, name) VALUES (?,?,?,?)");
            preparedStatement.setDate(1, startDate);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setDate(3, finishDate);
            preparedStatement.setString(4, projectName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateWorker(String name, String level, int salary, Date birthday) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO worker (name, level, salary, birthday) VALUES (?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, level);
            preparedStatement.setInt(3, salary);
            preparedStatement.setDate(4, birthday);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateProjectWorker(int projectId, int workerId) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project_worker (project_id, worker_id) VALUES (?,?)");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, workerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return Database.getInstance().getConnection();
    }
}

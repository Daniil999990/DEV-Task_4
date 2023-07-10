import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DatabaseQueryService {
    private final List<MaxProjectsClient> mpc = new LinkedList<>();
    private final List<MaxSalaryWorker> msw = new LinkedList<>();
    private final List<YoungestEldestWorker> yew = new LinkedList<>();
    private final List<LongestProject> lp = new LinkedList<>();
    private final LinkedList<ProjectPrice> pj = new LinkedList<>();

    public List<MaxProjectsClient> findMaxProjectsClients() {
        List<MaxProjectsClient> maxProjectsClients = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/find_max_projects_client.sql")));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int projectCount = resultSet.getInt("PROJECT_COUNT");
                MaxProjectsClient maxProjectsClient = new MaxProjectsClient(name, projectCount);
                maxProjectsClients.add(maxProjectsClient);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return maxProjectsClients;
    }

    public List<LongestProject> findLongestProjects() {
        List<LongestProject> longestProjects = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/find_longest_project.sql")));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int monthCount = resultSet.getInt("MONTH_COUNT");
                LongestProject longestProject = new LongestProject(name, monthCount);
                longestProjects.add(longestProject);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return longestProjects;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/find_youngest_eldest_workers.sql")));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("NAME");
                Date birthday = resultSet.getDate("BIRTHDAY");
                YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker(type, name, birthday);
                youngestEldestWorkers.add(youngestEldestWorker);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkers;
    }

    public List<ProjectPrice> printProjectPrices() {
        List<ProjectPrice> projectPrices = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/print_project_prices.sql")));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int price = resultSet.getInt("PRICE");
                ProjectPrice projectPrice = new ProjectPrice(name, price);
                projectPrices.add(projectPrice);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return projectPrices;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException, IOException {
        List<MaxSalaryWorker> msw = new ArrayList<>();
        String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/find_max_salary_worker.sql")));
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int salary = resultSet.getInt("SALARY");
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(name, salary);
                msw.add(maxSalaryWorker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msw;
    }
}

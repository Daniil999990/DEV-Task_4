import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) {
        List<MaxProjectCountClient> maxProjectCountClients = findMaxProjectsClient();
        for (MaxProjectCountClient client : maxProjectCountClients) {
            System.out.println("Client: " + client.getName() + ", Max Project Count: " + client.getProjectCount());
        }
    }

    public static List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String queryFile = "/Users/daniil/IdeaProjects/DEV-Task_4/src/main/resources/sql/find_max_projects_client.sql";

        try {
            String query = readSqlFile(queryFile);
            result = executeQuery(query);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String readSqlFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    private static List<MaxProjectCountClient> executeQuery(String query) throws SQLException {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                MaxProjectCountClient client = new MaxProjectCountClient(name, projectCount);
                result.add(client);
            }
        }

        return result;
    }
}

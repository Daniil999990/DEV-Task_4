import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        String initScriptPath = "/Users/daniil/IdeaProjects/DEV-Task_4/src/main/resources/sql/init_db.sql";

        try {
            String sql = readSqlFile(initScriptPath);
            executeSql(sql);
            System.out.println("Вірно.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static String readSqlFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    private static void executeSql(String sql) throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}

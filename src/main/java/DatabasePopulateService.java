import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String populateScriptPath = "/Users/daniil/IdeaProjects/DEV-Task_4/src/main/resources/sql/populate_db.sql";

        try {
            String sql = readSqlFile(populateScriptPath);
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
            // Заполнение таблицы client
            statement.executeUpdate("INSERT INTO client (NAME) VALUES " +
                    "('Client A')," +
                    "('Client B')," +
                    "('Client C')," +
                    "('Client D')," +
                    "('Client E');");

            // Заполнение таблицы worker
            statement.executeUpdate("INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES " +
                    "('Anton', '1990-01-01', 'Trainee', 800)," +
                    "('Daniil', '1985-05-15', 'Junior', 1200)," +
                    "('Johnson', '1982-09-30', 'Middle', 2500)," +
                    "('Anastasia', '1978-07-12', 'Senior', 5000)," +
                    "('David', '1992-03-22', 'Junior', 1500)," +
                    "('Andrey', '1989-11-18', 'Middle', 3000)," +
                    "('Roman', '1980-06-28', 'Senior', 4500)," +
                    "('Slava', '1995-04-05', 'Trainee', 900)," +
                    "('Max', '1987-08-08', 'Junior', 1400)," +
                    "('Carina', '1991-12-10', 'Middle', 2800);");
        }
    }
}
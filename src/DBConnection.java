import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://ep-fragrant-bush-ahdnnn81-pooler.c-3.us-east-1.aws.neon.tech:5432/neondb?sslmode=require";

    private static final String USER = "neondb_owner";
    private static final String PASS = "npg_rnNLQyceg60D";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

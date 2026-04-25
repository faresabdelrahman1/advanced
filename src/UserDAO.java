import java.sql.*;

public class UserDAO {

    public static boolean checkLogin(String username, String password) {

        String sql = "SELECT * FROM student WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void showAllUsers() {



            String sql = "SELECT * FROM student";

            try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                // طباعة أسماء الأعمدة
                System.out.println("=== COLUMNS ===");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(meta.getColumnName(i));
                }

                System.out.println("\n=== DATA ===");

                // طباعة الداتا
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                    }
                    System.out.println();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


package megamek.server;

import megamek.MegaMek;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.HashMap;

public class DBManager {

    public static void initiateDB() {
        createEloTable();
        addOrUpdateRow("Simon", 2);
        //getAll();
        getEloByUsername("Simon");
    }

    private static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:database/test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            return conn;

        } catch (SQLException e) {
            MegaMek.printToOut(e.getMessage());
        } catch ( Exception e ) {
            MegaMek.printToOut( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }

    private static void createEloTable() {
        Connection c = null;
        Statement stmt = null;

        try {
             c = connect();
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS elo_score ( " +
                    "USERNAME   TEXT    PRIMARY KEY, " +
                    "ELO        REAL    DEFAULT 0)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            MegaMek.printToOut( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                MegaMek.printToOut(ex.getMessage());
            }
        }
    }

    private static void addOrUpdateRow(String username, int elo) {
        Connection c = null;
        Statement stmt = null;

        try {
            c = connect();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT OR REPLACE INTO elo_score (USERNAME, ELO) " +
                    "VALUES ('"+username+"', "+elo+");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static HashMap<String, Integer> getAll() {
        Connection c = null;
        Statement stmt = null;

        try {
            c = connect();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM elo_score ORDER BY USERNAME;";
            ResultSet rs = stmt.executeQuery(sql);

            HashMap<String, Integer> eloMap = new HashMap();
            while ( rs.next() ) {
                String  username = rs.getString("USERNAME");
                int elo = rs.getInt("ELO");
                eloMap.put(username, elo);
            }
            rs.close();
            stmt.close();
            c.close();
            return eloMap;

        } catch ( Exception e ) {
            MegaMek.printToOut( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }

    public static int getEloByUsername(String name) {
        Connection c = null;
        Statement stmt = null;

        try {
            c = connect();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM elo_score WHERE USERNAME="+name+";";
            ResultSet rs = stmt.executeQuery(sql);
            int elo = 0;
            if ( rs.first() ) {
                String  username = rs.getString("USERNAME");
                elo = rs.getInt("ELO");
                MegaMek.printToOut("USERNAME = " + username);
            }
            rs.close();
            stmt.close();
            c.close();

            return elo;

        } catch ( Exception e ) {
            MegaMek.printToOut( e.getClass().getName() + ": " + e.getMessage() );
        }
        return 0;
    }
}

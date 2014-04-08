package ie.logn;

import ie.logn.utils.db.DatabaseUtil;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class LognDatabaseUtil {

    private static boolean useHsql = true;

    private static String DB_DRIVER;
    private static String FILE_DB_URL;
    private static String DB_USER;
    private static String DB_PWD;

    private static final String SQL_CREATE_HSQL_TABLES = "../data/src/main/sql/lognleagues.sql";
    private static final String SQL_DROP_HSQL_TABLES = "../data/src/main/sql/drop_lognleagues.sql";

    private static final String SQL_CREATE_ORA_INGCVA_TABLES = "../data/src/main/sql/lognleagues_oracle.sql";
    private static final String SQL_DROP_ORA_INGCVA_TABLES = "../data/src/main/sql/drop_lognleagues_oracle.sql";
    private static final String SQL_EMPTY_ORA_INGCVA_TABLES = "../data/src/main/sql/test_empty_tables.sql";

    private static final String HSQL_DB_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String HSQL_FILE_URL = "jdbc:hsqldb:file:";
    private static final String HSQL_IN_MEM_URL = "jdbc:hsqldb:mem:";
    private static final String HSQL_DB_USER = "sa";
    private static final String HSQL_DB_PWD = "";

    /* Jump through a hoop to work around all the static modifiers. */
    static private abstract class Internal {
        // abstract public SessionFactory getHsqlHibernateSessionFactory();
        abstract public DataSource getTestDatabase(String name);

        abstract public void createTables(Connection con) throws FileNotFoundException, SQLException;

        abstract public void dropTables(Connection con) throws FileNotFoundException, SQLException;

        abstract public void commitDatabase(Connection con) throws SQLException;

    }

    static private class UsingOracle extends Internal {

        @Override
        public DataSource getTestDatabase(String name) {
            return getOracleTestDatabase(FILE_DB_URL);
        }

        @Override
        public void createTables(Connection con) throws FileNotFoundException, SQLException {
            // DatabaseUtil.runScript(con, SQL_CREATE_ORA_INGCVA_TABLES);
        }

        @Override
        public void dropTables(Connection connection) throws FileNotFoundException, SQLException {
            // DatabaseUtil.runScript(con, SQL_DROP_ORA_INGCVA_TABLES, true);
            // emptyTables(connection);
        }

        @Override
        public void commitDatabase(Connection con) throws SQLException {
        }

    }

    private static class UsingHsql extends Internal {

        @Override
        public DataSource getTestDatabase(String name) {
            return getHsqlDatabase(HSQL_IN_MEM_URL + name);
        }

        @Override
        public void createTables(Connection con) throws FileNotFoundException, SQLException {
            DatabaseUtil.runScript(con, SQL_CREATE_HSQL_TABLES);

        }

        @Override
        public void dropTables(Connection con) throws FileNotFoundException, SQLException {
            DatabaseUtil.runScript(con, SQL_DROP_HSQL_TABLES, true);
        }

        @Override
        public void commitDatabase(Connection con) throws SQLException {
            DatabaseUtil.runUpdate(con, "COMMIT");
        }

    }

    private static Internal internal;
    static {
        if (useHsql) {
            internal = new UsingHsql();
        } else {
            internal = new UsingOracle();
        }
    }

    /**
     * @param name
     *            Name to give the in memory database.
     * @return A DataSource for a file version instance of HSQLDB.
     */
    public static DataSource getTestDatabase(String name) {
        return internal.getTestDatabase(name);
    }

    /** Used to setup testcases. */
    public static void createTables(Connection con) throws FileNotFoundException, SQLException {
        internal.createTables(con);
    }

    public static void dropTables(Connection con) throws FileNotFoundException, SQLException {
        internal.dropTables(con);
    }

    public static DataSource getOracleTestDatabase(String url) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(DB_DRIVER);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PWD);

        Properties props = new Properties();
        props.setProperty("shutdown", "true");
        ds.setConnectionProperties(props);

        return ds;
    }

    private static DriverManagerDataSource getHsqlDatabase(String url) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(HSQL_DB_DRIVER);
        ds.setUsername(HSQL_DB_USER);
        ds.setPassword(HSQL_DB_PWD);

        Properties props = new Properties();
        props.setProperty("shutdown", "true");
        ds.setConnectionProperties(props);

        return ds;
    }


    public static void commitDatabase(Connection con) throws SQLException {
        internal.commitDatabase(con);
    }

    public static void doTearDown(DataSource ds) throws SQLException, FileNotFoundException {
        Connection connection = ds.getConnection();
        dropTables(connection);
        connection.close();
    }

    public static void emptyTables(Connection connection) {
        String delim = ";\\n";
        try {
            DatabaseUtil.runScript(connection, SQL_EMPTY_ORA_INGCVA_TABLES, delim, true, null);
        } catch (FileNotFoundException e) {
            System.err.println("Error while emptying tables: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e1) {
            System.err.println("Error while emptying tables: " + e1.getMessage());
            e1.printStackTrace();
        }
    }
}

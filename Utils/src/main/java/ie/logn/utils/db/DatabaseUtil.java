package ie.logn.utils.db;

import ie.logn.utils.logger.DefaultLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class DatabaseUtil {

    static public void runScript(Connection con, String filename, String delim, boolean contOnError, TextFilter filter)
        throws SQLException, FileNotFoundException {
        Scanner s = null;
        
        try
        {
        	s = new Scanner(new BufferedReader(new FileReader(filename)));
	        
	        s = s.useDelimiter(delim);
	        String sql;
	        while (s.hasNext()) {
	            sql = s.next();
	            DefaultLogger.traceEx("db.script", "PRE: {0}", sql);
	            if (null != filter) {
	                sql = filter.filter(sql);
	            }
	            try {
	                DefaultLogger.debugEx("db.script", "POST: {0}", sql);
	                DatabaseUtil.runUpdate(con, sql);
	            } catch (SQLException e) {
	                System.err.println(e.getMessage() + "\n" + sql);
	                if (contOnError) {
	                    DefaultLogger.warn("Error running SQL statement: " + sql);
	                } else {
	                    throw e;
	                }
	            }
	        }
	        System.err.println("Completed executing " + filename);
        }
        finally
        {
        	if (s != null)
        		s.close();
        }
    }

    /**
     * Run the specified file as a script against a database.
     * 
     * @param con
     *            Connection for the database.
     * @param filename
     *            Path to script file.
     * @param delim
     *            String to use to delimit statements.
     * @param contOnError
     *            Indicates whether to continue after an SQL error.
     * @throws SQLException
     *             the sQL exception
     * @throws FileNotFoundException
     *             the file not found exception
     */
    static public void runScript(Connection con, String filename, String delim, boolean contOnError)
        throws SQLException, FileNotFoundException {
        runScript(con, filename, delim, contOnError, null);
    }

    /**
     * Run the specified file as a script against a database. Uses blank lines
     * as statement delimiters.
     * 
     * @param con
     *            Connection for the database.
     * @param filename
     *            Path to script file.
     * @param delim
     *            String to use to delimit statements.
     * @throws SQLException
     *             the sQL exception
     * @throws FileNotFoundException
     *             the file not found exception
     */
    static public void runScript(Connection con, String filename, String delim) throws SQLException,
        FileNotFoundException {
        runScript(con, filename, delim);
    }

    /**
     * Run the specified file as a script against a database. Uses blank lines
     * as statement delimiters.
     * 
     * @param con
     *            Connection for the database.
     * @param filename
     *            Path to script file.
     * @param contOnError
     *            Indicates whether to continue after an SQL error.
     * @throws SQLException
     *             the sQL exception
     * @throws FileNotFoundException
     *             the file not found exception
     */
    static public void runScript(Connection con, String filename, boolean contOnError) throws SQLException,
        FileNotFoundException {
        runScript(con, filename, "\\n\\s*\\n", contOnError);
    }

    /**
     * Run the specified file as a script against a database. Uses blank lines
     * as statement delimiters.
     * 
     * @param con
     *            Connection for the database.
     * @param filename
     *            Path to script file.
     * @param filter
     *            TextFilter which pre-processes each statement.
     * @throws SQLException
     *             the sQL exception
     * @throws FileNotFoundException
     *             the file not found exception
     */
    static public void runScript(Connection con, String filename, TextFilter filter) throws SQLException,
        FileNotFoundException {
        runScript(con, filename, "\\n\\s*\\n", false, filter);
    }

    /**
     * Run the specified file as a script against a database. Uses blank lines
     * as statement delimiters.
     * 
     * @param con
     *            Connection for the database.
     * @param filename
     *            Path to script file.
     * @throws SQLException
     *             the sQL exception
     * @throws FileNotFoundException
     *             the file not found exception
     */
    static public void runScript(Connection con, String filename) throws SQLException, FileNotFoundException {
        runScript(con, filename, "\\n\\s*\\n", false);
    }

    /**
     * Closes a Database connection checking for null and logging any errors
     * that occur during the close operation.
     * 
     * @param con
     *            Connection to close.
     */
    static public void close(Connection con) {
        if (null != con) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                DefaultLogger.warn("Error closing database connection.", e);
            }
        }
    }

    /**
     * Closes an SQL statement checking for null and logging any errors that
     * occur during the close operation.
     * 
     * @param stmt
     *            The statement to close.
     */
    static public void close(Statement stmt) {
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {
                DefaultLogger.warn("Error closing statement.", e);
            }
        }
    }

    /**
     * Closes a ResultSet checking for null and logging any errors that occur
     * during the close operation. This function will close the statement as
     * well and should not be called where the result set is one of many
     * returned from a statement.
     * 
     * @param rs
     *            The ResultSet to close;
     */
    public static void close(ResultSet rs) {
        if (null != rs) {
            try {
                rs.getStatement().close();
            } catch (SQLException e) {
                DefaultLogger.warn("Error closing statement.", e);
            }
        }
    }

    /**
     * Creates a string describing the result set. The string is made up of an
     * ordered list of column names seperated by a tab.
     * 
     * @param rs
     *            The ResultSet to create the header information for.
     * @return String
     * @throws SQLException
     *             the sQL exception
     */
    public static String getHeader(ResultSet rs) throws SQLException {
        return getHeader(rs, "\t");
    }

    /**
     * Creates a string describing the result set. The string is made up of an
     * ordered list of column names seperated by the separator.
     * 
     * @param rs
     *            The ResultSet to create the header information for.
     * @param separator
     *            String to use as a separator between columns.
     * @return String
     * @throws SQLException
     *             the sQL exception
     */
    public static String getHeader(ResultSet rs, String separator) throws SQLException {
        StringBuffer buf = new StringBuffer(1024);
        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            buf.append(meta.getColumnName(i)).append(separator);
        }

        return buf.toString();
    }

    /**
     * Returns the contents of the current record the rs as as string. The
     * fields of the record are seperated by a tab.
     * 
     * @param rs
     *            The ResultSet containing the record.
     * @param buf
     *            StringBuffer to append the record to.
     * @return String A string containing each column of the recordset.
     * @throws SQLException
     *             the sQL exception
     */
    public static String record2String(ResultSet rs, StringBuffer buf) throws SQLException {
        return record2String(rs, buf, "\t");
    }

    /**
     * Returns the contents of the current record the rs as as string. The
     * fields of the record are seperated by the string passed in separator.
     * 
     * @param rs
     *            The ResultSet containing the record.
     * @param buf
     *            StringBuffer to append the record to.
     * @param separator
     *            The string to use as a field separator.
     * @return String A string containing each column of the recordset.
     * @throws SQLException
     *             the sQL exception
     */
    public static String record2String(ResultSet rs, StringBuffer buf, String separator) throws SQLException {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (i > 1) {
                buf.append(separator);
            }

            buf.append(rs.getString(i));
        }

        return buf.toString();
    }

    /**
     * Returns the contents of the RecordSet as as string. The fields of the
     * record are seperated by the string passed in separator and each record is
     * terminated with a new line.
     * 
     * @param rs
     *            The ResultSet containing the record. This will be closed after
     *            the call returns.
     * @param separator
     *            The string to use as a field separator.
     * @return String A string containing each record of the recordset.
     * @throws SQLException
     *             the sQL exception
     */
    public static String recordSet2String(ResultSet rs, String separator) throws SQLException {
        StringBuffer text = new StringBuffer(1024);
        try {
            while (rs.next()) {
                record2String(rs, text, separator);
                text.append("\n");
            }
            return text.toString();
        } finally {
            rs.close();
        }
    }

    /**
     * Returns the contents of the RecordSet as as string. The fields of the
     * records are seperated by a tab and terminated with a new line.
     * 
     * @param rs
     *            The ResultSet containing the record. This will be closed after
     *            the call returns.
     * @return String A string containing each record of the recordset.
     * @throws SQLException
     *             the sQL exception
     */
    public static String recordSet2String(ResultSet rs) throws SQLException {
        return recordSet2String(rs, "\t");
    }

    /**
     * Retruns a string representation of the data in a table. Beware if called
     * on a large table, please use the whereClause parameter to limit the
     * number of rows displayed.
     * 
     * @param con
     *            Connection to use to query the database.
     * @param tableName
     *            the table whose rows are to be displayed
     * @param where
     *            a SQL where expression to filter the table rows selected
     * @return String Contents of the table.
     * @throws SQLException
     *             the sQL exception
     */
    public static String table2String(Connection con, String tableName, String where) throws SQLException {
        return sql2String(con, "SELECT * FROM " + tableName + " " + where);
    }

    /**
     * Retruns a string representation of the data in a table. Beware if called
     * on a large table.
     * 
     * @param con
     *            Connection to use to query the database.
     * @param tableName
     *            the table whose rows are to be displayed
     * @return String Contents of the table.
     * @throws SQLException
     *             the sQL exception
     */
    public static String table2String(Connection con, String tableName) throws SQLException {
        return table2String(con, tableName, "");
    }

    /**
     * Dumps the result set from runing the specified sql against the database
     * connection.
     * 
     * @param con
     *            Connection to exceute the sql against.
     * @param sql
     *            SQL to execute.
     * @return String representation of the sql.
     * @throws SQLException
     *             the sQL exception
     */
    public static String sql2String(Connection con, String sql) throws SQLException {
        return recordSet2String(DatabaseUtil.runQuery(con, sql));
    }

    /**
     * Dumps the result set from runing the specified sql against the database
     * connection.
     * 
     * @param con
     *            Connection to exceute the sql against.
     * @param sql
     *            SQL to execute.
     * @param seperator
     *            The string to use as a field separator.
     * @return String representation of the sql.
     * @throws SQLException
     *             the sQL exception
     */
    public static String sql2String(Connection con, String sql, String seperator) throws SQLException {
        return recordSet2String(DatabaseUtil.runQuery(con, sql), seperator);
    }

    /**
     * Gets a count of the number of rows in a database table.
     * 
     * @param con
     *            Connection to the database containing the table.
     * @param table
     *            The name of the table.
     * @return long A count on the rows in the named table.
     * @throws SQLException
     *             the sQL exception
     */
    static public long getTableRowCount(Connection con, String table) throws SQLException {
        ResultSet rs = runQuery(con, "SELECT COUNT(*) FROM " + table);
        try {
            if (!rs.next()) {
                throw new RuntimeException("Can't count records in table: " + table);
            }
            return rs.getLong(1);
        } finally {
            DatabaseUtil.close(rs);
        }
    }

    /**
     * Count the number of rows returned by an sql statement without having to
     * retrieve the data.
     * 
     * @param con
     *            Connection to the database.
     * @param sql
     *            SQL string to execute.
     * @param params
     *            Array of arguments for the SQL statement.
     * @return The number of rows the statement will return.
     * @throws SQLException
     *             the sQL exception
     */
    static public long getSQLRowCount(Connection con, String sql, Object[] params) throws SQLException {
        ResultSet rs = runQuery(con, "SELECT COUNT(*) FROM (" + sql + ") a", params);
        try {
            if (!rs.next()) {
                throw new RuntimeException("Can't count records in table: " + sql);
            }
            return rs.getLong(1);
        } finally {
            close(rs);
        }
    }

    /**
     * Count the number of rows returned by an sql statement without having to
     * retrieve the data.
     * 
     * @param con
     *            Connection to the database.
     * @param sql
     *            SQL string to execute.
     * @return The number of rows the statement will return.
     * @throws SQLException
     *             the sQL exception
     */
    static public long getSQLRowCount(Connection con, String sql) throws SQLException {
        return getSQLRowCount(con, sql, null);
    }

    /**
     * Build the SQL query to get the distinct values from a named table and
     * columns.
     * 
     * @param srcTableName
     *            the name of the source table
     * @param srcColumns
     *            the name of the source column
     * 
     * @return SQL statement for
     */
    public static String buildTableDistinct(String srcTableName, String[] srcColumns) {
        StringBuffer buf = new StringBuffer(64);
        buf.append("SELECT DISTINCT ");
        for (int i = 0; i < srcColumns.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(srcColumns[i]);
        }
        buf.append(" FROM ").append(srcTableName);
        return buf.toString();
    }

    /**
     * Executes the supplied SQL using the specified Connection.
     * 
     * @param con
     *            The Connection to execute the SQL against.
     * @param sql
     *            An SQL statement to run.
     * @return Number of records updated.
     * @throws SQLException
     *             the sQL exception
     */
    public static int runUpdate(Connection con, String sql) throws SQLException {
        Statement stmt = con.createStatement();
        try {
            return stmt.executeUpdate(sql);
        } finally {
            stmt.close();
        }
    }

    /**
     * Executes the supplied parameterised SQL string putting the updateValues
     * into the placeholders in the order they are supplied.
     * 
     * @param con
     *            Connection with which to execute the query.
     * @param sql
     *            SQL statement containing ? parameter holders.
     * @param args
     *            Values to put into parameters of SQL.
     * @return Number of records updated/inserted.
     * @throws SQLException
     *             the sQL exception
     */
    public static int runUpdate(Connection con, String sql, Object[] args) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        try {
            setPerparedArguments(stmt, args);
            return stmt.executeUpdate();
        } finally {
            stmt.close();
        }
    }

    /**
     * Executes the supplied SQL using the specified Connection.
     * 
     * @param con
     *            The Connection to execute the SQL against.
     * @param sql
     *            An SQL statement to run.
     * @return ResultSet for sql. The caller should call closeResultSet on this
     *         when finished.
     * @throws SQLException
     *             the sQL exception
     */
    public static ResultSet runQuery(Connection con, String sql) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(sql);
    }

    /**
     * Executes the supplied parameterised SQL using the specified Connection
     * and parameters. Callers should call #close(ResultSet) on the returned
     * ResultSet.
     * 
     * @param con
     *            The Connection to execute the SQL against.
     * @param sql
     *            A parameterised SQL statement to run.
     * @param args
     *            Values for parameters.
     * @return ResultSet for sql. The caller should call closeResultSet on this
     *         when finished.
     * @throws SQLException
     *             the sQL exception
     */
    public static ResultSet runQuery(Connection con, String sql, Object[] args) throws SQLException {
        // Logger.debug ( "DatabaseUtil.runQuery: " + sql );
        PreparedStatement stmt = con.prepareStatement(sql);
        try {
            setPerparedArguments(stmt, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            close(stmt);
            throw e;
        }
    }

    /**
     * Extracts a value from a database table.
     * 
     * @param con
     *            Connection to use when connecting to the database.
     * @param table
     *            Name of the table.
     * @param field
     *            Name of the field in the table to return.
     * @param where
     *            Where clause to select the row to return.
     * @return The value of the named field in the first row matching the where
     *         clause.
     * @throws SQLException
     *             the sQL exception
     */
    public static Object getFieldValue(Connection con, String table, String field, String where) throws SQLException {
        ResultSet rs = runQuery(con, "SELECT " + field + " FROM " + table + " WHERE " + where);
        try {
            if (!rs.next()) {
                throw new RuntimeException("Query returned no rows: SELECT " + field + " FROM " + table + " WHERE "
                    + where);
            }
            return rs.getObject(1);
        } finally {
            close(rs);
        }
    }

    /**
     * Returns an Object array containing the values of the named columns.
     * 
     * @param rs
     *            ResultSet to extract values from.
     * @param cols
     *            Array of field names.
     * @return Values of the named fields in the same orders as they appear in
     *         cols.
     * @throws SQLException
     *             the sQL exception
     */
    public static Object[] extractFieldValues(ResultSet rs, String[] cols) throws SQLException {
        if (cols == null) {
            return null;
        }
        Object[] vals = new Object[cols.length];
        for (int i = 0; i < cols.length; i++) {
            vals[i] = rs.getObject(cols[i]);
        }
        return vals;
    }

    /**
     * Deletes rows from a table where they match the supplied criteria.
     * 
     * @param con
     *            BatchTransformation being done.
     * @param tableName
     *            Name of the table to delete from.
     * @param whereFields
     *            Ordered list of the fields to match the given values.
     * @param whereValues
     *            Ordered list of the values of the fields above.
     * @return The number of warnings raised.
     * @throws SQLException
     *             the sQL exception
     */
    public static int removeRows(Connection con, String tableName, String[] whereFields, Object[] whereValues)
        throws SQLException {
        StringBuffer sql = new StringBuffer(128);
        sql.append("DELETE FROM ").append(tableName).append(" WHERE ");
        for (int i = 0; i < whereFields.length; i++) {
            if (i > 0) {
                sql.append(" AND ");
            }
            sql.append(whereFields[i]).append("=?");
        }

        return runUpdate(con, sql.toString(), whereValues);
    }

    /**
     * Build the SQL query to perform RI check between child and parent tables.
     * The child table is supposed to have a foreign key into the parent table.
     * 
     * @param childTable
     *            the name of the source table
     * @param childColumns
     *            collection of source columns
     * @param parentTable
     *            the name of the target table
     * @param parentColumns
     *            collection of target columns
     * 
     * @return SQL statement to check RI for the given parameters.
     */
    public static String buildTableRICheck(String childTable, String[] childColumns, String parentTable,
        String[] parentColumns) {
        StringBuffer sql = new StringBuffer(128);
        sql.append("SELECT ").append(StringUtils.join(childColumns, ", ")).append(" FROM ").append(childTable)
            .append(" C WHERE IS_DELETED='N' AND NOT EXISTS (SELECT * FROM ").append(parentTable)
            .append(" WHERE IS_DELETED='N'");

        // append the search columns to our query statement
        for (int i = 0; i < childColumns.length; i++) {
            sql.append(" AND ").append(parentColumns[i]).append("=C.").append(childColumns[i].trim());
        }
        return sql.append(");").toString();
    }

    /**
     * Build the SQL query to check columns on the same table.
     * 
     * @param tablename
     *            the name of the lookup table
     * @param keyCols
     *            the key cols
     * @param lhs
     *            the name of the source column
     * @param operator
     *            the operator
     * @param rhs
     *            the name of the target column to check against
     * @return String containing an SQL statement returning all rows which fail
     *         the specified test.
     */
    public static String buildColComparisonQuery(String tablename, String[] keyCols, String lhs, String operator,
        String rhs) {
        StringBuffer sql = new StringBuffer(64);
        sql.append("SELECT ").append(lhs).append(", ").append(rhs).append(", ").append(StringUtils.join(keyCols, ", "))
            .append(" FROM ").append(tablename).append(" WHERE NOT ").append(lhs).append(operator).append(rhs);
        return sql.toString();
    }

    /**
     * Build the SQL query to check if a column is outside the range of values
     * between two target columns.
     * 
     * @param tablename
     *            the name of the lookup table
     * @param keyCols
     *            columns to include in the result set to identify the records
     * @param low
     *            the name of the source column
     * @param mid
     *            the name of the first target column to check against
     * @param high
     *            the name of the second target column to check against
     * 
     * @return A String containing an SQL statement
     */
    public static String buildTableInRangeQuery(String tablename, String[] keyCols, String low, String mid, String high) {
        StringBuffer sql = new StringBuffer(128);
        sql.append("SELECT ").append(low).append(", ").append(mid).append(", ").append(high).append(", ")
            .append(StringUtils.join(keyCols, ", ")).append(" FROM ").append(tablename).append(" WHERE ").append(mid)
            .append(" NOT BETWEEN ").append(low).append(" AND ").append(high);
        return sql.toString();
    }

    /**
     * Return a result set containing records which match the given criteria.
     * 
     * @param bt
     *            BatchTransformation object this call is part of.
     * @param tablename
     *            Name of the table to check.
     * @param keyCols
     *            Ordered names of the fields which uniquely identify rows in
     *            the table.
     * @param whereCols
     *            Ordered names of fields used to select the rows to generate
     *            warnings for.
     * @param whereValues
     *            Ordered values of the where fields which will cause the row to
     *            be selected to generate a warning. Each element may also
     *            contain an IN expression, e.g. "IN(1,2,3)", "IN('a','d')"
     * @return A result set containing all records for which log info is to be
     *         generated.
     * @throws SQLException
     *             the sQL exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    public static ResultSet getLoggableRecords(Connection bt, String tablename, String[] keyCols, String[] whereCols,
        Object[] whereValues) throws SQLException, IllegalArgumentException {
        if (null != whereCols && null != whereValues && whereCols.length != whereValues.length) {
            throw new IllegalArgumentException(MessageFormat.format(
                "Invalid parameters passed to generateWarnings.\n\ttable: {0}\n\twhereFields: {1}\n\toldValues: {2}",
                new Object[] { tablename, new Integer(whereCols.length), new Integer(whereValues.length) }));
        }
        StringBuffer sql = new StringBuffer(64);
        sql.append("SELECT ").append(StringUtils.join(keyCols, ",")).append(",")
            .append(StringUtils.join(whereCols, ",")).append(" FROM ").append(tablename);

        boolean hasWhere = (null != whereCols && whereCols.length > 0);
        if (hasWhere) {
            sql.append(" WHERE ");
            for (int i = 0; i < whereCols.length; i++) {
                if (i > 0) {
                    sql.append(" AND ");
                }
                if (isInClause(whereValues[i])) {
                    sql.append(whereCols[i]).append(" ").append(whereValues[i]);
                } else {
                    sql.append(whereCols[i]).append("=?");
                }
            }
        }

        return runQuery(bt, sql.toString(), whereValues);
        // PreparedStatement stmt = bt.prepareStatement ( sql.toString () );
        // if ( hasWhere )
        // {
        // for ( int i = 0, p = 1; i < whereCols.length; i++ )
        // {
        // if ( !isInClause ( whereValues[i] ) )
        // {
        // stmt.setObject ( p++, whereValues[i] );
        // }
        // }
        // }
        // return stmt.executeQuery ();
    }

    /**
     * Sets the parameters in a PreparedStatement to the values supplied.
     * 
     * @param stmt
     *            PreparedStatement to process.
     * @param args
     *            Values to set parameters to.
     * @throws SQLException
     *             the sQL exception
     */
    private static void setPerparedArguments(PreparedStatement stmt, Object[] args) throws SQLException {
        if (args == null) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (!isInClause(args[i])) {
                if (args[i] instanceof Date) {
                    stmt.setTimestamp(i + 1, new java.sql.Timestamp(((java.util.Date) args[i]).getTime()));
                } else {
                    stmt.setObject(i + 1, args[i]);
                }
            }
        }
    }

    /**
     * Checks if is in clause.
     * 
     * @param obj
     *            the obj
     * @return true, if is in clause
     */
    private static boolean isInClause(Object obj) {
        String value = StringUtils.deleteWhitespace(obj.toString()).toUpperCase();
        return value.startsWith("IN(") || value.startsWith("NOTIN(");
    }

}

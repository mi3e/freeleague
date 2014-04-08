package ie.logn.utils.sql;

import java.util.List;
import java.util.regex.Matcher;

public class SqlUtils {

    public static String getAndJoinForSqlClauses(String strCriterea1, String strCriterea2) {
        return " ( " + strCriterea1 + " AND " + strCriterea2 + " ) ";
    }

    public static String getOrJoinForSqlClauses(String strCriterea1, String strCriterea2) {
        return " ( " + strCriterea1 + " OR " + strCriterea2 + " ) ";
    }

    public static <T> String getOrSubClauseSQLForColValues(String col, List<T> values, boolean isString) {
        StringBuilder sb = new StringBuilder("");
        int i = 0;
        for (T t : values) {
            String s = t.toString();
            if (i == 0) {
                sb.append(" ( ");
            }
            if (i > 0) {
                sb.append("\n").append(" OR ");
            }
            sb.append(col).append(" = ");
            if (isString) {
                sb.append("'");
            }
            sb.append(escapeReservedChars(s));
            if (isString) {
                sb.append("'");
            }
            i++;
        }
        if (i > 0) {
            sb.append(" ) ").append("\n");
        }
        return sb.toString();
    }

    public static String escapeReservedChars(String s) {
        String newS = s;
        if (newS.indexOf(',') >= 0) {
            newS = s.replaceAll(",", "\\,");
        }
        if (newS.indexOf('&') >= 0) {
            newS = s.replaceAll("&", "\\&");
        }
        if (newS.indexOf('?') >= 0) {
            newS = s.replaceAll("?", "\\?");
        }
        if (newS.indexOf('{') >= 0) {
            newS = s.replaceAll("{", "\\{");
        }
        if (newS.indexOf('}') >= 0) {
            newS = s.replaceAll("}", "\\}");
        }
        if (newS.indexOf('\\') >= 0) {
            newS = s.replaceAll("\\", Matcher.quoteReplacement("\\"));
        }
        if (newS.indexOf('(') >= 0) {
            newS = s.replaceAll("(", "\\(");
        }
        if (newS.indexOf(')') >= 0) {
            newS = s.replaceAll(")", "\\)");
        }
        if (newS.indexOf('[') >= 0) {
            newS = s.replaceAll("[", "\\[");
        }
        if (newS.indexOf(']') >= 0) {
            newS = s.replaceAll("]", "\\]");
        }
        if (newS.indexOf('-') >= 0) {
            newS = s.replaceAll("-", "\\-");
        }
        if (newS.indexOf(';') >= 0) {
            newS = s.replaceAll(";", "\\;");
        }
        if (newS.indexOf('~') >= 0) {
            newS = s.replaceAll("~", "\\~");
        }
        if (newS.indexOf('|') >= 0) {
            newS = s.replaceAll("|", "\\|");
        }
        if (newS.indexOf('&') >= 0) {
            newS = s.replaceAll("&", "\\&");
        }
        if (newS.indexOf('!') >= 0) {
            newS = s.replaceAll("!", "\\!");
        }
        if (newS.indexOf('>') >= 0) {
            newS = s.replaceAll(">", "\\>");
        }
        if (newS.indexOf('*') >= 0) {
            newS = s.replaceAll("*", "\\*");
        }
        if (newS.indexOf('%') >= 0) {
            newS = s.replaceAll("%", "\\%");
        }
        if (newS.indexOf('_') >= 0) {
            newS = s.replaceAll("_", "\\_");
        }

        return newS;
    }

}

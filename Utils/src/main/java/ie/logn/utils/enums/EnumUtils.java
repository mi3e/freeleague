package ie.logn.utils.enums;

public class EnumUtils {
    /**
     * * A common method for all enums since they can't have another base class
     * * @param <T> Enum type * @param c enum type. * @param string case
     * insensitive * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }

    public static <T extends Enum<T>> String toString(T t) {
        if (t != null) {
            return t.toString();
        }
        return null;
    }

}

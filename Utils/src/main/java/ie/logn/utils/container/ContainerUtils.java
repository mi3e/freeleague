package ie.logn.utils.container;

import java.util.Collection;
import java.util.Map;

/**
 * The Class ContainerUtils
 */
public final class ContainerUtils {

    /**
     * Instantiates a new generic map helper.
     */
    private ContainerUtils() {
    }

    public static <Element> boolean notEmpty(Collection<Element> collection) {
        if (collection != null && collection.size() > 0) {
            return true;
        }
        return false;
    }

    public static <MapKey, MapValue> boolean notEmpty(Map<MapKey, MapValue> map) {
        if (map != null && map.size() > 0) {
            return true;
        }
        return false;
    }

    public static <Element> boolean empty(Collection<Element> collection) {
        return !notEmpty(collection);
    }

    public static <MapKey, MapValue> boolean empty(Map<MapKey, MapValue> map) {
        return !notEmpty(map);
    }

    /**
     * Check for null get.
     * 
     * @param <MapKey>
     *            the generic key type
     * @param <MapValue>
     *            the generic value type
     * @param map
     *            the map
     * @param key
     *            the key
     * @param notAvailableDefault
     *            the not available default
     * @return the map value
     */
    public static <MapKey, MapValue> MapValue chkNullGet(Map<MapKey, MapValue> map, MapKey key,
        MapValue notAvailableDefault) {
        if (map != null && map.size() > 0) {
            return map.get(key);
        }
        return notAvailableDefault;
    }
}

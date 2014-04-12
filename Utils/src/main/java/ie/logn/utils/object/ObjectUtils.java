package ie.logn.utils.object;

import ie.logn.utils.container.ContainerUtils;
import ie.logn.utils.file.CSVWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtils {

    public static <T> List<String> getObjectValues(final T object) {
        return ReflectionUtils.getObjectValues(object, new ArrayList<String>());
    }

    /**
     * @param object
     * @return as above, a list of strings based on the values returned from the
     *         objects get methods
     */
    public static <T> List<String> getObjectValues(final T object, final List<String> excludeMethods) {
        return ReflectionUtils.getObjectValues(object, excludeMethods);
    }

    /**
     * @param objects
     *            list of objects to stringify, one line per object
     * @param separator
     *            csv separator
     * @param generateHeader
     *            whether to generate a header or not
     * @return A string buffer consisting of one line per object, generated
     *         using the above methods.
     */
    public static <T> StringBuffer getCsvValuesForObjectList(final List<T> objects, char separator,
        boolean generateHeader) {

        if (ContainerUtils.empty(objects)) {
            return new StringBuffer();
        }

        StringWriter sw = new StringWriter();
        CSVWriter csvWriter = null;
        try{
        	csvWriter = new CSVWriter(sw, separator);
	
	        if (generateHeader) {
	            // Why can't I use T.class here. Type erasure..
	            List<String> headers = ReflectionUtils.convertGetterMethodsToFileHeader(objects.get(0).getClass());
	
	            csvWriter.writeNext(headers.toArray(new String[headers.size()]));
	        }
	
	        for (T object : objects) {
	            List<String> objValues = getObjectValues(object);
	            csvWriter.writeNext(objValues.toArray(new String[objValues.size()]));
	        }
	
	        StringBuffer buf = new StringBuffer(sw.toString());
	
	        return buf;
        }
        finally {
        	if (csvWriter != null)
				try {
					csvWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
    }

    @Deprecated
    //Should use guava or apache utils
    public static boolean isNullOrEmpty(Object a) {
        if (a == null) {
            return true;
        }
        if (a instanceof String) {
            return ((String) a).isEmpty();
        }

        return false;
    }
}

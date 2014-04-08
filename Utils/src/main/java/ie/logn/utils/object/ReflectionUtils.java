package ie.logn.utils.object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.text.WordUtils;

public class ReflectionUtils {

    
    public static boolean compareObjects(Object one, Object two) {
        return compareObjects(one, two, new ArrayList<String>());
    }

    public static boolean compareObjects(Object one, Object two, List<String> excludeFields) {
        return EqualsBuilder.reflectionEquals(one, two, excludeFields);
    }


    public static String convertMethodNameToSqlColumnName(final String methodName, final String methodPrefix) {
        List<String> methodPrefixes = new ArrayList<String>();
        methodPrefixes.add(methodPrefix);
        return convertMethodNameToSqlColumnName(methodName, methodPrefixes);
    }

    public static String convertMethodNameToSqlColumnName(final String methodName, final List<String> methodPrefixes) {
        if (StringUtils.isEmpty(methodName))
            return methodName;

        for (String methodPrefix : methodPrefixes) {
            if (!methodName.startsWith(methodPrefix)) {
                continue;
            }

            String methodNameMod = methodName.replaceFirst(methodPrefix, "");

            methodNameMod = WordUtils.uncapitalize(methodNameMod.substring(0, 1)) + methodNameMod.substring(1);

            for (int i = 0; i < methodNameMod.length(); ++i) {
                char c = methodNameMod.charAt(i);

                if (Character.isUpperCase(c)) {
                    methodNameMod = methodNameMod.substring(0, i) + "_" + methodNameMod.substring(i);
                    i++;
                }
            }

            return methodNameMod.toUpperCase();
        }

        return methodName;
    }

    public static <T> List<String> convertGetterMethodsToFileHeader(Class<T> clazz) {
        return convertGetterMethodsToFileHeader(clazz, new ArrayList<String>());
    }

    public static <T> List<String> convertGetterMethodsToFileHeader(Class<T> clazz, final List<String> excludeMethods) {
        List<String> headerNames = new ArrayList<String>();

        for (Method method : clazz.getMethods()) {
            List<String> methodPrefixes = new ArrayList<String>();

            methodPrefixes.add("get");
            methodPrefixes.add("is");

            List<String> excludeMethodsList = new ArrayList<String>();
            excludeMethodsList.addAll(excludeMethods);
            excludeMethodsList.add("getClass");

            if (startsWith(method.getName(), methodPrefixes) && (!stringEquals(method.getName(), excludeMethodsList)))
                headerNames.add(convertMethodNameToSqlColumnName(method.getName(), methodPrefixes));
        }

        return headerNames;
    }

    public static <T> List<String> getObjectValues(final T object, final List<String> excludeMethods) {
        List<String> objectValues = new ArrayList<String>();

        Class<? extends T> clazz = (Class<? extends T>) object.getClass();

        List<String> methodPrefixes = new ArrayList<String>();

        methodPrefixes.add("get");
        methodPrefixes.add("is");

        List<String> excludeMethodsList = new ArrayList<String>();
        excludeMethodsList.addAll(excludeMethods);
        excludeMethodsList.add("getClass");

        for (Method method : clazz.getMethods()) {

            if (startsWith(method.getName(), methodPrefixes) && (!stringEquals(method.getName(), excludeMethodsList))) {
                try {
                    Object val = method.invoke(object, (Object)null);

                    objectValues.add(val == null ? null : val.toString());

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return objectValues;
    }

    public static <T> HashMap<String, Method> getNameToMethodMap(Class<? extends T> clazz, final String methodPrefix) {
        List<String> methodPrefixes = new ArrayList<String>();
        methodPrefixes.add(methodPrefix);
        return getNameToMethodMap(clazz, methodPrefixes);
    }

    public static <T> HashMap<String, Method> getNameToMethodMap(Class<? extends T> clazz,
        final List<String> methodPrefixes) {

        HashMap<String, Method> methodMap = new HashMap<String, Method>();

        for (Method method : clazz.getMethods()) {
            if (startsWith(method.getName(), methodPrefixes)) {
                methodMap.put(convertMethodNameToSqlColumnName(method.getName(), methodPrefixes), method);
            }
        }

        return methodMap;
    }

    public static <T> Object convertToCorrectType(Class<T> parameterType, final String value) {

        //
        // TODO, expand as required with extra basic types
        //
        switch (parameterType.getName()) {
            case "boolean":
            case "java.lang.Boolean":
                if (StringUtils.isEmpty(value))
                    return false;
                if (value.equalsIgnoreCase("true"))
                    return true;
                return false;
            case "java.util.Date":
                if (StringUtils.isEmpty(value))
                    return null;
                try {
                	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
                    return df.parse(value);
                } catch (ParseException e) {
                    try {
                    	DateFormat df2 = new SimpleDateFormat("dd/mm/yyyy");
                        return df2.parse(value);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return null;
                    }
                }

            case "java.math.BigDecimal":
                if (StringUtils.isEmpty(value))
                    return null;
                return new BigDecimal(value);

            case "java.lang.Long":
            case "long":
                if (StringUtils.isEmpty(value))
                    return null;
                return new Long(value);

            default:
                return value;

        }
    }

    private static boolean startsWith(final String value, final List<String> preFixes) {
        for (String prefix : preFixes) {
            if (value.startsWith(prefix))
                return true;
        }

        return false;
    }

    private static boolean stringEquals(final String value, final List<String> toMatchAgainst) {
        for (String match : toMatchAgainst) {
            if (value.equalsIgnoreCase(match)) {
                return true;
            }
        }

        return false;
    }

}

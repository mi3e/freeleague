/**
 * 
 */
package ie.logn.utils.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author m06d529
 * 
 */
public class DirectoryUtils {
    public static File createTempDirectory() throws IOException {
        final File temp;

        temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

        if (!(temp.delete())) {
            throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
        }

        if (!(temp.mkdir())) {
            throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
        }

        return (temp);
    }

    public static void deleteFileOrDirectory(File file) throws IOException {
        if (file != null && file.exists())
            FileUtils.deleteDirectory(file);
    }

    public static boolean doesFileOrDirectoryExist(String filename) {
        File file = new File(filename);

        return file.exists();
    }

}

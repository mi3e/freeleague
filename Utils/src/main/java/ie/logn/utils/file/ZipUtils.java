/**
 * 
 */
package ie.logn.utils.file;

import ie.logn.utils.logger.DefaultLogger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author m06d529
 * 
 */
public class ZipUtils {

    public static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);

        in.close();
        out.close();
    }

    public static void unzip(String fullFilePath, String destinationDirectory) throws IOException {
        Enumeration<? extends ZipEntry> entries;
        ZipFile zipFile;

        try {
            zipFile = new ZipFile(fullFilePath);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    // Assume directories are stored parents first then
                    // children.
                    DefaultLogger.debug("Extracting directory: " + entry.getName());
                    // This is not robust, just for demonstration purposes.
                    (new File(entry.getName())).mkdir();
                    continue;
                }
                DefaultLogger.debug("Extracting file: " + entry.getName());

                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(
                    destinationDirectory + "/" + entry.getName())));
            }

            zipFile.close();
        } catch (IOException ioe) {
            DefaultLogger.error("Unhandled exception:");
            ioe.printStackTrace();
            return;
        }
    }

}

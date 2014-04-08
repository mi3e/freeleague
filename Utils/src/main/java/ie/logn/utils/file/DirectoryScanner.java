package ie.logn.utils.file;

import ie.logn.utils.logger.DefaultLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Class containing methods for reading directory structures from disk. The user
 * provides a callback command object to process the directory entries.
 * 
 */
public class DirectoryScanner {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        getFileListing(new File(args[0]));
    }

    /**
     * Lists the contents of a directory and all it's sub-directories to stdout
     * using the DefaultScannerCommand class.
     * 
     * @param root
     *            Directory to start from.
     * 
     * @throws FileNotFoundException
     */
    static public void getFileListing(File root) throws FileNotFoundException {
        getFileListing(root, new DefaultScannerCommand());
    }

    /**
     * Scans a directory and all of its accessible sub-directories calling the
     * ScannerCommand object for each entry it finds.
     * 
     * @param root
     *            Directory to start from.
     * @param cmd
     *            ScannerCommand used to process each entry.
     * 
     * @throws FileNotFoundException
     */
    static public void getFileListing(File root, ScannerCommand cmd) throws FileNotFoundException {
        validateDirectory(root);
        scanDirectory(root, cmd);
    }

    /**
     * Recursively scans a directory passing each scanned entry to the
     * ScannerCommand object.
     * 
     * @param dir
     *            Directory to scan.
     * @param cmd
     *            ScannerCommand object used to process each entry.
     */
    static private void scanDirectory(File dir, ScannerCommand cmd) {
        if (!dir.canRead()) {
            return;
        }
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            try {
                cmd.processEntry(files[i]);
            } catch (DirectoryScannerException e) {
                DefaultLogger.error("Error processing file: " + files[i], e);
            }
            if (files[i].isDirectory()) {
                scanDirectory(files[i], cmd);
            }
        }
    }

    /**
     * Ensure the File object is a valid directory.
     * 
     * @param dir
     *            Directory to validate.
     * 
     * @throws FileNotFoundException
     */
    static private void validateDirectory(File dir) throws FileNotFoundException {
        if (dir == null) {
            throw new IllegalArgumentException("Directory cannot be null.");
        }
        if (!dir.exists()) {
            throw new FileNotFoundException("Directory does not exist: " + dir);
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Root is not a directory: " + dir);
        }
    }

}

/**
 * ScannerCommand that dumps listed directories to standard out.
 * 
 */
class DefaultScannerCommand implements ScannerCommand {

    /**
     * @see ScannerCommand#processEntry(java.io.File)
     */
    public void processEntry(File file) {
        System.out.println(getFileDescription(file));
    }

    private String getFileDescription(File file) {
        StringBuffer buf = new StringBuffer();
        buf.append(file.getAbsolutePath());
        if (file.isDirectory()) {
            buf.append(File.separatorChar);
        } else {
            buf.append(getSizeString(file));
        }
        if (!file.canRead()) {
            buf.append(" [NOT ACCESSIBLE]");
            if (!file.canWrite()) {
                buf.append(" [READ ONLY]");
            }
        }
        buf.append(", Last Modified: ").append(new Date(file.lastModified()));
        return buf.toString();
    }

    private String getSizeString(File file) {
        String unit = " bytes";
        double adjSize;
        long size = file.length();
        if (size > GB) {
            adjSize = size / GB;
            unit = "Gb";
        } else if (size > MB) {
            adjSize = size / MB;
            unit = "MB";
        } else if (size > KB) {
            adjSize = size / KB;
            unit = "KB";
        } else {
            adjSize = size;
        }
        return MessageFormat.format(" Size: {0,number,0.##}{1}", new Object[] { new Double(adjSize), unit });
    }

    private static final double KB = 1024;
    private static final double MB = KB * 1024;
    private static final double GB = MB * 1024;
}

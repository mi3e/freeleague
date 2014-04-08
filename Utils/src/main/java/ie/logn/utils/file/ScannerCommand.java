package ie.logn.utils.file;

import java.io.File;

/**
 * Command object called by getFileListing for each entry found while scanning a
 * directory.
 * 
 */
public interface ScannerCommand {

    /**
     * Method called once for each entry found in a directory. Should be
     * overriden to implement application specific action for directory entries.
     * 
     * @param file
     *            File to be processed.
     * 
     * @throws DirectoryScannerException
     */
    public void processEntry(File file) throws DirectoryScannerException;

}
package ie.logn.utils.file;

public class DirectoryScannerException extends Exception {

    /**
     * Constructor.
     */
    public DirectoryScannerException() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param arg0
     */
    public DirectoryScannerException(String arg0) {
        super(arg0);
    }

    /**
     * Constructor.
     * 
     * @param arg0
     */
    public DirectoryScannerException(Throwable arg0) {
        super(arg0);
    }

    /**
     * Constructor.
     * 
     * @param arg0
     * @param arg1
     */
    public DirectoryScannerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

}

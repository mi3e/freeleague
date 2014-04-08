package ie.logn.utils.db;

/**
 * Interface used to filter text. Might be used for pre-processing SQL to ensure
 * platform specific type mappings.
 * 
 * @version $Revision: 1.1 $
 */
public interface TextFilter {

    /**
     * @param text
     *            Text to apply the filter to.
     * 
     * @return The result of applying the filter to text.
     */
    public String filter(String text);

}

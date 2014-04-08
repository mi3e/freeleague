package ie.logn.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class FileReadWriteUtils {

    public static Reader replaceCharInReader(Reader reader, char toReplace, char newChar) throws IOException {
        StringWriter stringWriter = new StringWriter();

        IOUtils.copy(reader, stringWriter);

        String string = stringWriter.toString();

        string = string.replace(toReplace, newChar);

        return new StringReader(string);
    }

    public static String readFileToString(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));

        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }
}

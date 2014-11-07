package Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;

/**
 * Created by saul on 10/22/14.
 */
public class IO {
    public static String getTextFromFile(String pathString) throws IOException {
        Path path = FileSystems.getDefault().getPath(pathString);
        Charset charset = Charset.forName("UTF-8");
        List<String> fileLines = readAllLines(path, charset);
        return Strings.join("\n", fileLines.toArray(new String[fileLines.size()]));
    }
}

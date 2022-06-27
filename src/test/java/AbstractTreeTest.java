import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public abstract class  AbstractTreeTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    protected Node createTree(String dirname, String filename)  {
        try {
            return mapper.readValue(new File(Paths.get("src", "test", "resources", "json", dirname, filename)
                    .toAbsolutePath().toString()), Node.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

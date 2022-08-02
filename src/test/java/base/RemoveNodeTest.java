package base;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import tree.Node;
import tree.RemoveNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveNodeTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectWriter pretty;

    static {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        pretty = mapper.writerWithDefaultPrettyPrinter();
    }

    protected Node createTree(String filename) {
        try {
            return mapper.readValue(new File(Paths.get("src", "test", "resources", "json", "remove-node", filename)
                    .toAbsolutePath().toString()), Node.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void shouldRemove1() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected1.json");
        final var actualRoot = RemoveNode.remove(root, 100);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove2() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected2.json");
        final var actualRoot = RemoveNode.remove(root, 150);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldNotRemove() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("tree.json");
        final var actualRoot = RemoveNode.remove(root, 66);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove3() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected3.json");
        final var actualRoot = RemoveNode.remove(root, 15);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove4() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected4.json");
        final var actualRoot = RemoveNode.remove(root, 11);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove5() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected5.json");
        final var actualRoot = RemoveNode.remove(root, 70);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove8() throws Exception {
        final var root = createTree("tree.json");
        final var expected = createTree("expected8.json");
        final var actualRoot = RemoveNode.remove(root, 20);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemove6() throws Exception {
        final Node root = null;
        final var expected = root;
        final var actualRoot = RemoveNode.remove(root, 4);
        assertEquals(pretty.writeValueAsString(expected), pretty.writeValueAsString(actualRoot));
    }

    @Test
    void shouldRemoveFast() throws Exception {

        final var root = createTree("large-tree.json");
        final var start = System.currentTimeMillis();
        final var iters = 100000;
        for (int i = 0; i < iters; i++) {
            final var actualRoot = RemoveNode.remove(root, ThreadLocalRandom.current().nextInt());
        }
        final var end = System.currentTimeMillis();
        final var total = end - start;
        System.out.printf("Iters: %s, millis per iter: %f(ms), total: %s(ms)", iters, (double)total / iters, total);
    }
}
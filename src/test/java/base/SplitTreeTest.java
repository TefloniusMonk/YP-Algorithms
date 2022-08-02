package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tree.SplitTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitTreeTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    protected SplitTree.Node createTree(String filename) {
        try {
            return mapper.readValue(new File(Paths.get("src", "test", "resources", "json", "tree", filename)
                    .toAbsolutePath().toString()), SplitTree.Node.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldSplit1() {

        for (int i = 1; i < 20; i++) {
            final SplitTree.Node node = createTree("tree.json");

            try {
                final List<SplitTree.Node> res = SplitTree.split(node, i);
            } catch (Exception e) {
                System.out.println(i);
                throw e;
            }
        }
    }

    @Test
    void shouldSplit5() {

        final SplitTree.Node node = createTree("tree.json");

        final List<SplitTree.Node> res = SplitTree.split(node, 1);
        assertEquals(1, 1);

    }

    @Test
    void shouldSplit4() {
        final SplitTree.Node node = createTree("tree1.json");
        final List<SplitTree.Node> res = SplitTree.split(node, 1);
        assertEquals(1, res.get(0).getSize());
        assertEquals(7, res.get(1).getSize());
    }

    @Test
    void shouldSplit() {
        for (int i = 1; i < 6; i++) {
            final SplitTree.Node node6 = createNode();
            final List<SplitTree.Node> res = SplitTree.split(node6, i);
            assertEquals(i, res.get(0).getSize());
            assertEquals(6 - i, res.get(1).getSize());
        }
    }

    @Test
    void shouldSplit6() {
        final SplitTree.Node node6 = createNode2();
        final List<SplitTree.Node> res = SplitTree.split(node6, 4);
        assertEquals(4, res.get(0).getSize());
        assertEquals(2, res.get(1).getSize());

    }

    @Test
    void shouldSplit2() {
        final SplitTree.Node node6 = createNode();
        final List<SplitTree.Node> res = SplitTree.split(node6, 1);
        assertEquals(1, res.get(0).getSize());
        assertEquals(5, res.get(1).getSize());
    }

    @Test
    void shouldSplit3() {
        final SplitTree.Node node6 = createNode();
        final List<SplitTree.Node> res = SplitTree.split(node6, 6);
        assertEquals(6, res.get(0).getSize());
    }

    private SplitTree.Node createNode() {
        final SplitTree.Node node1 = new SplitTree.Node(null, null, 3, 1);
        final SplitTree.Node node2 = new SplitTree.Node(null, node1, 2, 2);
        final SplitTree.Node node3 = new SplitTree.Node(null, null, 8, 1);
        final SplitTree.Node node4 = new SplitTree.Node(null, null, 11, 1);
        final SplitTree.Node node5 = new SplitTree.Node(node3, node4, 10, 3);
        final SplitTree.Node node6 = new SplitTree.Node(node2, node5, 5, 6);
        return node6;
    }

    private SplitTree.Node createNode2() {
        final SplitTree.Node node1 = new SplitTree.Node(null, null, 1, 1);
        final SplitTree.Node node2 = new SplitTree.Node(node1, null, 2, 2);
        final SplitTree.Node node3 = new SplitTree.Node(node2, null, 8, 3);
        final SplitTree.Node node4 = new SplitTree.Node(node3, null, 11, 4);
        final SplitTree.Node node5 = new SplitTree.Node(node4, null, 15, 5);
        final SplitTree.Node node6 = new SplitTree.Node(node5, null, 20, 6);
        return node6;
    }
}
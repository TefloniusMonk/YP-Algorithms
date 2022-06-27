import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxDepthTest extends AbstractTreeTest{
    @Test
    void shouldFindMaxDepth() throws Exception {
        final var node = createTree("max", "tree.json");
        final var actual = MaxDepth.treeSolution(node);
        final var expected = 5;
        assertEquals(expected, actual);
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BalancedTreeTest extends AbstractTreeTest{
    @Test
    void shouldCheckIfBalanced1(){
        final var node = createTree("balanced", "tree1.json");
        assertTrue(BalancedTree.treeSolution(node));
    }

    @Test
    void shouldCheckIfBalanced2(){
        final var node = createTree("balanced", "tree2.json");
        assertFalse(BalancedTree.treeSolution(node));
    }

    @Test
    void shouldCheckIfBalanced3(){
        final var node = createTree("balanced", "tree3.json");
        assertTrue(BalancedTree.treeSolution(node));
    }

    @Test
    void shouldCheckIfBalanced4(){
        final var node = createTree("balanced", "tree4.json");
        assertFalse(BalancedTree.treeSolution(node));
    }
}
import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class Node implements Serializable {
     int value;
     Node left;
     Node right;

     @JsonCreator
    private Node() {
    }


    private void setValue(int value) {
        this.value = value;
    }

    private void setLeft(Node left) {
        this.left = left;
    }

    private void setRight(Node right) {
        this.right = right;
    }

    private int getValue() {
        return value;
    }

    private Node getLeft() {
        return left;
    }

    private Node getRight() {
        return right;
    }
}

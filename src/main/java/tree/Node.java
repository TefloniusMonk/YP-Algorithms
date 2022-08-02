package tree;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class Node implements Serializable {
     public int value;
     public Node left;
     public Node right;

     @JsonCreator
     public Node() {
    }

    public Node(Node left, Node right, int value) {
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}

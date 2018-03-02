package Generator;

public class NodeNavigation {
    private Node node;
    private Direction direction;

    public NodeNavigation(Node node, Direction direction) {
        this.node = node;
        this.direction = direction;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

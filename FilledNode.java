package anillo;

public class FilledNode extends Node {
    private final Object data;
    FilledNode next;

    public FilledNode(Object data) {
        this.data = data;
        this.next = null;
    }

    public Object getData() {
        return this.data;
    }
    public Node getNext() {
        return this.next;
    }


public Node addNext(Object data) {
        FilledNode previousNode = this.getPrevious();
        FilledNode newNode = new FilledNode(data);
        previousNode.next = newNode;
        newNode.next = this;
        return newNode;
    }

    public void removeNext() {
        FilledNode previous = this.getPrevious();
        previous.next = this.next;
    }

    private FilledNode getPreviousRecursive(FilledNode previous) {
        return (previous.next == this) ? previous : getPreviousRecursive((FilledNode) previous.next);
    }
    private FilledNode getPrevious() {
        return getPreviousRecursive(this);
    }
}


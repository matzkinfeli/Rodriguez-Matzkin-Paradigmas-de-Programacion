package anillo;

public class EmptyNode extends Node {
    public Object getData() {
        throw new IllegalStateException("Un nodo vacio no tiene datos");
    }

    public Node getNext() {
        throw new IllegalStateException("No hay siguiente elemento");
    }

    public Node addNext(Object data) {
        FilledNode newNode = new FilledNode(data);
        newNode.next = newNode;
        return newNode;
    }

    public void removeNext() {
        throw new IllegalStateException("No hay ningun elemento para realizar la operacion");
    }
}

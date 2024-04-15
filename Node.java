package anillo;

public abstract class Node {

    public abstract Object getData();
    public abstract Node getNext();
    public abstract void removeNext();
    public abstract Node addNext(Object data);
}


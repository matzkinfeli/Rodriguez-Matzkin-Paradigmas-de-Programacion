package anillo;

public class Ring {
    public Node current;

    public Ring() {
        this.current = new EmptyNode();
    }

    public Ring next() {
        Ring nextRing = new Ring();
        nextRing.current = this.current.getNext();
        return nextRing;
    }

    public Object current() {
        return this.current.getData();
    }

    public Ring add(Object data) {
        this.current = this.current.addNext(data);
        return this;
    }

    public Ring remove() {
        if (this.current.getNext() == this.current) {
            this.current = new EmptyNode();
            return this;
        } else {
            Ring nextRing = new Ring();
            nextRing.current = this.current.getNext();
            this.current.removeNext();
            return nextRing;
        }
    }
}

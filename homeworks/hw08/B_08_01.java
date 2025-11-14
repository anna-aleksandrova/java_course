package homeworks.hw08;

public class B_08_01 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        while (!s.empty()) {
            try {
                System.out.print(s.pop() + " ");                
            } catch (Exception e) {
                System.out.println("pop() applied to an empty stack\n");
            }
        }
        System.out.println();
    }

}

class Stack<T> {
    public Node<T> top_node;

    public Stack() {
        this.top_node = null;
    }

    public boolean empty() {
        return this.top_node == null;
    }

    public void push(T item) {
        Node<T> node = new Node<T>(item);
        if (!this.empty()) {
            Node<T> temp = this.top_node;
            this.top_node = node;
            top_node.next(temp);
        }
        else {
            this.top_node = node;
        }
    }

    public T pop() throws Exception {
        if (this.empty()) throw new Exception("Stack: pop applied to empty container.");
        else {
            T res = this.top_node.item;
            this.top_node = this.top_node.next;
            return res;
        }
    }

    public T top() throws Exception {
        if (this.empty()) throw new Exception("Stack: pop applied to empty container.");
        else return this.top_node.item;
    }
}

class Node<T> {
    public T item;
    public Node<T> next;

    public Node(T item) {
        this.item = item;
        this.next = null;
    }

    public void next(Node<T> node) {
        this.next = node;
    }    
}

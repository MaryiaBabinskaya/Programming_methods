class Source {
    class Node { //sam wezel
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        Node() { //konstruktor
            value  = 0;
            left   = null;
            right  = null;
            parent = null;
        }
        Node(int v) {
            value  = v;
            left   = null;
            right  = null;
            parent = null;
        }
        public void print() {
            System.out.print(value + " ");
        }
    }
    Node root; //nasz korzen
    Source() {
        root = null;
    }
    private void preorder(Node t) { //idziemy po lewym boku, potem po prawym
        if (t != null) {
            t.print(); // is printed before calling recursively for children
            preorder(t.left);
            preorder(t.right);
        }
    }
    private void inorder(Node t) {
        if (t != null) {
            inorder(t.left);
            t.print(); // is printed in between recursive call with children
            inorder(t.right);
        }
    }
    private void postorder(Node t) { //z konc i lewego boku
        if (t != null) {
            postorder(t.left);
            postorder(t.right);
            t.print(); // printing after recursion
        }
    }
    private Node findExtremum(Node t, boolean searchMin) { // searching in branch
        Node p  = null;
        while (t != null) {
            p = t;
            t = searchMin ? t.left : t.right;
        }
        return p;
    }
    private Node delete(Node t, int x) {
        if (t != null) { // idzie do null
            if (x < t.value) t.left = delete(t.left, x);
            else if (x > t.value) t.right = delete(t.right, x);
            else { // if `x` is same as `t.value`, then this is the node to be deleted
                // Node with only one child or no child
                if (t.left == null) return t.right;
                else if (t.right == null) return t.left;
                t.value = findExtremum(t.right, true).value; // node with two children; get smallest to the right branch
                t.right = delete(t.right, t.value); // usun nastepce w kolejnosci
            }
        }
        return t;
    }
    public void print(String order) {
        switch (order) {
            case "pre":
                preorder(root);
                break;
            case "in":
                inorder(root);
                break;
            case "post":
                postorder(root);
                break;
        }
    }
    public Node min() {
        return findExtremum(root, true);
    }
    public Node max() {
        return findExtremum(root, false);
    }
    public Node search(int x) { //szukamy wezel
        Node t = root;
        while (t != null && t.value != x) t = x < t.value ? t.left : t.right;
        return t;
    }
    public Node insert(int x) { //nowy wezel
        Node s = new Node(x);
        if (root == null) // insert as first element
            root = s;
        else { // insert po czemu mamy
            Node t = root;
            Node p = null;
            while (t != null) { // szukamy odpowiednie miejsce, O(n)
                p = t;
                t = x < t.value ? t.left : t.right;
            }
            s.parent = p;
            if (x < p.value)  p.left = s; //w jaka strone postawic
            else p.right = s;
        }
        return s;
    }
    public void delete(int x) { //usuwanie
        root = delete(root, x);
    }
}
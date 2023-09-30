 //Maryia Babinskaya 1
import java.util.Scanner;
class Node {
////////////////////////////////////////////////////////////////////////////////////
    public Person info; // element danych (klucz)
    public Node left; // lewy potomek wezla
    public Node right; // prawy lewy potomek wezla
    // konstruktor
    public Node(Person _info) {
        info  = _info;
        left  = null;
        right = null;
    }
} //koniec klasy Node
////////////////////////////////////////////////////////////////////////////////////
class Person {
    public int priority; // priorytet osoby
    public String name;
    public String surname;
    // konstruktor
    public Person(int _priority, String _name, String _surname) {
        priority  = _priority;
        name  = _name;
        surname = _surname;
    }
} //koniec klasy Person
////////////////////////////////////////////////////////////////////////////////////
class Tree {
    Node root; //korzen
    int N; //ile elementow
    int index; //indeks poczatka/konca

    Tree() {
        root = null;
        N = 0;
        index = 0;
    }
//////////////////////////////////////////////////////////////////////////////////////
    //createPREODER - rekurencyjnie tworzy drzewo BST w kolejnosci PREODER(od poczatku do konca)
    //Zl. O(n)
    public Node createPREODER(Person[] arr, int n, int min, int max) { //node,left,right
        if (index >= n) return null; //if aktualny index >= ilosci stop
        Node root = null;
        Person prior1 = arr[index];
        if (prior1.priority > min && prior1.priority < max) { //porownujemy prior1
            root = new Node(prior1);
            index = index + 1;
            if (index < n) {
                //na poczatku lewo, potem prawo
                root.left = createPREODER(arr, n, min, prior1.priority);
                root.right = createPREODER(arr, n, prior1.priority, max);
            }
        }
        //zwraca referencje do korzenia calego drzewa
        return root;
    }
//////////////////////////////////////////////////////////////////////////////////////
    //createPOSTORDER - rekurencyjnie tworzy drzewo BST w kolejnosci POSTORDER(od konca do poczatku)
    //Zl. O(n)
    public Node createPOSTORDER(Person[] arr, int min, int max) { //left,right,node
        if (index < 0) return null; //if aktualny index < 0 wtedy stop
        Node root = null;
        Person prior1 = arr[index];
        if (prior1.priority > min && prior1.priority < max) {
            root = new Node(prior1);
            index = index - 1;
            if (index >= 0) {
                //na poczatku prawo, potem lewo
                root.right = createPOSTORDER(arr,prior1.priority,max);
                root.left = createPOSTORDER(arr,min, prior1.priority);
            }
        }
        //zwraca referencje do korzenia calego drzewa
        return root;
    }
////////////////////////////////////////////////////////////////////////////////////
//iteracyjna, Zl. O(n)
boolean DELETE(int priority) {
    if(root == null) return false; //if bst puste
    N = N - 1;
    Node t = root;
    Node prev = null;
    while (t != null && t.info.priority != priority){ //szukamy nuzhny element
        prev = t;
        if(priority < t.info.priority) t = t.left; //jezeli mniej to po lewym boku
        else t = t.right;
    }
    if(t == null){ //if w drzewie nie ma tego elementu
        N = N + 1;
        return false;
    }
    else { //jezeli istnieje
        ///////////////////////////////////////////////////////////////
        if(t.left != null && t.right != null){ //if ma dwa potomka, szukamy min element w prawym subtree
            //search next element
            Node next = t.right;
            Node prev_next = t;
            while (next.left != null) {
                prev_next = next;
                next = next.left;
            }
            t.info = next.info; //zamieniamy element z jego next elementem
            if(prev_next == t) {
                if(next.right != null) prev_next.right = next.right;
                else prev_next.right = null;
            }
            else {
                if(next.right != null) prev_next.left = next.right;
                else prev_next.left = null;
            }
        }
        ///////////////////////////////////////////////////////////////
        else if( t.left != null && t.right == null){ //if tylko lewy potomek
            if(prev == null) root = root.left;//jezeli korzen
            else {
                if (prev.info.priority < priority) prev.right = t.left;
                else prev.left = t.left;
            }
        }
        ///////////////////////////////////////////////////////////////
        else if(t.left == null && t.right != null){ //if tylko prawy
            if(prev == null) root = root.right;//jezeli korzen
            else{
                if (prev.info.priority < priority)  prev.right = t.right;
                else prev.left = t.right;
            }
        }
        ///////////////////////////////////////////////////////////////
        else{ //if element na koncu(nie ma potomkow)
            if(prev == null) root = null; //jezeli korzen
            else{
                if ( prev.info.priority > priority) prev.left = null;
                else prev.right = null;
            }
        }
        ///////////////////////////////////////////////////////////////
        //zwraca true jezeli element zostal usuniety
        return true;
    }
}
////////////////////////////////////////////////////////////////////////////////////
static class Stack<T> { // standardowy stak
    private class Node {
        T data;
        Node prev;
        public Node(T x, Node _prev) {
            data = x;
            prev = _prev;
        }
    }
    private Node top;
    public Stack() {
        top = null;
    }
    void push(T x) {
        if (top == null) top = new Node(x, null);
        else top = new Node(x, top);
    }
    T pop() {
        T temp = top.data;
        top = top.prev;
        return temp;
    }
    boolean empty() {
        return top == null;
    }
}
////////////////////////////////////////////////////////////////////////////////////
private void PREORDER() { // (Root, Left, Right)
    if (root == null) return;
    Stack<Node> s = new Stack<>();
    s.push(root);
    int counter = 0;
    while (!s.empty()) { //Zl. O(n)
        // Pop the top item from stack and print
        Node x = s.pop();
        if (s.empty() && counter == N - 1 ) System.out.print( x.info.priority + " - " + x.info.name + " " + x.info.surname);
        else System.out.print( x.info.priority + " - " + x.info.name + " " + x.info.surname + ", ");
        // Push right and left children of the popped node to stack
        if (x.right != null) s.push(x.right);
        if (x.left != null) s.push(x.left);
        counter++;
    }
}
////////////////////////////////////////////////////////////////////////////////////
public void INORDER(int ilosc) { // (Left, Root, Right)
    Stack<Node> s = new Stack<>();
    Node t = root;
    int counter = 0;
    while (t != null || !s.empty()) { // Zl. O(n)
        if (t !=  null) { // reach the left most Node of the t Node
            s.push(t);
            t = t.left;
        } else { //potem po prawej
            t = s.pop();
            if (counter + 1 == N) System.out.print( t.info.priority + " - " + t.info.name + " " + t.info.surname);
            else System.out.print( t.info.priority + " - " + t.info.name + " " + t.info.surname + ", ");
            t = t.right; // the node and its left subtree was visited, now is right subtree's turn
            counter++;
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////
private void POSTORDER() { // (Left, Right, Root)
    if (root == null) return;
    // create pusty s(stak) and push root node
    Stack<Node> s = new Stack<>();
    s.push(root);
    // create stack dla wynika
    Stack<Node> out = new Stack<>();
    while (!s.empty()) { //Zl. O(n), jedna petla
        //Pop the top item and push it to output stack
        Node t = s.pop();
        out.push(t);
        // Push left and right children of the popped node to stack
        if (t.left != null)  s.push(t.left);
        if (t.right != null)  s.push(t.right);
    }
    //Print postorder
    //W tej metodzie mamy 2 while, ale oni nie przecinaja sie, wiec zostaje O(n)
    while (!out.empty()) {
        Node x = out.pop();
        if (x == root) System.out.print( x.info.priority + " - " + x.info.name + " " + x.info.surname);
        else System.out.print( x.info.priority + " - " + x.info.name + " " + x.info.surname + ", ");
    }
}
////////////////////////////////////////////////////////////////////////////////////
    public void print(String order) { //metoda print
        System.out.print(order + ": ");
        switch (order) {
            case "PREORDER":
                PREORDER();
                break;
            case "POSTORDER":
                POSTORDER();
                break;
        }
    }
////////////////////////////////////////////////////////////////////////////////////
    int height(Node t) { // linear - O(n), recursive, szuka wysokosc
        if (t == null) return 0;
        else {
            int l = height(t.left);  // height of left  subtree
            int r = height(t.right); // height of right subtree
            return ( l >= r ? l : r ) + 1; // return height of bigger subtree (increased by 1)
        }
    }
////////////////////////////////////////////////////////////////////////////////////
private Node min(Node t) { //Zl. O(n)
    while (t.left != null) {
        t = t.left;
    }
    return t;
}
//////////////////////////////////////////////////////////////////////////////////////
public Node next(int x) {
    Node t = root;
    Node next = null;
    while (t != null) { //Zl. O(n)
        if (t.info.priority == x) return t.right != null ? min(t.right) : next;
        else if (t.info.priority < x) t = t.right;
        else {
            next = t;
            t = t.left;
        }
    }
    if (t == null) return null; //jezeli takiego elementa nie ma
    return next;
}
////////////////////////////////////////////////////////////////////////////////////
private Node max(Node t) { //Zl. O(n)
    while (t.right != null) {
        t = t.right;
    }
    return t;
}
////////////////////////////////////////////////////////////////////////////////////
public Node prev(int x) {
    Node t = root;
    Node prev = null;
    while (t != null) { //Zl. O(n)
        if (t.info.priority == x) return t.left != null ? max(t.left) : prev;
        else if (t.info.priority < x) {
            prev = t;
            t = t.right;
        } else t = t.left;
    }
    if (t == null) return null; //jezeli takiego elementa nie ma
    return prev;
}
////////////////////////////////////////////////////////////////////////////////////
    public void insert(Person x) {
        N = N + 1;
        Node s = new Node(x);
        if (root == null) { //jezeli nie ma do tego elementow
            root = s;
            return;
        }
        Node t = root;
        Node p = null;
        while (t != null) { //szukamy odpowiednie miejsce; Zl. O(n)
            p = t;
            t = x.priority < t.info.priority ? t.left : t.right;
        }
        if (x.priority < p.info.priority) p.left = s;
        else if (x.priority > p.info.priority) p.right = s;
    }
////////////////////////////////////////////////////////////////////////////////////
Person MAX(){
    if(root == null) return null; //if pusto
    if(root.right == null) { //if nie ma po prawej stronie wezlow, wtedy korzen MAX
        N = N - 1;
        Node x = root;
        root = root.left;
        return x.info;
    }
    else {
        N = N - 1;
        Node curr = root;
        Node prev = root;
        while (curr.right != null) { //poszuk last el w prawej czesci
            prev = curr;
            curr = curr.right;
        }
        Node x = curr;
        prev.right = curr.left; //przepinamy wsk
        return x.info;
    }
}
////////////////////////////////////////////////////////////////////////////////////
    Person MIN(){
        if (root == null) return null; //if pusto
        if(root.left == null){ //if nie ma po lewej stronie wezlow, wtedy korzen MIN
            N = N - 1;
            Node x = root;
            root = root.right;
            return x.info;
        }
        else {
            N = N - 1;
            Node curr = root;
            Node prev = root;
            while (curr.left != null) { //find last el w lewej czesci
                prev = curr;
                curr = curr.left;
            }
            Node elem = curr;
            prev.left = curr.right;//przepinamy wsk
            return elem.info;
        }
    }
////////////////////////////////////////////////////////////////////////////////////
}
class Source {
    public static void main(String[] args) {  //MAIN
        Scanner in = new Scanner(System.in);
        Tree bst = new Tree();
        Node temp = null;
        int Z = Integer.parseInt(in.nextLine());
        int n;
        for (int z = 0; z < Z; z++) {
            System.out.println("ZESTAW " + (z+1));
            int command = Integer.parseInt(in.nextLine());
            for (int k = 0; k < command; k++) {
                String[] cmd = in.nextLine().split(" ");
                switch (cmd[0]) {
                    case "CREATE":
                        n = Integer.parseInt(cmd[2]); //ile bedzie elementow
                        Person [] arr = new Person[n]; //new tabelka takiego rozmiaru
                        int zakres = 3;
                        for(int i = 0; i < n; i++){
                            int prioritet = Integer.parseInt(cmd[zakres]);//3 6 9
                            String name = cmd[zakres + 1];                //4 7 10
                            String surname = cmd[zakres + 2];             //5 8 11
                            Person person = new Person(prioritet,name,surname);
                            arr[i] = person; //w naszej tabelke bedzie Person
                            zakres += 3;
                        }
                        bst.N = n; //ilosc elementow w drzewie jak n
                        if( bst.root != null) bst.root = null; //root == null, t.j. create new one
                        if(cmd[1].equals("POSTORDER")) { //left,right,node
                            bst.index = n - 1; // z konca
                            bst.root = bst.createPOSTORDER(arr,-1,1000000);
                        }
                        else { //PREORDER //node,left,right
                            bst.index = 0; //od poczatku
                            bst.root = bst.createPREODER(arr,n,-1,1000000);
                        }
                        break;
                    case "DELETE":
                        if (!bst.DELETE(Integer.parseInt(cmd[1]))) System.out.println(cmd[0] + " " + cmd[1] + ": BRAK");
                        break;
                    case "PREORDER":
                        if(bst.root == null) System.out.println("PREORDER: ");
                        bst.print("PREORDER");
                        System.out.println();
                        break;
                    case "POSTORDER":
                        if(bst.root==null) System.out.println("POSTORDER: ");
                        bst.print("POSTORDER");
                        System.out.println();
                        break;
                    case "INORDER":
                        if(bst.root==null) System.out.println("INORDER: ");
                        System.out.print("INORDER: ");
                        bst.INORDER(bst.N);
                        System.out.println();
                        break;
                    case "HEIGHT":
                        if(bst.root == null) System.out.println("HEIGHT: ");
                        System.out.println("HEIGHT: " + (bst.height(bst.root) - 1));
                        break;
                    case "NEXT":
                        n = Integer.parseInt(cmd[1]);
                        temp = bst.next(n);
                        System.out.print("NEXT " + cmd[1] + ": ");
                        if (temp != null) System.out.println(temp.info.priority + " - " + temp.info.name + " " + temp.info.surname);
                        else System.out.println("BRAK");
                        break;
                    case "PREV":
                        n = Integer.parseInt(cmd[1]);
                        temp = bst.prev(n);
                        System.out.print("PREV " + cmd[1] + ": ");
                        if (temp != null) System.out.println(temp.info.priority + " - " + temp.info.name + " " + temp.info.surname);
                        else System.out.println("BRAK");
                        break;
                    case "ENQUE":
                        int priority= Integer.parseInt(cmd[1]);
                        String name= cmd[2];
                        String surname= cmd[3];
                        Person osoba = new Person(priority,name,surname);
                        bst.insert(osoba);
                        break;
                    case "DEQUEMAX":
                        Person osoba1 = bst.MAX();
                        if(bst.root == null) System.out.println("DEQUEMAX: ");
                        else System.out.println("DEQUEMAX: " + osoba1.priority + " - " + osoba1.name + " " + osoba1.surname);
                        break;
                    case "DEQUEMIN":
                        Person osoba2 = bst.MIN();
                        if(bst.root == null) System.out.println("DEQUEMIN: ");
                        else System.out.println("DEQUEMIN: " + osoba2.priority + " - " + osoba2.name + " " + osoba2.surname);
                        break;
                }
            }
        }
    }
}
/*
Test

IN:
2
5
CREATE POSTORDER 5 34 Anna Nowik 56 Inna Kulic 1 Ola Tarkowska 29 Gocha Buler 100 Sofia Miruk
HEIGHT
INORDER
DELETE 29
PREORDER
5
CREATE PREORDER 5 34 Anna Nowik 56 Inna Kulic 1 Ola Tarkowska 29 Gocha Buler 100 Sofia Miruk
NEXT 100
PREV 34
ENQUE 45 Filip Serek
POSTORDER

Out:

ZESTAW 1
HEIGHT: 2
INORDER: 1 - Ola Tarkowska, 29 - Gocha Buler, 100 - Sofia Miruk
PREORDER: 100 - Sofia Miruk, 1 - Ola Tarkowska
ZESTAW 2
NEXT 100: BRAK
PREV 34: BRAK
POSTORDER: 45 - Filip Serek, 56 - Inna Kulic, 34 - Anna Nowik
*/
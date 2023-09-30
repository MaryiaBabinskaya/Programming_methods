import java.util.ArrayList;
import java.util.HashMap;

class Node {
    String word;
    ArrayList<Node> neighbours;

    public Node(String word) {
        this.word = word;
        neighbours = new ArrayList<Node>();
    }

    public String getWord() {
        return word;
    }

    public void AddNeighbour(Node neighbour) {
        neighbours.add(neighbour);
    }

    public void Print() {
        System.out.print("Node: " + word + ", neighbours: ");
        for (Node neighbour : neighbours) {
            System.out.print(neighbour.word + ", ");
        }
        System.out.println();
    }
}

/*
 * Podstawy, wyświetlanie (2)
 * Możliwość dodawania krawędzi (ze sprawdzeniem warunku) (3 - testy)
 * Możliwośc automatycznego dodawania słów (3 - testy)
 * Sprawdzanie okręgu (3 - testy)
 * Kod się kompuiluje, wyjaśnione działanie, złożoność czasową metod (2)
 */

//////// START ////////
class Graph {
    ArrayList<Node> list;

    public Graph() { //konstruktor
        list = new ArrayList<>();
    }

    public void Print() { //O(n^2)
        // wypisuje slowo, potem jego sasiadow
        for (Node n : list) {
            System.out.print(n.getWord() + ":");
            for (Node adj : n.neighbours) {
                System.out.print(" " + adj.word);
            }
            System.out.println();
        }
    }

    public void AddEdge(Node nodeFrom, Node nodeTo) { //dodajemy krawedz, O(1)(poprostu wstawiamy)
//        if(!list.contains(nodeTo))
//            list.add(nodeTo);
//        if(!list.contains(nodeFrom))
//            list.add(nodeFrom);
//        nodeFrom.AddNeighbour(nodeTo);
        nodeFrom.AddNeighbour(nodeTo);
        nodeTo.AddNeighbour(nodeFrom);
    }

    public void AddWord(String word) { //dodajemy slowo, Zl. O(n), chociaz mam
        for (Node n : list) { //jezeli juz mamu takie slowo
            if (n.word.equals(word)) {
                return;
            }
        }
        Node n = new Node(word);
        char n_first = n.word.charAt(0);
        char n_last = n.word.charAt(n.word.length() - 1);
//               ArrayList<Node> copy = (ArrayList<Node>) list.clone();
        for (Node adj : list) { //O(n) - idziemy po sasiadach, sprawdzamy warunek, jezeli warunek dziala dodajemy edge
            char adj_first = adj.word.charAt(0);
            char adj_last = adj.word.charAt(adj.word.length() - 1);
            if (n_last == adj_first) {
                AddEdge(n, adj);
            }
            if (n_first == adj_last) {
                AddEdge(n, adj);
            }
        }
        list.add(n);
    }

    public Boolean HasCircle() {
        boolean is_visited[] = new boolean[list.size()];
        HashMap<Node, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        for (int i = 0; i < list.size(); i++) {
            if (dfs(i, is_visited, map))
                return true;
        }
        return false;
    }
    private boolean dfs(int current, boolean[] is_visited, HashMap<Node, Integer> map){
        if (is_visited[current]) return true;
        is_visited[current] = true;
        for (Node adj: list.get(current).neighbours){
            if (dfs(map.get(adj), is_visited, map)){
                return true;
            }
        }
        is_visited[current] = false;
        return false;
    }
}
//////// STOP ////////

public class WordGraph {
    public static void main(String[] args) {

        TestEmpty();
        TestEdgeCircle();
        TestEdgeNoCircle();
        TestWordCircle();
        TestWordNoCircle();

    }

    static void TestEmpty() {

        Graph graph = new Graph();
        graph.Print();
        System.out.println("TestEmpty: FALSE vs " + graph.HasCircle());
    }

    ///////////////////

    static void TestEdgeCircle() {

        Graph graph = new Graph();
        Node node1 = new Node("hej");
        Node node2 = new Node("ja");
        Node node3 = new Node("ah");

        graph.AddEdge(node1, node2);
        graph.AddEdge(node2, node3);
        graph.AddEdge(node3, node1);

        graph.Print();
        System.out.println("TestEdgeCircle: TRUE vs " + graph.HasCircle());
    }

    static void TestEdgeNoCircle() {

        Graph graph = new Graph();
        Node node1 = new Node("hej");
        Node node2 = new Node("ja");

        graph.AddEdge(node1, node2);

        graph.Print();
        System.out.println("TestEdgeNoCircle FALSE vs " + graph.HasCircle());
    }

    ///////////////////

    static void TestWordCircle() {

        Graph graph = new Graph();
        graph.AddWord("auto");
        graph.AddWord("ola");
        graph.AddWord("ania");
        graph.AddWord("olaf");
        graph.AddWord("fiat");

        graph.Print();
        System.out.println("TestWordCircle: TRUE vs " + graph.HasCircle());
    }

    static void TestWordNoCircle() {

        Graph graph = new Graph();
        graph.AddWord("ja");
        graph.AddWord("ty");

        graph.Print();
        System.out.println("TestWordNoCircle: FALSE vs " + graph.HasCircle());
    }

}
import java.util.ArrayList;

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
    // KOD TUTAJ

    public Graph() {
        // KOD TUTAJ
    }
    public void Print() {
        // KOD TUTAJ
    }

    public void AddEdge(Node nodeFrom, Node nodeTo) {
        // KOD TUTAJ
    }

    public void AddWord(String word) {
        // KOD TUTAJ
    }

    public Boolean HasCircle() {
        // KOD TUTAJ
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
        // graph.Print();
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

        // graph.Print();
        System.out.println("TestEdgeCircle: TRUE vs " + graph.HasCircle());
    }

    static void TestEdgeNoCircle() {

        Graph graph = new Graph();
        Node node1 = new Node("hej");
        Node node2 = new Node("ja");

        graph.AddEdge(node1, node2);

        // graph.Print();
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

        // graph.Print();
        System.out.println("TestWordCircle: TRUE vs " + graph.HasCircle());
    }

    static void TestWordNoCircle() {

        Graph graph = new Graph();
        graph.AddWord("ja");
        graph.AddWord("ty");

        // graph.Print();
        System.out.println("TestWordNoCircle: FALSE vs " + graph.HasCircle());
    }

}

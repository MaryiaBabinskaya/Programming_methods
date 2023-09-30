//Maryia Babinskaya 1
import java.util.Scanner;
class Source {
    ///////////////////////////////////////////////////////////////////////////
    public static class List { // lista pociagow
        class Node { //wezel sklada sie z name pociagu, next (kolejny pociag), listy wagonow
            String name;
            Node next;
            ListWithHead wagony = new ListWithHead();
            Node(String _name) { //konstruktor
                name = _name;
                next = null;
            }
            public void display() { //wypisujemy nazwe pociagu, potem nazwe jego wagonow
                System.out.print(name + ": ");
                wagony.display();
            }
        }
        Node first;
        Node dodatkowy_prew;
        List() { first = null; }
        void displayNode() { //wypisujemy
            System.out.print("Trains: ");
            Node temp = first;
            while (temp != null) {
                System.out.print(temp.name + " ");
                temp = temp.next; //idziemy po wezlach do null
            }
            System.out.println("");
        }
        void insertNode(String x) {  //wstawiamy nowy node z nazwa x
            Node temp = new Node(x);
            if (first != null) { //jezeli mamy first, to wstawiamy dalej
                temp.next = first;
            }
            first = temp; //jezeli do tego nie bylo first to temp robimy pierwszym
        }
        void deleteNode(Node X2) { //wyrzucamy jakis node
            if (X2 == first) first = X2.next; //jezeli X pierszy, to po prostu pierszy stanowi sie nastepny wezel
            else {
                Node NodeToDelete = poszukNode(X2.name);
                dodatkowy_prew.next = X2.next;
            }
        }
        Node poszukNode(String x) {
            Node temp = first;
            Node tempPozadi = first;
            while (temp != null) {
                if (temp.name.equals(x)) {
                    dodatkowy_prew = tempPozadi;
                    return temp;
                }
                else {
                    tempPozadi = temp;
                    temp = temp.next;
                }
            }
            return tempPozadi;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public static class ListWithHead { //lista wagonow
        class Node { //wezel w liscie wagonow
            String name;
            Node next, prev;
            public Node(String _name) { //konstruktor
                name = _name;
                next = prev = null;
            }
        }
        Node first, last, current;
        ListWithHead() { //konstruktor
            first = new Node("");
            first.next = null;
            current = first;
        }
        void insertFirst(String x) { //wstawiamy pierszy wagon
            Node temp = new Node(x);
            temp.next = first.next;
            if (first.next != null) first.next.prev = temp;
            first.next = temp;
        }
        void insert(String x) { //wstawiamy po prostu
            Node temp = new Node(x);
            if (current != null) {
                temp.prev = current;
                temp.next = current.next;
                current.next = temp;
            }
            else first.next = temp; //jezeli nie mamy jeszcze bezacego(bo wtedy nie mamy tego pierwszego)
            if (last == null) last = temp; //jezeli nie mamy jeszcze last node, to robimy temp
            current = temp; //tez robimy bezacym
        }
        void insertLast(String x) { //wstawiamy ostatni wagon
            Node temp = new Node(x);
            last.next = temp;  // przesuwamy wskazniki
            temp.prev = last;
            last = temp; // potem przypisujemy
        }
        String DelFirst() { //wyrzucamy pierwszy
            if (first.next != null) {
                Node temp = first.next; // trzeba temp node wyrzucic
                if (temp.next != null)  temp.next.prev = first;
                first.next = temp.next;
                return temp.name;
            } else return ""; //jezeli nie mamy pierwszego
        }
        String DelLast() { //wyrzucamy ostatni
            if (last != null) {
                Node temp = last;
                if (last == first) { //kiedy mamy tolko jeden wezel
                    last = null;
                    first.next = null;
                    return temp.name;
                } else {
                    last = temp.prev;
                    last.next = null;
                    return temp.name;
                }
            } else return ""; //jezeli nie mamy ostatniego
        }
        void display() { //wypisujemy
            Node temp = first.next;
            while (temp != null) { //idziemy do null i wypisujemy
                System.out.print(temp.name + " ");
                temp = temp.next;
            }
            System.out.println("");
        }
        void reverse() { //odwraca kolejnosc wagonow
           Node temp = first.next;
            while (temp != null) { //idzie dokonca
                Node swap = temp.next;
                temp.next = temp.prev;
                temp.prev = swap;
                temp = temp.prev;
            }
            first.next.next = null;
            last.prev = first;
            temp = first.next;
            first.next = last;
            last = temp;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int liczba_zestawow_danych = Integer.parseInt(in.nextLine());
        for (int q = 0; q < liczba_zestawow_danych; q++) {
            List trains = new List(); //lista naszych pociagow
            int liczba_operacji = Integer.parseInt(in.nextLine());
            for (int q1 = 0; q1 < liczba_operacji; q1++) {
                String funkcja[] = in.nextLine().split(" ");
                List.Node train; // lista pocigow
                if (funkcja.length == 1) trains.displayNode(); //funkcja trains
                else {
                    train = trains.poszukNode(funkcja[1]); // sprawdza czy jest taki pocziag
                    boolean trainExist = (train != null && train.name.equals(funkcja[1]));
                    if (funkcja[0].equals("New")) {
                        if (trainExist) System.out.println("Train " + funkcja[1] + " already exists"); //jezeli istnieje
                        else { //jezeli nie, robi insert
                            trains.insertNode(funkcja[1]);
                            trains.first.wagony.insert(funkcja[2]);
                        }
                    } else if (trainExist) { //jezeli poiag istnieje
                        switch (funkcja[0]) {
                            case "InsertFirst": //wstawia wagon na poczatek
                                train.wagony.current = train.wagony.first;
                                train.wagony.insertFirst(funkcja[2]);
                                break;
                            case "InsertLast": //wstawia wagon na koniec
                                train.wagony.current = train.wagony.last;
                                train.wagony.insertLast(funkcja[2]);
                                break;
                            case "Display": //wypisuje
                                train.display();
                                break;
                            case "Reverse": //robimy reverse
                                train.wagony.reverse();
                                break;
                            default: //inne komandy gdzie potrzebujemy istnienie 2 pociagu
                                List.Node train2 = trains.poszukNode(funkcja[2]); //sprawdzamy czy istnieje
                                boolean trainExist2 = (train2 != null && train2.name.equals(funkcja[2]));
                                if (funkcja[0].equals("Union")) {
                                    if (trainExist2) {
                                        train.wagony.last.next = train2.wagony.first.next; //laczymy
                                        train.wagony.last.next.prev = train.wagony.last;
                                        train.wagony.last = train2.wagony.last;
                                        trains.deleteNode(train2);
                                    } else System.out.println("Train " + funkcja[2] + " does not exist");
                                } else if (!trainExist2) { //jezeli nie istnieje 2 pocziag
                                    trains.insertNode(funkcja[2]);
                                    String cos;
                                    switch (funkcja[0]) {
                                        case "DelFirst":
                                            cos = train.wagony.DelFirst();
                                            if (cos.length() > 0) {
                                                trains.first.wagony.insert(cos);
                                            }
                                            break;
                                        case "DelLast":
                                            cos = train.wagony.DelLast();
                                            if (cos.length() > 0) {
                                                trains.first.wagony.insert(cos);
                                            }
                                            break;
                                    }
                                    if (train.wagony.first.next == null) trains.deleteNode(train);
                                } else System.out.println("Train " + funkcja[2] + " already exists");
                        }
                    } else  System.out.println("Train " + funkcja[1] + " does not exist"); //jezeli pociag nie istnieje
                }
            }
        }
    }
}
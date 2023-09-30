public class Main {
    public static void main (String [] args){

        Vector first = new Vector(5);
        Vector second = new Vector(7,5);

        first.display();
        second.display();

        first.toString();
        second.toString();

        first.insert(6);
        second.insert(28);

        first.display();
        second.display();

        first.remove(0);
        second.remove(28);

        first.display();
        second.display();

        first.at(4);
        first.at(0);
        second.at(1);
    }
}

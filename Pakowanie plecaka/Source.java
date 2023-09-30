// Maryia Babinskaya 1
import java.util.Scanner;
class Source {
    public static Scanner in = new Scanner(System.in);
    //////////STOS///////////////////
    static class Link {
        public int znak;
        public Link next;
        public Link(int d) {
            znak = d;
        }
    }
    static class LinkStack {
        public Link top;
        public int size;
        public LinkStack() {
            top = null;
            size = 0;
        }
        //Wstawianie na poczatek
        public void push(int d) {
            Link newLink = new Link(d);
            newLink.next = top;
            top = newLink;
            size++;
        }
        public boolean isEmpty() {
            return (top == null);
        }
        public int size() {
            int theSize=size;
            return theSize;
        }
        //usuwa element ze szczytu stosa
        public int pop() {
            Link temp = top;
            top = top.next;
            if(size>0) size--;
            return temp.znak;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    static boolean rec_pakuj(int co_szukamy, int i, int n, int arr[], LinkStack stack) {
        if (i == n) { //jezeli koniec tablicy
            return false;
        }
        if (arr[i] < co_szukamy) {
            boolean curr = rec_pakuj(co_szukamy - arr[i], i + 1, n, arr, stack); //szukamy potrzebna liczbe
            if (!curr) { // jezeli nie istnieja
                return rec_pakuj(co_szukamy, i + 1, n, arr, stack); //paczynamy znow z drugiego i
            } else {
                stack.push(arr[i]); //dodaje do stosu
                return true;
            }
        } else if (arr[i] > co_szukamy) { //bierzemy nast element
            return rec_pakuj(co_szukamy, i + 1, n, arr, stack);
        } else //jezeli rowna sie
            stack.push(arr[i]);//dodaje do stosu
        return true;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    static boolean iter_pakuj(int co_szukamy, int n, int arr[], LinkStack stack){
        LinkStack stack1 = new LinkStack();
        int i = 0;
        while(true){
            if( i < n ) {
                if (arr[i] <= co_szukamy) {
                    stack.push(arr[i]);
                    stack1.push(i);
                    if (arr[i] == co_szukamy)  return true;
                    co_szukamy -= arr[i];
                    i++;
                }
                else i++;
            } else {
                if(i == n && stack1.size == n && co_szukamy > 0 ) return false;
                else if(i == n && co_szukamy > 0 && stack.size()!=0 &&  stack1.size()!=0){
                     co_szukamy+= stack.pop();
                     i = stack1.pop() + 1;
                }
                else return false;
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int Zestaw = in.nextInt();
        for (int z = 0; z < Zestaw; z++) {
            int co_szukamy = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = in.nextInt();
            }
            LinkStack stack = new LinkStack();
//////////////////////////////////////////////////////////////////////////////////////////////////////
            if (rec_pakuj(co_szukamy, 0, n, arr, stack) == false) {
                System.out.println("BRAK");
            } else {
                String res = new String();
                while (!stack.isEmpty()) {
                    res += stack.pop() + " ";
                }
                System.out.println("REC: " + co_szukamy + " = " + res);
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////
            if (iter_pakuj(co_szukamy, n, arr, stack) == true) {
                String res1 = new String();
                while (!stack.isEmpty()) {
                    res1 = stack.pop() + " " + res1;
                }
                System.out.println("ITER: " + co_szukamy + " = " + res1);
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}

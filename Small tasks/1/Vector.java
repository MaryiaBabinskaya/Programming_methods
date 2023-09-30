import java.sql.SQLOutput;
import java.util.*;

class Vector {

    private int[] a; // referencja do wektora
    private int maxSize; // maksymalna długość wektora
    private int n; // aktualna długość wektora

    public Vector(int m) { // konstruktor
        maxSize = m;
        n = 0;
        a = new int[maxSize];
    }

    // Dopisz:

    // konstruktor przyjmujący maksymalny rozmiar "m" i wypełniający wektor
    // "l" losowymi wartościami z przedziału 0-100 (proszę znaleźć jak losować w
    // Internecie)
    public Vector(int m, int l) {
        maxSize = m;
        n = l;
        a = new int[maxSize];
        for(int i = 0; i < l; i++){
            a [i] = (int)(Math.random() * 100) ;
        }
    }

    // wyświetl wektor na ekran
    public void display() {
        System.out.print("Wektor:" );
      for(int i = 0; i < maxSize; i++ ) {
          System.out.print(" " + a[i]);
      }
      System.out.println(" ");
    }

    // zwróć Stringa z kolejnymi wartościami z wektora rozdzielonymi spacją
    @Override
    public String toString() {
        System.out.println(Arrays.toString(a) + " ");
       return "";
    }

    // wstaw "x" na koniec wektora (uwaga na maksymalny rozmiar)
    public void insert(int x) {
        a[maxSize-1] = x;
    }

    // usuń z wektora wszystkie wystąpienia liczby "x"
    public void remove(int x) {
        int l = 0, j = 0;
        for(int i = 0; i < maxSize; i++){
             if(a[i] == x){
                l++;                         //ile razy powtorzaja x w tablice
             }
         }
        int [] newA = new int[maxSize - l]; //nowa tabelka
        for(int i = 0; i < maxSize; i++){
            if(a[i] != x){
                newA[j] = a[i];             //wpisujemy wszystko oprocz x
                j++;
            }
        }
        maxSize = maxSize - l;
        for(int i = 0; i < maxSize; i++){
                a[i] = newA[i];
        }
       /* System.out.print("Wektor:" );
        for(int i = 0; i < maxSize-l; i++ ) {
            System.out.print(" " + newA[i]);
        }
        System.out.println(" "); */
    }

    // zwraca element o podanym indeksie (uwaga na nieprawidłowe indeksy)
    public int at(int i) {
        if( i>=maxSize ){
            System.out.println("Mistake!");
        }
        else
        System.out.println("Na indeksie " + i + " stoi element " + a[i] + ".");
        return 0;
    }
    // Stwórz w funkcji main prezentację wszystkich funkcji

}
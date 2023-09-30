//Maryia Babinskaya 1
import java.util.Scanner;
class Wezel{
    int liczba;
    int wiersz;
    int kolumna;
    public Wezel(int _liczba, int _wiersz, int _kolumna) {
        liczba = _liczba;
        wiersz = _wiersz;
        kolumna = _kolumna;
    }
}
class Source{
    public static Scanner in = new Scanner(System.in);
    static Wezel [] arr_of_el; //array liczb ktore znajduja sie w kopcu
    static int rozmiar; //rozmiar kopca
    public Source(Wezel arr[], int _rozmiar) { //konstruktor
        arr_of_el = arr;
        rozmiar = _rozmiar;
        for (int i = (rozmiar-1)/2; i >= 0; i--) { //tworzenie kopca w tablicy arr
            downheap(i);
        }
    }
    static void downheap(int i) { //jak w skrypcie, wstawianie do listy
        int j;
        Wezel tmp = arr_of_el[i];
        while ( i < rozmiar / 2 ) {
            j = 2 * i + 1; // indeks lewego nastepnika arr[i]
            if ( j < rozmiar - 1 && arr_of_el[j].liczba > arr_of_el[j+1].liczba ) j++;
            // wybierz wiekszy z nastepnikow
            // arr[j] wiekszy z nastepnikow wezla arr[i]
            if (tmp.liczba <= arr_of_el[j].liczba ) break; // warunek kopca OK
            arr_of_el[i] = arr_of_el[j]; //przesun aktualny element do gory
            i = j ;
        }
        arr_of_el[i] = tmp;
    }
     static void merge(int[][] arr) {
        Wezel[] kopiec = new Wezel[arr.length];
        int size_wyniku = 0;
        for(int i = 0; i < arr.length; i++) {
            Wezel node = new Wezel(arr[i][0],i,1);
            kopiec[i] = node;
            size_wyniku += arr[i].length;
        }
         //Create a min_heap with arr.length heap nodes
        Source min_heap = new Source(kopiec, arr.length);
        int[] wynik = new int[size_wyniku]; //nasz output
// Now one by one get the min element from min heap and replace it with next element of its array
        for(int i = 0; i < size_wyniku; i++) {
            //bierzemy min i wrzucamy w wynik
            Wezel root = min_heap.arr_of_el[0];
            wynik[i] = root.liczba;
            //szukamy nast el
            if(root.kolumna < arr[root.wiersz].length)
                root.liczba = arr[root.wiersz][root.kolumna++];
            else //jezeli root ostatni
                root.liczba = Integer.MAX_VALUE;
            //przesuwamy min el
            min_heap.arr_of_el[0] = root;
            downheap(0);
        }
        for(int i = 0; i < size_wyniku; i++){ //wypisujemy result
            System.out.print(wynik[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int Zestaw = in.nextInt();
        for (int z = 0; z < Zestaw; ++z) { //ZESTAW
            int n = in.nextInt(); //Ilosc ciagow
            int[] arr1 = new int[n]; //pierwsza tabelka(ile el w kazdym ciagu)
            for (int i = 0; i < n; i++) arr1[i] = in.nextInt();
            int tabelka[][] = new int[n][]; //dwuwym tab, ktora przechowuje el z tych ciagow
            for (int q0 = 0; q0 < n; q0++) {
                tabelka[q0] = new int[arr1[q0]];
                for (int q1 = 0; q1 < arr1[q0]; q1++) {
                    tabelka[q0][q1] = in.nextInt();
                }
            }
            merge(tabelka);
        }
    }
}
/*
Test:
2
3
5 6 2
15 6 3 4 23
2 7 45 90 3 2
56 34
4
2 6 1 4
1 6
6 0 5 65 33 11
7
12 1212 3 65
Out:
2 7 15 6 3 4 23 45 56 34 90 3 2 
1 6 6 0 5 7 12 65 33 11 1212 3 65
 */
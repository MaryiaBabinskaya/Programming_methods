// Maryia Babinskaya 1
import java.util.Scanner;
class Source {
    public static Scanner in = new Scanner(System.in);
    // Worst time of insertion sort is O(n^2)
    // Dzieki algorytmu mediana median (magiczne piatki) tego minujemy
    // Rowniez w przypadku pesymistycznym mamy O(n)
    static void InsertionSort(int[] arr, int I, int n) {
        //InsertionSort jak w skrypcie
        for (int i = I + 1; i < n; i++) {
            int tmp = arr[i];
            int j = i - 1;
            while (j >= I && tmp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
    }
    // jedna petla, O(n)
    static void Partition(int[] arr, int liczba, int left, int right) {
        int  i = left, L = left, R = right-1;
        while (i <= R) {
            if (arr[i] < liczba) {
                swap(i, L, arr);
                L++;
                i++;
            } else if (arr[i] > liczba) {
                swap(i, R, arr);
                R--;
            } else i++; //kiedy ==
        }
    }
    // Magiczne piatki, O(n)
    //Select zrobiony na podstawie wykladu
    //Nie uzywam dodatkowej pamieci
    static int SelectMethod(int[] arr, int k, int left, int right) {
        if (right - left <= 1) return arr[left];
        int counter_medians = 0; //dla dalniejszej pracy w tabelkie
        for (int i = left; i < right; i += 5) { //dzieli na piatki
            int n;
            if ( i + 5 < right) n = i + 5; //ustawia n - ilosc elementow
            else n = right;
            InsertionSort(arr, i, n); //sortujemy kazdy zbior
            swap(left + counter_medians, (n + i)/2, arr); //mediany sa przesuwane na poczatek tablicy
            counter_medians++; //liczy ile median
        }
        //Wyznacz nowy zbior Q = {srodkowych elementow z kazdego podzbioru (median) } - mamy na poczatku tablicy wszystkie mediany
        //Wyznacz rekurencyjnie M = Select2(Q, |Q|/2) (rekurencyjnie, oblicza mediane)
        int M = SelectMethod(arr, (counter_medians + 1)/2, left, left + counter_medians); // mediana median bedzie naszym pivot
        Partition(arr, M, left, right); //znow dzielimy
        int x = left; //ustawiamy x na samy poczatek tablicy
        while (arr[x] < M) x++; //x++ dopoki mniej naszego M
        int S1 = x - 1; // ostatni element ktory mniejszy od M
        while (x < right && arr[x] == M) x++; //x++ dopoki rowna naszemu M
        int S2 = x - 1; // ostatni M
        if (k <= S1 - left) return SelectMethod(arr, k, left, S1+1); // szukany element jest k-tym elementem w S1
        else if (k <= S2 - left) return M; // szukany jest w S2, czyli rowny M
        else return SelectMethod(arr, k - (S2 - left + 1), S2 + 1, right); // szukaj w S3, numer k - (S2 - L + 1) (odpowiednio mniejszy)
    }
    static void swap(int x, int y, int[] array) { //swap dla elementow
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    public static void main(String[] args) {
        int Zestaw = in.nextInt();
        for (int z = 0; z < Zestaw; ++z) {
            int n = in.nextInt();
            int [] arr = new int[n];
            for (int i = 0; i < n; i++)  arr[i] = in.nextInt();
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                int k = in.nextInt();
                System.out.print(k + " ");
                if (k > 0 && k <= n) System.out.println(SelectMethod(arr, k-1, 0, n));
                else System.out.println("brak");
            }
        }
    }
}
//Test:

//In

//5
//-----
//100
//100 99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51 50 49 48 47 46 45 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
//5
//5 2 76 32 4
//-----
//5
//5 6 7 12 5
//3
//1 9 3
//-----
//5
//1 2 3 4 5
//3
//1 2 3
//-----
//4
//1 1 1 1
//2
//6 3
//-----
//7
//56 24 12 6 -1 4 2
//4
//1 4 5 2
//-----

//Out
//-----
//5 5
//2 2
//76 76
//32 32
//4 4
//-----
//1 5
//9 brak
//3 6
//-----
//1 1
//2 2
//3 3
//-----
//6 brak
//3 1
//-----
//1 -1
//4 6
//5 12
//2 2
//-----
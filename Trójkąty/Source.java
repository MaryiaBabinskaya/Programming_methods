//Maryia Babinskaya gr 1
import java.util.Scanner;
public class Source {

    public static Scanner sc = new Scanner(System.in);
//////////////////////////////////////////////////////////////////////////////////////////
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;    //jezeli juz nie ma co dzielic
        // wybierz element oporny
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // podzielic na podtablice, ktore sa wieksze i mniejsze niz element oporny
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) { //zmieniamy miejscami
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // wywolamy rekursje, aby posortowac lewa i prawa strone
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
//////////////////////////////////////////////////////////////////////////////////////////
    static int binarySearch (int arr[], int begin, int end, int x) {
        while (end >= begin && end < arr.length) {
            int mid = (begin + end) / 2;  // szukamy serduszko tablicy
            if (arr[mid] >= x)         // jezeli x jest mniejszy/rowny to rozpatrzymy poczatek
                end = mid - 1;
            else
                begin = mid + 1;       // odwrotnie
        }
        return begin;
    }
//////////////////////////////////////////////////////////////////////////////////////////
    public static void main( String [] args ) {

        short ilosc_zestawow_danych = sc.nextByte();

        for (short zestaw = 0; zestaw < ilosc_zestawow_danych; zestaw++) {   // tyle razy wpisujemy tablice

            byte liczbe_elementow = sc.nextByte();

            int[] arr = new int[liczbe_elementow];

            for (short q1 = 0; q1 < liczbe_elementow; q1++) {  // zapolnili tablice
                arr[q1] = sc.nextShort();
            }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            quickSort(arr, 0, liczbe_elementow - 1); //otsortowali tablice
            int count = 0;                                   // ile istniejacych trojkatow

            System.out.println((zestaw + 1) + ": n= " + liczbe_elementow);

            int nuzhno = 1;
                for (int q1 = 0; q1 < arr.length; q1++) {
                    System.out.print(arr[q1] + " ");
                    if (nuzhno != arr.length && nuzhno % 25 == 0) {
                        System.out.print('\n');
                    }
                    nuzhno++;
                }
            System.out.print('\n');
            int counter_ilosci = 0;

            for (int firstI = 0; firstI < arr.length - 2; firstI++) {
                int k = firstI + 2; // poczatek(begin) tablicy dla poszukiwania

                for (int secondI = firstI + 1; secondI < arr.length - 1; secondI++) {
                    int dodatnia_czesc = arr[firstI] + arr[secondI];
                    k = binarySearch(arr, k, arr.length - 1, dodatnia_czesc); // arr, begin, end, x
                    // k - indeks w tablice, z ktorego ida niepasujace elementy
                    count += k - secondI - 1; // ile trojkatow mozna stworzyc z powyzszego przedzialu


                    if (count > 0) {
                        for (int i = firstI + 2; i < k; i++) {
                            if (secondI < i && counter_ilosci < 10) {
                                System.out.print("(" + firstI + "," + secondI + "," + i + ") ");
                                counter_ilosci++;
                            }
                        }
                    }

                }
            }
                if (count == 0) {
                    System.out.println("Triangles cannot be built ");
                } else {
                    System.out.print('\n');
                    System.out.println("Number of triangles: " + count);
                }

            }
        }
    }

//  O(n^2(log2n))
//  n^2 - 2 for
//  log2n - poszukiwanie binarne
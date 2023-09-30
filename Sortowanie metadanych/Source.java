//Maryia Babinskaya gr 1
import java.util.Scanner;
class Source {
    public static Scanner in = new Scanner(System.in);
////////////////////////////////////////////////////
    //SelectionSort dla zadan mniej niz 5 elementow
public static void SelectionSort(String arr[][], int ile_zestatow, int ile_kolumn, int porzadek) {
    char c = arr[0][0].charAt(0); //patrzyma jaki 1-szy element
    if ( c >= 48 && c <= 57) { //jezeli int
        for (int i = 0; i < ile_zestatow; i++) {
            int min = i;
            for (int j = i + 1; j < ile_zestatow; j++) {
                if (porzadek == 1) { //rosnaco
                    if (Integer.parseInt(arr[j][0]) < Integer.parseInt(arr[min][0])) //porownywamy
                        min = j;
                } else { //malejaco
                    if (Integer.parseInt(arr[j][0]) > Integer.parseInt(arr[min][0])) //porownywamy
                        min = j;
                }
            }
            swap(min, i, arr, ile_kolumn); //robimy zamiane
        }
    } else { //jezeli slowy
        //robimy takie same jak dla int, tylko uzywmy compareTo
        for(int i = 0; i < ile_zestatow; i++){
            int min = i;
            for(int j = i+1; j < ile_zestatow; j++){
                if( porzadek == 1 ){
                    if(arr[j][0].compareTo(arr[min][0]) < 0)
                        min = j;
                } else {
                    if(arr[j][0].compareTo(arr[min][0]) > 0)
                        min = j;
                }
            }
            swap(min,i,arr,ile_kolumn);
        }
    }
}
////////////////////////////////////////////////////
    //QuickSort bez rekurencji i uzycia stosu
public static void QuickSort(int left, int right, String arr[][], int ile_kolumn, int jak_sortujemy) {
    char c = arr[0][0].charAt(0); //sprawdzamy 1-szy element
        int i = 0;
        while (left < right || i > 0) {
            if (left < right) {
                int q;
                if ( c >= 48 && c <= 57 ) q = Partition1(left, right, arr, ile_kolumn, jak_sortujemy);//jezeli int
                else q = Partition2(left, right, arr, ile_kolumn, jak_sortujemy); //jezeli slowy
                //dodajemy na poczatek ostatniego elementa "-"
                String minus = "-";
                minus = minus + arr[right][0];
                arr[right][0] = minus;
                right = q - 1;
                i++;
            } else {
                left = right + 2;
                right = left;
                while (right < arr.length)
                    if (arr[right][0].substring(0, 1).equals("-")){
                        arr[right][0] = arr[right][0].substring(1);//usuwamy "-"
                        break;
                    } else right++;
                i--;
            }
        }
}
    static int Partition1(int left, int right, String arr[][],int ile_kolumn, int jak_sortujemy) { //dla liczb
        int x = Integer.parseInt(arr[left][0]);
        int i = left;
        if(jak_sortujemy == 1) {
            for (int j = left + 1; j <= right; j++) {
                if (Integer.parseInt(arr[j][0]) <= x) {
                    i = i + 1;
                    swap(i, j, arr, ile_kolumn);
                }
            }
        } else {
            for (int j = left + 1; j <= right; j++) {
                if (Integer.parseInt(arr[j][0]) >= x) {
                    i = i + 1;
                    swap(i, j, arr, ile_kolumn);
                }
            }
        }
        swap(i, left, arr, ile_kolumn);
        return i;
    }
    static int Partition2(int left, int right, String arr[][],int ile_kolumn, int jak_sortujemy) { //dla liczb
        int i = left;
        if(jak_sortujemy == 1) {
            for (int j = left + 1; j <= right; j++) {
                if (arr[j][0].compareTo(arr[left][0]) < 0) {
                    i = i + 1;
                    swap(i, j, arr, ile_kolumn);
                }
            }
        } else {
            for (int j = left + 1; j <= right; j++) {
                if (arr[j][0].compareTo(arr[left][0]) > 0) {
                    i = i + 1;
                    swap(i, j, arr, ile_kolumn);
                }
            }
        }
        swap(i, left, arr, ile_kolumn);
        return i;
    }
////////////////////////////////////////////////////
    static void swap(int a, int b, String[][] arr, int ile_kolumn) { //swap dla wierszow
    String [] temp = new String [ile_kolumn];
    for(int q = 0; q < ile_kolumn; q++) temp[q] = arr[a][q];
    for(int q = 0; q < ile_kolumn; q++) arr[a][q] = arr[b][q];
    for(int q = 0; q < ile_kolumn; q++) arr[b][q] = temp[q];
    }
///////////////////////////////////////////////////////
    public static void main(String[] args) {
        int Zestaw = Integer.parseInt(in.nextLine());
        for (int z = 0; z < Zestaw; z++) {

            String str = in.nextLine();
            String [] arr = str.split(","); //1 tabelka, z liczbami
            String i1 = arr[0];
            int ile_zestatow = Integer.parseInt (i1);
            String i2 = arr[1];
            int jaka_kolumna = Integer.parseInt (i2);
            String i3 = arr[2];
            int jak_sortujemy = Integer.parseInt (i3);

            String str1 = in.nextLine(); //np. Album,Year,Songs,Length
            String [] arr1 = str1.split(","); //kolonki
            int ile_kolonok = arr1.length;
                String temp1 = arr1[jaka_kolumna-1];
                int k1 = jaka_kolumna-1;
                while( k1 > 0 ){
                    arr1[k1] = arr1[k1-1];
                    k1--;
                }
                arr1[0] = temp1;


            String TABELKA [][] = new String [ile_zestatow][ile_kolonok]; //create new tab
            int w = 0;
            for(int q = 0; q < ile_zestatow; q++){
                String str2 = in.nextLine();
                String [] ARR1 = str2.split(",");
                for(int i = 0; i < ARR1.length; i++) {
                    TABELKA[w][i] = ARR1[i];
                }
                w++;
            }
            //wynosimy wybrana kolumne na poczatek
            for(int i = 0; i < ile_zestatow; i++){
                   String temp = TABELKA[i][jaka_kolumna-1];
                   int k = jaka_kolumna-1;
                   while( k > 0 ){
                       TABELKA[i][k] = TABELKA[i][k-1];
                       k--;
                   }
                   TABELKA[i][0] = temp;
            }

            if (ile_zestatow <= 5 ) SelectionSort(TABELKA,ile_zestatow,ile_kolonok,jak_sortujemy);
            else QuickSort(0,ile_zestatow-1,TABELKA,ile_kolonok,jak_sortujemy);

            for(int q1 = 0; q1 < ile_kolonok; q1++){
                System.out.print(arr1[q1]);
                if (q1 != ile_kolonok - 1 ) System.out.print(",");
            }
            System.out.println();
            for(int q = 0; q < ile_zestatow; q++){
                for(int q1 = 0; q1 < ile_kolonok; q1++){
                    if(TABELKA[q][q1].substring(0, 1).equals("-")) TABELKA[q][q1] = TABELKA[q][q1].substring(1);//usuwamy "-"
                    System.out.print(TABELKA[q][q1]);
                    if (q1 != ile_kolonok - 1 ) System.out.print(",");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
//Test
//4

//10,1,-1
//Book,Year,Autor,Ile_stron
//Dziady,1822,Adam Mickiewicz,288
//Nad Niemnem,1888,Eliza Orzeszkowa,622
//Czuly narrator,2020,Olga Tokarczuk,320
//Lalka,1889,Boleslaw Prus,676
//NIEZWYCIEZONY,1964,Stanislaw Lem,208
//Empuzjon,2022,Olga Tokarczuk,100
//Anna Karenina,1878,Lew Tolstoj,912
//Przeminelo z wiatrem,1936,Mitchell Margaret,576
//Duma i uprzedzenie,1813,Jane Austen,368
//Wichrowe Wzgorza,1847,Emily Jane Bronte,100

//3,2,1
//Album,Year,Songs,Length
//Stadium Arcadium,2006,28,122
//Unlimited Love,2022,17,73
//Californication,1999,15,56

//6,2,-1
//Food,Cena,Ranking
//Bigos,15,3
//Pierogi,9,5
//Chalwa,20,4
//Pizza,16,2
//Lody,4,6
//Pasta,17,1

//4,1,1
//Imie,Nastroj,Procent
//Tomek,5,75
//Kuba,2,90
//Ola,6,99
//Monika,7,14

//Out

//Book,Year,Autor,Ile_stron
//Wichrowe Wzgorza,1847,Emily Jane Bronte,100
//Przeminelo z wiatrem,1936,Mitchell Margaret,576
//Nad Niemnem,1888,Eliza Orzeszkowa,622
//NIEZWYCIEZONY,1964,Stanislaw Lem,208
//Lalka,1889,Boleslaw Prus,676
//Empuzjon,2022,Olga Tokarczuk,100
//Dziady,1822,Adam Mickiewicz,288
//Duma i uprzedzenie,1813,Jane Austen,368
//Czuly narrator,2020,Olga Tokarczuk,320
//Anna Karenina,1878,Lew Tolstoj,912

//Year,Album,Songs,Length
//1999,Californication,15,56
//2006,Stadium Arcadium,28,122
//2022,Unlimited Love,17,73

//Cena,Food,Ranking
//20,Chalwa,4
//17,Pasta,1
//16,Pizza,2
//15,Bigos,3
//9,Pierogi,5
//4,Lody,6

//Imie,Nastroj,Procent
//Kuba,2,90
//Monika,7,14
//Ola,6,99
//Tomek,5,75
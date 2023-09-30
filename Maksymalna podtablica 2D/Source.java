//Maryia Babinskaya gr 1
import java.util.Scanner;

class Source {

    public static Scanner sc = new Scanner(System.in);

    public static int[] kadan(int []arr1, int n){
        int[] res = new int[] {-100,0,-1};  // -1 dla ujemnych i zer
        int curSum = 0;                     // CurMaxSum, StartRow, EndRow
        int lst = 0;
        for(int i = 0; i < n; i++){
            curSum +=arr1[i];      // liczymy bezaca sume
            if(curSum <= 0){
                curSum = 0;
                lst = i + 1; // prenosimy na jedna kolumne dalej
            }
            else if((curSum > res[0]) || (curSum == res[0] && (i-lst) < (res[2]-res[1]))){ // jezeli wieksza, lub =( przy tym warunku)
                res[0] = curSum;
                res[1] = lst;
                res[2] = i;
            }
        }
        if (res[2] == -1) { // dla ujemnych i zer
            res[0] = arr1[0];
            res[1] = 0;
            res[2] = 0;
            for (int i = 1; i < n; i++) {  // jezeli znajdziemy element > niz suma
                if (arr1[i] > res[0]) {
                    res[0] = arr1[i];
                    res[1] = i;
                    res[2] = i;
                }
            }
        }
        // System.out.println("max_sum = " + res[0] + "  begin: " + res[1] + "  end:" + res[2] );
        return res;
    }


    public static void main(String[] args) {
        byte ilosc_zestawow_danych = sc.nextByte();

        for (byte zestaw = 0; zestaw < ilosc_zestawow_danych; zestaw++) {   // tyle razy wpisujemy tablice

            byte numer_zestawu = sc.nextByte();
            char ciag_znakow = sc.next().charAt(0); //dla :
            byte liczbe_wierszy = sc.nextByte();
            byte liczbe_kolumn = sc.nextByte();

            int[][] arr = new int[liczbe_wierszy][liczbe_kolumn];
            for (byte q = 0; q < liczbe_wierszy; q++) {           //napelnili tablice liczbami
                for (byte q1 = 0; q1 < liczbe_kolumn; q1++) {
                    arr[q][q1] = sc.nextShort();
                }
            }
            int [] cur;
            int MaxSum = -100;
            int left = 0;
            int top = 0;
            int right = 0;
            int bottom = 0;

            for (int topRow = 0; topRow < liczbe_wierszy; topRow++) {   //idziemy po wierszach
                int[] arr1 = new int[liczbe_kolumn];

                for (int bottomRow = topRow; bottomRow < liczbe_wierszy; bottomRow++) {

                    for (int i = 0; i < liczbe_kolumn; i++) {
                        arr1[i] += arr[bottomRow][i];
                    }
                    cur = kadan(arr1,liczbe_kolumn); // szuka MaxCurSum, pozycji na ktorych elementy stoja
                    if (cur[0] > MaxSum || (cur[0] == MaxSum && (bottom-top) > (bottomRow-topRow)) ) {
                        MaxSum = cur[0]; // cur[0] = CurMaxSum
                        top = topRow;
                        left = cur[1]; // cur[1] = startRow
                        bottom = bottomRow;
                        right = cur[2]; // cur[2] = endRow
                    }
                }
            }

            if (MaxSum < 0) System.out.println( numer_zestawu + ":" + " n = " + liczbe_wierszy + " m = " + liczbe_kolumn + ", s = 0, mst is empty");
            else System.out.println( numer_zestawu + ":" + " n = " + liczbe_wierszy + " m = " + liczbe_kolumn + ", s = " + MaxSum + ", mst = a[" + top + ".." + bottom + "][" + left + ".." + right + "]");
        }

    }

}
// Zawartosc O( (max(n, m))3 )
// 8
//1 : 4 4
//        -1 -1 100 100
//        -1 -1 100 100
//        100 100 -1 -1
//        100 100 -1 -1
//2 : 4 8
//        -1 2 -3 5 -4 -8 3 -3
//        2 -4 -6 -8 2 -5 4 1
//        3 -2 9 -9 3 6 -5 2
//        1 -3 5 -7 8 -2 2 -6
//3 : 4 4
//        8 2 1 1
//        2 4 1 1
//        1 1 -16 1
//        1 1 1 16
//4 : 4 4
//        -89 35 -67 -68
//        5 44 -21 -86
//        85 -32 -7 55
//        -55 55 -53 -29
//5 : 1 1
//        0
//6 : 5 1
//        0
//        1
//        2
//        3
//        4
//7 : 4 1
//        -5
//        0
//        20
//        -20
//8 : 4 4
//        0 0 0 1
//        0 0 0 0
//        0 0 0 0
//        0 0 0 0
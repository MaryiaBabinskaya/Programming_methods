//Maryia Babinskaya gr 1
import java.util.Scanner;
class Source {

    public static Scanner in = new Scanner(System.in);
       static public String result; //prefiks
       static public int count; //pomocnicza dla szukania prefiksa
       static void shuffleArray(String a[], int begin, int end) {

        if (end - begin == 1 || end - begin == 0) { //jezeli dwa elementy lub jeden
            //szukamy nasz prefiks
                int length = a[end].length();
                if (a[begin].length() < length) length = a[begin].length();
                if (result.length() < length) length = result.length();
                for (int x = length; x > 0; x--) {
                    if (result.regionMatches(0, a[begin], 0, x) && result.regionMatches(0, a[end], 0, x)) {
                        result = a[begin].substring(0, x);
                        break;
                    }
                    if( x==1 && result == a[0] && a.length != 1) count++;
                }
            return;
        }
        int mid = (begin + end) / 2; //szukamy middle
        int tempBegin2 = mid + 1; // temp dla swap pierszej cz. drugiej tablicy
        int tempBegin1 = (begin + mid) / 2; //midd dla swap drugiej cz. pierszej tablicy
        for (int i = tempBegin1 + 1; i <= mid; i++) { //swap
            // swap a[i], a[temp++]
            String temp1 = a[i];
            a[i] = a[tempBegin2];
            a[tempBegin2] = temp1;
            tempBegin2++;
        }
        //Rozpatrzymy 4 przypadki(ponizej)
        // Recursively doing for first half and second half
           // 4 8 16... parzysta_parzysta          (1)
           // 6 10 12... nieparzysta_nieparzysta   (2)
           // 5 9 11... nieparzysta_parzysta       (3)
           // 7... parzysta_nieparzysta            (4)
           ////////////1///////////
           if( (mid - begin + 1) % 2 == 0 && (end - mid) % 2 == 0) {
               shuffleArray(a, begin, mid);
               shuffleArray(a, mid + 1, end);
           }
           ////////////2///////////
           if( (mid - begin + 1) % 2 != 0 && (end - mid) % 2 != 0) {
               shuffleArray(a, begin, mid);
               shuffleArray(a, mid, end);
           }
           ////////////3///////////
           if( (mid - begin + 1) % 2 != 0 && (end - mid) % 2 == 0) {
               shuffleArray(a, begin, mid);
               shuffleArray(a, mid, end);
           }
           ////////////4///////////
           if( (mid - begin + 1) % 2 == 0 && (end - mid) % 2 != 0) {
               shuffleArray(a, begin, mid);
               shuffleArray(a, mid+1, end);
           }
    }

    public static void main(String[] args) {
        int Zestaw = Integer.parseInt(in.nextLine()); //ilosz zestawow
        for (int z = 0; z < Zestaw; z++) {
            int n = Integer.parseInt(in.nextLine());//ile piosenek
            String str = in.nextLine();
            String [] arr = str.split(" ");
            result = arr[0];
            count = 0;
            shuffleArray(arr,0,n-1);
            for(int q0 = 0; q0 < n; q0++)   System.out.print(arr[q0] + " ");
            System.out.println("");
            if( (result == arr[0] && arr.length != 1) || count > 0 ) System.out.println("");
            else System.out.println(result);
        }
    }
}
//Zlozonosc  O(N log (N)).
// Za kazdym razem dzieli tabelke na 2 czesci i "sortuje" - wiec log N
// mamy petle for dla swap - wiec jeszcze musimy * N

//Test

//8
//3
//a1 a2 b1
//4
//a1 a2 b1 b2
//5
//a1 a2 a3 b1 b2
//6
//a1 a2 a3 b1 b2 b3
//7
//a1 a2 a3 a4 b1 b2 b3
//8
//a1 a2 a3 a4 b1 b2 b3 b4
//9
//a1 a2 a3 a4 a5 b1 b2 b3 b4
//10
//a1 a2 a3 a4 a5 b1 b2 b3 b4 b5

//Out
//a1 b1 a2
//a1 b1 a2 b2
//a1 b1 a2 b2 a3
//a1 b1 a2 b2 a3 b3
//a1 b1 a2 b2 a3 b3 a4
//a1 b1 a2 b2 a3 b3 a4 b4
//a1 b1 a2 b2 a3 b3 a4 b4 a5
//a1 b1 a2 b2 a3 b3 a4 b4 a5 b5
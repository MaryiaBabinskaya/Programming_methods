public class Main {
    public static void main(String[] args) {

        int arr[] = new int[]{1,2,3};
/*
        //////////////////// n /////////////////////

        int L = 0;             //dopolnitelno
        int pierszyEl = 0;     //indeks poczatka
        int ostatniEl = 0;     //indeks konca
        int sum = arr[0];
        int SumMax = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (sum < 0){ //jezeli max podposledovatelnost w minuse to nie trzeba ja wlaczyc w sum
                L = i;
                sum = arr[i];
            }
            else
                sum += arr[i];

            if (sum > SumMax){   // choose SumMax
                pierszyEl = L;
                ostatniEl = i;
                SumMax = sum;
            }
        }

        if (pierszyEl==0 && ostatniEl==0 && SumMax < 0 )
            SumMax = 0;

        System.out.println("Najwieksza summa: " + SumMax + "." + " Podmassiv: (" + pierszyEl + " do " + ostatniEl + ").");

        /////////////////////////////////////////////
*/
/*
        //////////////////// n^2 /////////////////////

        int SumMax  = 0;
        int pierszyEl = 0;
        int ostatniEl = 0;
        for(int i = 0; i < arr.length; i++ ){
            int sum = 0;
         for (int  j = i; j < arr.length; j++) {
             sum += arr[j];
                 if (sum > SumMax) {
                    SumMax = sum;
                    pierszyEl = i;
                    ostatniEl = j;
                }
         }
        }
        System.out.println("Najwieksza summa: " + SumMax + "." + " Podmassiv: (" + pierszyEl + " do " + ostatniEl + ").");

        /////////////////////////////////////////////


*/
//     //////////////////// n^3 /////////////////////

        int pierszyEl = 0;
        int ostatniEl = 0;
        int SumMax = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum = sum + arr[k];
                    if (sum > SumMax) {
                        SumMax = sum;
                        pierszyEl = i;
                        ostatniEl = j;
                    }
                }
            }
        }
            System.out.println("Najwieksza summa: " + SumMax + "." + " Podmassiv: (" + pierszyEl + " do " + ostatniEl + ").");

            /////////////////////////////////////////////
//
    }
}
public class Zadanie_domowe4 {

//////////////////////////////////////////////////////////////////////////////////////
   public static void Sort(int arr[]){
        boolean swapped = true;
        int start = 0;
        int end = arr.length;
        while (swapped == true) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            start = start + 1;
        }
    }

    public static void main(String[] args){
        int my_arr[] = { 6, 8, 34, 21, 0, 1, 98, 64, 6};

        for (int i = 0; i < my_arr.length; i++)   // do sortowania
            System.out.print(my_arr[i] + " ");

        System.out.println();
        Sort(my_arr);

        for (int i = 0; i < my_arr.length; i++) // po sortowaniu
        System.out.print(my_arr[i] + " ");

    }

//    Sortowanie wstawiania binarnego wykorzystuje wyszukiwanie binarne,
//    aby znaleźć właściwą pozycję do wstawienia elementu pod określonym indeksem w
//    każdej iteracji. Najpierw znajduje się miejsce, w którym należy wstawić element.
//    Następnie elementy są przesuwane do następnej właściwej lokalizacji.
//    Teraz określony element jest umieszczany na pozycji.


//////////////////////////////////////////////////////////////////////////////////////

}

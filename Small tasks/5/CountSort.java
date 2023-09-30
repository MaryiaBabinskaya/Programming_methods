public class CountSort {
    public static int[] sort(int[] arr, int N) {
        int[] count = new int[10]; //positions
        int[] out   = new int[N];  //result

        for (int i = 0; i < N; i++) // liczy ile bedzie 1, 2, 3 i td
            count[arr[i]]++;
        //count [0,2,2,0,1,1,0,1,0,0]
        for (int i = 1; i <= N; i++) //idzie po tabelke i sumuje 0 i 1, 1 i 2, 2 i 3 i td do N
            count[i] += count[i-1];
        //count [0,2,4,4,5,6,6,7,0,0]
        for (int i = N-1; i >= 0; i--) {
            count[arr[i]]--; //odejmujemy 1 od "indeksa"
            //np. arr[6] to 2, wiec w count[2]--(bylo 4, stalo 3)
            out[count[arr[i]]] = arr[i]; //na pozycje tego counta wstawiamy element z pierszej nie otsortowanej tabelki
        }
        return out; //out[1,1,2,2,4,5,7]
    }

    public static void main(String args[]) {
        int N = 7; //ilosc elementow
        int [] a = new int [] {1,4,1,2,7,5,2};
        for(int i = 0; i < N; i++)
            System.out.print(a[i]+" ");
        System.out.println("");

        int[] b = sort(a, N);
        System.out.println();
        for(int i = 0; i < N; i++)
            System.out.print(b[i]+" ");
    }
}

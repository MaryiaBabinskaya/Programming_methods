class Hoarea {
    static int K = 3;
    static boolean printSorted = true;
    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    static int partition(int[] arr, int L, int R) {
        int pivot = arr[R];
        int i = L - 1;
        for (int j = L; j <= R- 1; ++j) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, R);
        return (i + 1);
    }
    public static int select(int[] arr, int L, int R, int k) {
        int partition = partition(arr, L, R);
        if (partition == k) {
            return arr[partition];
        } else if (partition < k ) {
            return select(arr, partition + 1, R, k);
        } else {
            return select(arr, L, partition-1, k);
        }
    }
    public static void main(String[] args) {
        int N = 10;
        int[] a = {12,54,67,3,9,10,8,123,64,44};


        System.out.print("Array:  ");
        for(int i = 0; i < N; i++)
            System.out.print(a[i]+" ");

        System.out.println("k-th element: " + select(a, 0, N-1, K-1));

        if (printSorted) {
            System.out.print("Sorted: ");
            for(int i = 0; i < N; i++)
                System.out.print(CountSort.sort(a,N)+" ");
        }
    }

}
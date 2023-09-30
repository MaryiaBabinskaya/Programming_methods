class HeapSort {
    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private static void downheap(int[] arr, int i, int n) {
        int temp = arr[i];
        while (i < n/2) {
            int j = 2 * i + 1;
            if (j < n-1 && arr[j] > arr[j+1]) { // 3 2 OK, po prostu przesuwamy index
                j++;
            }
            if (temp <= arr[j]) break; // Ten warunek nie taki jak w skrypcie
            arr[i] = arr[j];
            i = j;
        }
        arr[i] = temp;
    }
    public static void sort(int[] arr) {
        int n = arr.length;
        // faza 1: tworzenie kopca w tablicy a
        // jest to efektywna realizacja operacji construct
        for (int i = (n-1)/2; i >= 0; i--) {
            downheap(arr, i, n);
        }
        // faza 2: usuwanie z kopca
        while (n > 0) {
            n = n - 1;
            swap(arr, 0, n);
            downheap(arr, 0, n);
        }
    }
    public static void main(String[] args) {
        int N = 20;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int)(Math.random() * 60);
        }
        System.out.print("Before: ");
        printArray(arr);
        sort(arr);
        System.out.print("After:  ");
        printArray(arr);
    }
}
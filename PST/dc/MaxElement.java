package PST.dc;

public class MaxElement {
    public static int maxElement(int[] arr, int n) {
        if (n == arr.length) return 0;
        return Math.max(arr[n], maxElement(arr, n + 1));
    }
    public static int maxElement(int current,int[] arr) {
        if(current == -1) return 0;
        return Math.max(arr[current], maxElement(current - 1, arr));
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(maxElement(arr, 0));
        System.out.println(maxElement(new int[]{34,12,67,32,11}, 0));
        System.out.println(maxElement(new int[]{120,97,35,122}, 0));
        System.out.println(maxElement(arr.length-1,arr));
        System.out.println(maxElement(4,new int[]{34,12,67,32,11}));
        System.out.println(maxElement(3,new int[]{120,97,35,122}));
    }
}

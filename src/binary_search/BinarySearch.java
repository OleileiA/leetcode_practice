package binary_search;

public class BinarySearch {

    // 最标准的二分查找的模板
    public int std_bs(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 变体1，查找第一个等于target的值的index
    public static int bs2(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != target) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }
    // 变体2，查找最后一个等于target的值的index
    public static int bs3(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] != target) return mid;
                low = mid + 1;
            }
        }

        return -1;
    }

    // [1, 2, 4, 5]
    // target = 3

    // 变体3，查找第一个小于等于target的值的index
    public static int bs4(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= target) {
                if ((mid == a.length - 1) || a[mid + 1] > target) return mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    // 变体4，查找第一个大于等于target的值的index
    public static int bs5(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= target) {
                if (mid == 0 || a[mid - 1] < target) return mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 3, 4, 4, 4, 4, 5 };
        System.out.println(bs4(a, 2));
    }
}

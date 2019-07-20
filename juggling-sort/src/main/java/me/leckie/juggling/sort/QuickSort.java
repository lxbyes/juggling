package me.leckie.juggling.sort;

public class QuickSort {

  public static void quickSort(int[] nums, int start, int end) {
    if (start < end) {
      int i = start, j = end, base = nums[start];
      while (i < j) {
        while (i < j && nums[j] > base) {
          j--;
        }
        if (i < j) {
          nums[i++] = nums[j];
        }
        while (i < j && nums[i] < base) {
          i++;
        }
        if (i < j) {
          nums[j--] = nums[i];
        }
      }
      nums[i] = base;
      quickSort(nums, start, i - 1);
      quickSort(nums, i + 1, end);
    }
  }

  public static void main(String[] args) {
    int[] nums1 = {3, 7, 21, 8, 0, 4, 7};
    int[] nums2 = {5, 1, 38, 1, 19};
    int[] nums3 = {8, 999, 67, 32, 9874, 3333};
    quickSort(nums1, 0, nums1.length - 1);
    quickSort(nums2, 0, nums2.length - 1);
    quickSort(nums3, 0, nums3.length - 1);
    printArray(nums1);
    printArray(nums2);
    printArray(nums3);
  }

  public static void printArray(int[] array) {
    for (int n : array) {
      System.out.printf("%d\t", n);
    }
    System.out.println();
  }

}

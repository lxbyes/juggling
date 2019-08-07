package me.lceckie.juggling.box.some;

import java.util.Arrays;

/**
 * @author Leckie
 * @version HeapSort.java, v0.1 2019/8/4 11:46
 */
public class HeapSort {

  public static void buildHeap(int[] nums) {
    for (int i = nums.length / 2; i >= 0; i--) {
      adjust(nums, i, nums.length);
    }
  }

  public static void printHeap(int[] numsOrigin) {
    // int[] nums = new int[numsOrigin.length];
    // System.arraycopy(numsOrigin, 0, nums, 0, nums.length);
    int[] nums = Arrays.copyOf(numsOrigin, numsOrigin.length);
    for (int i = nums.length - 1; i >= 0; i--) {
      System.out.printf("%d\t", nums[0]);
      nums[0] = nums[i];
      adjust(nums, 0, i);
    }
  }

  public static void adjust(int[] nums, int index, int length) {
    int child = 2 * index + 1;
    if (child >= length) {
      return;
    }
    if (child + 1 < length && nums[child + 1] > nums[child]) {
      child++;
    }
    if (nums[child] > nums[index]) {
      int temp = nums[index];
      nums[index] = nums[child];
      nums[child] = temp;
      // 没有破坏平衡，不用调整
      adjust(nums, child, length);
    }
  }

  public static void main(String[] args) {
    int[] nums = {21, 32, 22, 1, 33, 24, 22, 51, 6, 34, 12, 90, 78, 98, 110, 97};
    buildHeap(nums);
    printHeap(nums);
  }

}

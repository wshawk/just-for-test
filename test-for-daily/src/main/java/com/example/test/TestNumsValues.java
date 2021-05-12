package com.example.test;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/4/19
 */
public class TestNumsValues {
    public static void main(String[] args) {
        // int[] names = {1,2,3};
        // int[] values = {1,2,1};
        // test(names, values);
        int[] a = new int[]{1,2,3,4};
        int[] b = new int[]{4,5,6,7};
        int maxValue = getMaxValue(a, b);
        System.out.println(maxValue);
    }
    public static void test(int[] names, int[] values){
        int sum = 0;
        for (int i=0,len=values.length; i<len; i++){
            sum +=values[i] * names[i];
        }
        System.out.println(sum);
    }
    /**
     * 招行笔试算法题：有两个长度均为n，的数组a，b，从a数组头或尾取出一个数，
     * 假设第i次取的值为ax,那么第i次的价值为b[i - 1]*ax，
     * 价值总和为前i-1次价值之和加上第i次的价值，求第n次取数最大价值总和
     * 知识点：双指针加动态规划
     * @param nums int整型一维数组
     * @param values int整型一维数组
     * @return int整型
     * ERROR: 错误
     */
    public static int getMaxValue (int[] nums, int[] values) {
        int dp[] = new int[nums.length + 1];
        int left = 0;
        int right = nums.length - 1;
        dp[0] = 0;
        int i = 1;
        for (int value : values) {
            if(left > right){
                return dp[nums.length];
            }
            if(nums[left] > nums[right]){
                dp[i] = dp[i - 1] + nums[left] * value;
                left++;
            } else{
                dp[i] = dp[i - 1] + nums[right] * value;
                right--;
            }
            i++;
        }
        return dp[nums.length];
    }
}

package com.example.review;

/**
 * @author hawk
 * @package com.example.review
 * @desc
 * @date 2021/4/16
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = {3, 6, 2, 4, 1, 65, 28, 15, 10};
        maoPao(arr);
    }

    private static void maoPao(int[] arr) {
        //  错误
        // for (int i=0,len=arr.length; i < len; i++){
        //     for (int j=0,jlen=arr.length; j<jlen; j++){
        //         if ( arr[i] > arr[i+1]){
        //             int temp = arr[i];
        //             arr[ i ] = arr[i+1];
        //             arr[i + 1] = temp;
        //         }
        //     }
        // }

        // 正确
        for (int i=0, len=arr.length; i<len; i++){
            for (int j=i+1; j<len; j++){
                if (arr[i] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        for (int i:arr){
            System.out.print(i+" ");
        }
    }
}

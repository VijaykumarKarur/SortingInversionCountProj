import java.util.Arrays;

public class Solution {
    final int MOD = 1000000007;
    public int mergeCount(int[] A, int start, int end, int mid){
        int[] leftArray = Arrays.copyOfRange(A, start, mid + 1);
        int[] rightArray = Arrays.copyOfRange(A, mid + 1, end + 1);
        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int arrayIndex = start;
        int swaps = 0;
        while(leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length){
            if(leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]){
                A[arrayIndex++] = leftArray[leftArrayIndex++];
            }
            else {
                A[arrayIndex++] = rightArray[rightArrayIndex++];
                swaps = ((swaps % MOD) + (((mid + 1) - (start + leftArrayIndex)) % MOD) % MOD);
            }
        }
        while(leftArrayIndex < leftArray.length){
            A[arrayIndex++] = leftArray[leftArrayIndex++];
        }
        while(rightArrayIndex < rightArray.length){
            A[arrayIndex++] = rightArray[rightArrayIndex++];
        }
        return swaps;
    }

    public int mergeSortCount(int[] A, int start, int end){
        int count = 0;
        if(start < end){
            int mid = start + (end - start) / 2;
            count = ((count % MOD) + mergeSortCount(A, start, mid) % MOD) % MOD;
            count = ((count % MOD) + mergeSortCount(A, mid + 1, end) % MOD) % MOD;
            count = ((count % MOD) + mergeCount(A, start, end, mid) % MOD) % MOD;
        }
        return count;
    }

    public int solve(int[] A) {
        return mergeSortCount(A, 0, A.length - 1);
    }
}

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] sum = new int[n][n];
        for(int i=0;i<n;i++){
            sum[n-1][i] = triangle.get(n-1).get(i);
        }
        for(int i = n-2;i>=0;i--)
            for(int j=0;j<=i;j++){
                sum[i][j] =triangle.get(i).get(j) + (sum[i+1][j] < sum[i+1][j+1]?sum[i+1][j]:sum[i+1][j+1]);
            }
        return sum[0][0];
    }
}
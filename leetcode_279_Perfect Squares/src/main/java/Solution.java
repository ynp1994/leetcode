class Solution {
    public int numSquares(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            memo[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            int limit = (int) Math.sqrt(i);
            for (int j = 1; j <= limit; j++) {
                memo[i] = Math.min(memo[i], 1 + memo[i - j * j]);
            }
        }

        return memo[n];
    }

    public int numDecodings(String s) {
        s = "0" + s;
        int len = s.length();
        int[] memo = new int[len];
        memo[0] = 1;
        for (int i = 1; i < len; i++) {
            int num = Integer.parseInt(s.substring(i - 1, i + 1));
            if (s.charAt(i) == '0') {
                if (num < 10 || num > 20)
                    return 0;
                memo[i] = memo[i - 2];
                memo[i - 1] = memo[i];
                continue;
            }
            if (num >= 11 && num <= 26)
                memo[i] = memo[i - 1] + memo[i - 2];
            else
                memo[i] = memo[i - 1];
        }
        return memo[len - 1];
    }

    public int uniquePaths(int m, int n) {
/*        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m - 1][n - 1];*/
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] = res[j] + res[j - 1];
            }
        }
        return res[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1)
            return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0)
                dp[i][0] = 1;
            else
                break;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0)
                dp[0][i] = 1;
            else
                break;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
    public int rob(int[] nums) {
/**rob1
 *  int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i]+dp[i-2], dp[i-1]);
        }
        return dp[n-1];
 */
//rob2
        int n = nums.length;
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1));
    }

    private int rob(int[] nums, int lo, int hi) {
        if(lo == hi)
            return nums[lo];
        int[] dp = new int[hi-lo + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < hi; i++) {
            dp[i] = Math.max(nums[i]+dp[i-2], dp[i-1]);
        }
        return dp[hi-1];
    }
}
package likou;

public class a2319checkXMatrix {
    public static void main(String[] args) {
        int[][] grid = {{2,0,0,1},{0,3,1,0},{0,5,2,0},{4,0,0,2}};
        System.out.println(checkXMatrix(grid));
    }

    public static boolean checkXMatrix(int[][] grid) {
        boolean res = true;
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j || (i+j) == len-1){
                    if (grid[i][j] == 0) return false;
                }else {
                    if (grid[i][j] != 0) return false;
                }
            }
        }
        return res;
    }
}

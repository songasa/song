package likou;

import java.util.Map;

public class a1828countPoints {
    public static void main(String[] args) {
        int[][] points = {{1,3},{3,3},{5,3},{2,2}};
        int[][] queries = {{2,3,1},{4,3,1},{1,1,2}};
        int[] ints = countPoints(points, queries);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        int tmp = 0;
        int c = 0;
        for (int[] query : queries) {
            for (int[] point : points) {
                if (Math.pow(query[2],2) >=
                    Math.pow(Math.abs(query[0]-point[0]),2) +
                    Math.pow(Math.abs(query[1]-point[1]),2)
                )tmp++;
            }
            res[c++] = tmp;
            tmp = 0;
        }
        return res;
    }
}

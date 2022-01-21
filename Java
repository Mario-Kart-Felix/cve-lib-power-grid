import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Application {
    private static int gridSerialNumber = 6042; // input
    private static int gridSize = 300;
    private static int[][] grid = new int[gridSize][gridSize];
    private static  HashMap<String,Long> totalPower = new HashMap<>();
    private static  HashMap<String,Long> totalPowerAnySize = new HashMap<>();

    private static int powerLevel(int x, int y) {
        int powerLevel;
        int rackId = x + 10;
        powerLevel = rackId * y;
        powerLevel = powerLevel + gridSerialNumber;
        powerLevel = powerLevel * rackId;
        powerLevel = (powerLevel / 100) % 10;
        powerLevel = powerLevel - 5;
        return powerLevel;
    }

    private static void initializeGrid() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                grid[y][x] = powerLevel(x,y);
                //System.out.print(grid[x][y] + " ");
            }
            //System.out.println();
        }
    }

    private static long returnSum(int x, int y, int size){
        // for me the size is 3x3 for example, but that means , that from the center to any edge is just an additional number
        long sum = 0;
        for(int j = y ; j < y + size  ; j++){
            for(int i = x; i < x + size; i++){
                sum = sum + grid[j][i];
            }
        }
        return sum;
    }


    private static void findLargestTotalPower(){
        for (int y = 0; y < gridSize - 2; y++) {
            for (int x = 0; x < gridSize - 2; x++) {
                totalPower.put(""+(x)+","+(y),returnSum(x,y,3));
            }
        }
        long max = Integer.MIN_VALUE;
        String keyAssociated = "";
        for (String key:totalPower.keySet()) {
            long power = totalPower.get(key);
            if(power > max){
                max = power;
                keyAssociated = key;
            }
        }
        // result part 1
        System.out.println(keyAssociated);
    }

    private static void findLargestTotalPowerSquareOfAnySize(){
        int size = 1;
        while (size != 300){
            for (int y = 0; y < gridSize -(size - 1); y++) {
                for (int x = 0; x < gridSize -(size - 1); x++) {
                    totalPowerAnySize.put(""+(x)+","+(y)+","+ size, returnSum(x,y,size));
                }
            }
            size++;
        }
        long max = Integer.MIN_VALUE;
        String keyAssociated = "";
        for (String key:totalPowerAnySize.keySet()) {
            long power = totalPowerAnySize.get(key);
            if(power > max){
                max = power;
                keyAssociated = key;
            }
        }
        System.out.println(keyAssociated);
    }

    public static void main(String[] args) {
        initializeGrid();
        findLargestTotalPower();
        findLargestTotalPowerSquareOfAnySize();
    }
}

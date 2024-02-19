import java.util.*;
import java.io.*;

class Main {
    /* To be loaded from StdIn using input(). */
    int height; // height of image
    int width; // width of image
    int blockHeight; // height of a tiling block
    int blockWidth; // width of a tiling block
    int[][] fReward; // reward for putting pixel in foreground
    int[][] bReward; // reward for putting pixel in background
    int[][] pBtwCols;   // pBtwCols[i][j] is separation penalty between pixel (i,j), (i,j+1)
                        // dimensions: height x (width - 1) 
    int[][] pBtwRows;   // pBtwRows[i][j] is separation penalty between pixel (i,j), (i+1,j)
                        // dimensions: (height-1) x (width)

    /* To be printed to StdOut using output() */
    boolean[][] foreground; // selects the pixels that will go in the foreground

    // Load input from StdIn.
    void input() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String[] hw = in.readLine().split("\\s+");
            height = Integer.parseInt(hw[0]);
            width = Integer.parseInt(hw[1]);
            String[] bhw = in.readLine().split("\\s+");
            blockHeight = Integer.parseInt(bhw[0]);
            blockWidth = Integer.parseInt(bhw[1]);
            fReward = new int[height][width];
            bReward = new int[height][width];
            pBtwCols = new int[height][width-1];
            pBtwRows = new int[height-1][width];
            // populate fReward
            for (int i = 0; i < height; i++) {
                String[] rewards = in.readLine().split("\\s+");
                for (int j = 0; j < width; j++) {
                    fReward[i][j] = Integer.parseInt(rewards[j]);
                }
            }
            // populate bReward
            for (int i = 0; i < height; i++) {
                String[] rewards = in.readLine().split("\\s+");
                for (int j = 0; j < width; j++) {
                    bReward[i][j] = Integer.parseInt(rewards[j]);
                }
            }
            // populate pBtwColsA
            for (int i = 0; i < height; i++) {
                String[] penalties = in.readLine().split("\\s+");
                for (int j = 0; j < width-1; j++) {
                    pBtwCols[i][j] = Integer.parseInt(penalties[j]);
                }
            }
            // populate pBtwRows
            for (int i = 0; i < height-1; i++) {
                String[] penalties = in.readLine().split("\\s+");
                for (int j = 0; j < width; j++) {
                    pBtwRows[i][j] = Integer.parseInt(penalties[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String arrToString(boolean[][] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                builder.append(arr[i][j]);
                if (j < arr[i].length-1) {
                    builder.append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // Print output to StdOut.
    void output() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            out.write(arrToString(foreground).replace("true","1").replace("false","0"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() {
        input();
        // YOUR CODE HERE
        for(int i=0; i<height/2;i++) {
        	for(int j=0; j<width; j++) {
        		forground[i][j]=true;
        	}
        }
        for(int i=height/2; i<height;i++) {
        	for(int j=0; j<width; j++) {
        		forground[i][j]=false;
        	}
        }
        output();
    }

    public static void main(String[] args) {
        new Main();
    }
}

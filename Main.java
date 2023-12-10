import java.util.Scanner;

public class Main {
//    final int N = 5;
//    final int M=5;

    //to print the path in the form of matrix where all 1 represents a path from drone to target
    void printSolution(int sol[][],int N,int M)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(" " + sol[i][j] + " ");
            System.out.println();
        }
    }

    //Function to check the boundary conditions
    boolean isSafe(int grid[][], int x, int y)
    {
        // if (x, y outside maze) return false
        int N=grid.length;
        int M=grid[0].length;
        return (x >= 0 && x < N && y >= 0 && y < M && grid[x][y] == 1);
    }


    boolean finalPath(int grid[][],int i,int j,int xt,int yt)
    {


        int N=grid.length;
        int M=grid[0].length;
        int [][]sol=new int[N][M];
        for(int a=0;a<N;a++)
        {
            for(int b=0;b<M;b++)
            {
                sol[a][b]=0;
            }
        }

        if (find_Path(grid, i, j,xt,yt ,sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol,N,M);
        return true;
    }


    /*This function is performing BACKTRACK to find the path and
    then marking visited cells unvisited again so that we can use that path again*/
    boolean find_Path(int grid[][], int x, int y,
                      int xt,int yt,int[][]sol)
    {

        if (x == xt && y == yt) {
            sol[x][y] = 1;
            return true;
        }


        if (isSafe(grid, x, y) == true) {
            // mark x, y as part of solution path
            sol[x][y] = 1;

            /* Move forward in 8 direction */
            if (find_Path(grid, x + 1, y,xt,yt ,sol)||find_Path(grid, x + 1, y+1,xt,yt ,sol)||find_Path(grid, x + 1, y+1,xt,yt ,sol)||find_Path(grid, x, y + 1,xt,yt, sol) ||find_Path(grid, x - 1, y,xt,yt ,sol)||
                    find_Path(grid, x, y-1,xt,yt ,sol)||find_Path(grid, x - 1, y-1,xt,yt ,sol)||find_Path(grid, x + 1, y+1,xt,yt ,sol)||find_Path(grid, x + 1, y-1,xt,yt ,sol)||find_Path(grid, x - 1, y+1,xt,yt ,sol))
                return true;

            /* If moving in x direction doesn't give
               solution then  Move down in y direction */

            /* If none of the above movements works then
               BACKTRACK: unmark x, y as part of solution
               path */
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    //considering 0 that are drones as blockers 1 as a free route
    public static void main(String args[])
    {
        Main path = new Main();

//        int grid[][] = { { 1, 0, 1, 1 },
//                { 1, 1, 1, 0 },
//                { 1, 1, 0, 1 },
//                { 0, 1, 1, 1 } };

        Main m=new Main();
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter number of rows- ");
        int row= sc.nextInt();
        System.out.print("Enter number of columns- ");
        int col= sc.nextInt();


        int [][]grid=new int[row][col];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = 1;
            }
        }
        int [][]drones=new int [4][4];
        for(int i=0;i<4;i++)
        {
            System.out.print("Enter Coordinates x&y: ");
            for(int j=0;j<2;j++)
            {

                drones[i][j]=sc.nextInt();

            }
        }
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 2; j++) {
                int a=drones[i][0];
                int b=drones[i][1];
                grid[a][b]=0;
            }
        }

        //array that basically stores the coordinates for the four drones
//        int [][]d=new int [4][2];
//        d[0][0]=2;
//        d[0][1]=2;
//        d[1][0]=1;
//        d[1][1]=3;
//        d[2][0]=0;
//        d[2][1]=1;
//        d[3][0]=3;
//        d[3][1]=0;

//        int xt,yt;  //target coordinates
//        xt=2;
//        yt=3;
        System.out.print("Enter target values a & b: ");
        int xt=sc.nextInt();
        int yt=sc.nextInt();

        for(int i=0;i<4;i++)
        {
            int a=drones[i][0];
            int b=drones[i][1];
            // System.out.print(a+ " ");
            // System.out.print(b+ " ");
            // rat.solveMaze(maze);
            System.out.println("The path of the drone on "+a+" "+b+" "+(i+1)+" represented by 1s in the grid");
            grid[a][b]=1;
            path.finalPath(grid,a,b,xt,yt);
            System.out.println();
        }


    }
}
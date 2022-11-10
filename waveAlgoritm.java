// Волновой алгоритм

import java.util.*;
 

public class waveAlgoritm {
    static int ROW = 5;
    static int COL = 5;
    
    static class Cell
    {
        int x;
        int y;
    
        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    };
    
    
    static class queueNode
    {
        Cell pt; 
        int dist;
    
        public queueNode(Cell pt, int dist)
        {
            this.pt = pt;
            this.dist = dist;
        }
    };
    
    
    static boolean checkValid(int row, int col)
    {
        return ((row >= 0) && (row < ROW) && (col >= 0) && (col < COL));
    }
    
    
    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};
    
    
    static int bfsLee(int mat[][], Cell src, Cell dest)
    {
        if (mat[src.x][src.y] != 1 || mat[dest.x][dest.y] != 1)
            return -1;
    
        boolean [][]visited = new boolean[ROW][COL];        
        visited[src.x][src.y] = true;
    
        Queue<queueNode> q = new LinkedList<>();        
        queueNode s = new queueNode(src, 0);
        q.add(s);    
       
        while (!q.isEmpty())
        {
            queueNode curr = q.peek();
            Cell pt = curr.pt;
    
            if (pt.x == dest.x && pt.y == dest.y)
                return curr.dist;
            q.remove();
            
            for (int i = 0; i < 4; i++)
            {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];
                
                if (checkValid(row, col) && 
                        mat[row][col] == 1 && 
                        !visited[row][col])
                {
                    
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode
                                (new Cell(row, col),
                                    curr.dist + 1 );
                    q.add(Adjcell);
                }
            }
        }        
        
        return -1;
    }

   
    
    public static void main(String[] args) 
    {
        int matr[][] = {{ 1, 0, 1, 1, 1 },
                    { 1, 0, 1, 0, 1 },
                    { 1, 1, 1, 0, 1 },
                    { 0, 0, 0, 0, 1 },
                    { 1, 1, 1, 0, 1 }};

        Arrays.stream(matr).map(Arrays::toString).forEach(System.out::println);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter X coordinate of your source cell: ");
        int x1 = sc.nextInt();
        System.out.print("Enter Y coordinate of your source cell: ");
        int y1 = sc.nextInt();
        System.out.print("Enter X coordinate of your destination cell: ");
        int x2 = sc.nextInt();
        System.out.print("Enter Y coordinate of your destination cell: ");
        int y2 = sc.nextInt();
        sc.close();


        Cell start = new Cell(x1, y1);
        Cell end = new Cell(x2, y2);
    
        int dist = bfsLee(matr, start, end);
    
        if (dist != -1)
            System.out.println("Length of the shortest path: " + dist);
        else
            System.out.println("Shortest path doesn't exist");
        }    
}

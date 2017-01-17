package xyz.rocknet;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Grid grid = new Grid(5,5);
        //grid.setRandomHedges(5);
        grid.setBlock(new XY(1,1));
        grid.setBlock(new XY(1,2));
        grid.setBlock(new XY(1,3));
        grid.setBlock(new XY(2,1));
        grid.setBlock(new XY(2,3));
        grid.setBlock(new XY(3,3));
        grid.setBlock(new XY(3,4));
        grid.setBlock(new XY(4,1));
        grid.print("before");
        Path path = grid.getPathToEnd( new XY(0,0));
        grid.print("after");
        if( path != null) {
            System.out.format("path found in grid\n");
            for( XY step : path.toArray())
                System.out.format(" [%1$d,%2$d]", step.X, step.Y);
            System.out.format("\n");
        }
        else
            System.out.format("No path found in grid\n");
    }
}

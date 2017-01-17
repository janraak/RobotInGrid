package xyz.rocknet;

import java.util.Random;
import java.util.UUID;
import java.util.List;

/**
 * Created by Jan on 1/16/2017.
 */
public class Grid {
    private boolean[][] _Hedges;
    private boolean[][] _NoGo;
    private int _NoOfRows;
    private int _NoOfColumns;

    public Grid(int rows, int columns) {
        _NoOfRows = rows;
        _NoOfColumns = columns;
        _Hedges= new boolean[_NoOfRows][_NoOfColumns];
        _NoGo= new boolean[_NoOfRows][_NoOfColumns];
    }

    public void setRandomHedges(int i) {
        Random randomizer = new Random(UUID.randomUUID().getLeastSignificantBits());
        int noOfCells=_NoOfColumns*_NoOfRows;
        while (i-- > 0) {
            boolean hedgeSet=false;
            do {
                int point = Math.abs(randomizer.nextInt(noOfCells))% noOfCells;
                int row = point / _NoOfColumns;
                int column =point% _NoOfRows;
                if( row ==0&&column==0)
                    continue;;
                if( row >=(_NoOfRows-1)&& column>=(_NoOfColumns-1))
                    continue;;
                hedgeSet = _Hedges[row][column];
                _Hedges[row][column]=true;
                _NoGo[row][column]=true;
            }while( hedgeSet);
        }
    }

    public boolean setBlock(XY place){
        boolean oldValue = _Hedges[place.X][place.Y];
        _Hedges[place.X][place.Y]=true;
        _NoGo[place.X][place.Y]=true;
        return oldValue;

    }
    public Path getPathToEnd( XY place) {
        Path path = new Path();
        moveToEndOverRight(place,path);
        return path;
    }

    public boolean moveToEndOverDown(XY place, Path path) {
        System.out.format(" moveToEndOverDown %1$d:%2$d \t",place.X,place.Y );
        if (place.X == (_NoOfRows - 1) && place.Y == (_NoOfColumns - 1)){
            path.add(place);
            return true;
    }
        boolean q = canMove(place.down())&&moveToEndOverDown(place.down(), path);
        System.out.format("%1$d:%2$d %3$c\n",place.X,place.Y,q?'!':'-');
        if( q) {
            path.add(place);
            return true;
        }
        q = canMove(place.right())&&moveToEndOverRight(place.right(), path);
        System.out.format("%1$d:%2$d %3$c\n",place.X,place.Y,q?'!':'-');
        if( q) {
            path.add(place);
            return true;
        }
        markNoGo(place);
        return false;
    }

    public boolean moveToEndOverRight(XY place, Path path) {
        System.out.format(" moveToEndOverRight %1$d:%2$d \t",place.X,place.Y );
        if (place.X == (_NoOfRows - 1) && place.Y == (_NoOfColumns - 1)){
            path.add(place);
            return true;
        }
        boolean q = canMove(place.right())&&moveToEndOverDown(place.right(), path);
        System.out.format("%1$d:%2$d %3$c\n",place.X,place.Y,q?'!':'-');
        if( q) {
            path.add(place);
            return true;
        }
        q = canMove(place.down()) &&moveToEndOverRight(place.down(), path);
        System.out.format("%1$d:%2$d %3$c\n",place.X,place.Y,q?'!':'-');
        if( q) {
            path.add(place);
            return true;
        }
        markNoGo(place);
        return false;
    }

    private boolean canMove(XY cell) {
        if( cell.X < 0 || cell.Y < 0 || cell.X >= _NoOfRows || cell.Y>=_NoOfColumns )
            return false;
        return !_NoGo[cell.X][cell.Y];
    }

    private boolean markNoGo(XY cell) {
        if( cell.X < 0 || cell.Y < 0 || cell.X >= _NoOfRows || cell.Y>=_NoOfColumns )
            return false;
        boolean oldValue=_NoGo[cell.X][cell.Y];
        _NoGo[cell.X][cell.Y] = true;
        return oldValue;
    }

    public void print(String tag) {
        System.out.format("\n%1$s\n\n", tag);
        System.out.format("\t");
        for( int c=0;c<_NoOfColumns;++c)
            System.out.format("\t%1$d", c);
        System.out.format("\n");
        for( int r=0;r<_NoOfRows;++r) {
            System.out.format("%1$d\t",r);
            for( int c=0;c<_NoOfColumns;++c)
                System.out.format("\t%1$c",_NoGo[r][c]?'x':'.');
            System.out.format("\n");
        }
    }
}

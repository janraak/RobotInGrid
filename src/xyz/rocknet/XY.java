package xyz.rocknet;

/**
 * Created by Jan on 1/16/2017.
 */
public class XY {
    public final int X;
    public final int Y;

    public XY(int row, int column) {
        X=row;
        Y=column;
    }
    public XY right()
    {
        return new XY( X,1+Y);
    }
    public XY down()
    {
        return new XY( 1+X,Y);
    }
}

package xyz.rocknet;

import java.util.List;

/**
 * Created by Jan on 1/16/2017.
 */
public class Path  {
    private List<XY> _Steps;

    public List<XY> toArray()
    {
        return  _Steps;
    }
    public void add( XY position)
    {
        System.out.format("++%1$d:%2$d\n",position.X,position.Y);
        _Steps.add(0,position);
    }

    public Path(){
        _Steps=new java.util.ArrayList<XY>();

    }
}

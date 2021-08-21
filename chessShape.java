package by0820;

import java.awt.*;

/**
 * chessShape 类，按顺序储存放置棋子的顺序
 *
 * 属性
 * 1.index 放置棋子的顺序
 * 2.chessX 放置棋子的 x 坐标
 * 3.chessY 放置棋子的 Y 坐标
 * 4.color 放置棋子的颜色
 * 5.reback 棋子是否撤回    //只可以撤回上一步，不可以撤回多步 0 未撤回，1 撤回
 *
 * 构造方法
 * 1.chessShape(int index,int x,int y,Color cc,boolean reback) 创建对象包含 5个属性的设置
 *
 * 方法
 * 1.void setReback(boolean reback) 设置 reback属性
 * 2.int getIndex() 得到 Index 棋子放置序号
 * 3.int getChessX() 得到 chessX 棋子横坐标
 * 4.int getChessY() 得到 chessY 棋子纵坐标
 * 5.Color getColor() 得到棋子颜色
 * 6.boolean getReback() 得到棋子是否撤回状态
 */

public class chessShape {
    int index;
    int chessX;
    int chessY;
    Color color;
    boolean reback;
    public chessShape(int index,int x,int y,Color cc,boolean reback){
        this.index=index;
        this.chessX=x;
        this.chessY=y;
        this.color=cc;
        this.reback=reback;
    }

    public void setReback(boolean reback){this.reback = reback; }

    public  int getIndex(){return index; }

    public int getChessX(){ return chessX; }

    public int getChessY(){ return chessY; }

    public Color getColor(){ return color; }

    public boolean getReback(){ return reback; };
}

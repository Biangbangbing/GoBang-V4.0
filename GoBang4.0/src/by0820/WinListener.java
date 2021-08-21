package by0820;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * WinListener 获胜监听器 负责实现获胜界面按键功能
 *
 * 属性
 * 1.goBangUi 棋盘界面对象 BiangGoBangUI goBangUi
 * 2.winJf 获胜界面对象 JFrame winJf
 * 3.mylis 棋盘监听器对象 BiangGoBangListener mylis
 * 4.pen 画笔对象
 *
 * 重写的方法
 * 1.void actionPerformed(ActionEvent e) 重写 actionPerformed 方法实现获胜界面的按键功能 (再战一局 回顾棋局 退出游戏)
 * 2.
 *
 * 方法：
 * 1.void setGoBangUi(BiangGoBangUI goBangUi) 设置棋盘界面 传参
 * 2.void setWinJf(JFrame winJf)  设置获胜界面 传参
 * 3.void setMylis(BiangGoBangListener mylis) 设置棋盘界面监听器 传参
 * 4.void setGraphics(Graphics pen) 设置画笔传参
 *
 *
 */


public class WinListener implements ActionListener,BiangGoBangInterface{
    BiangGoBangUI goBangUi;
    JFrame winJf;
    BiangGoBangListener mylis;
    Graphics pen;

    public void setGoBangUi(BiangGoBangUI goBangUi){
        this.goBangUi = goBangUi;
    }

    public void setWinJf(JFrame winJf) {
        this.winJf = winJf;
    }

    public void setMylis(BiangGoBangListener mylis) {
        this.mylis = mylis;
    }

    public void setGraphics(Graphics pen) {
        this.pen = pen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnStr = e.getActionCommand();
        if(btnStr.equals("再战一局")){
            winJf.setVisible(false);
            mylis.countBlack=0;
            mylis.countWhite=0;
            mylis.countSum=0;
            mylis.controlColor=0;
            goBangUi.chessIndex.clear();
            goBangUi.initBiangGoBangUI();
        }
        else if(btnStr.equals("回顾棋局")){
            winJf.setVisible(false);
            for(int i = 0;i < ROW + 1;i++){
                for(int j = 0; j<LINE + 1;j++)
                    goBangUi.chesses[i][j]=0;
            }
            goBangUi.paint(goBangUi.getGraphics());
            //Graphics tempPen = goBangUi.getGraphics();
            //System.out.println(goBangUi.chessIndex.size());
            for(int i=0;i<goBangUi.chessIndex.size();i++){
                pen.setColor(goBangUi.chessIndex.get(i).getColor());
                //System.out.println("我要画了！");
                pen.fillOval(goBangUi.chessIndex.get(i).getChessX()-SIZE/2,goBangUi.chessIndex.get(i).getChessY()-SIZE/2,SIZE,SIZE);
                //延时1s 500ms

                try {
                    System.out.println("Start..." + new Date().toString());
                    // delay 1.5 seconds
                    Thread.sleep(1500);
                    System.out.println("End..." + new Date().toString());

                } catch (InterruptedException err) {
                    System.err.format("IOException: %s%n", err);
                }

                if(goBangUi.chessIndex.get(i).getReback()==true){
                    goBangUi.paint(goBangUi.getGraphics());
                    for(int j=0;j<i;j++){
                        pen.setColor(goBangUi.chessIndex.get(j).getColor());
                        pen.fillOval(goBangUi.chessIndex.get(j).getChessX()-SIZE/2,goBangUi.chessIndex.get(j).getChessY()-SIZE/2,SIZE,SIZE);
                    }
                }
//                pen.setColor(Color.WHITE);
//                pen.fillRect(100,30,500,30);
//                pen.setColor(Color.BLACK);
//                pen.drawString("黑色棋子计数: "+countBlack,150,50);
//                pen.drawString("白色棋子计数: "+countWhite,450,50);


            }


            JOptionPane.showMessageDialog(null,"棋局回顾结束","回顾结束",1);
            if(goBangUi.chessIndex.get(goBangUi.chessIndex.size()-1).getColor().equals(Color.BLACK))
                goBangUi.WinGame(2);
            else
                goBangUi.WinGame(1);
        }
        else if(btnStr.equals("结束游戏")){
            winJf.setVisible(false);
            goBangUi.setVisible(false);
            mylis.countBlack=0;
            mylis.countWhite=0;
            mylis.countSum=0;
            mylis.controlColor=0;
            goBangUi.chessIndex.clear();
            goBangUi.startGame();
        }

    }
}

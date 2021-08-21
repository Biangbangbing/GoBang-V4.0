package by0820;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuListener 菜单监听器 实现菜单功能按键
 *
 * 属性：
 * 1.GoBangUi 棋盘界面对象 ———— BiangGoBangUI goBangUi
 * 2.startJf 菜单界面对象 ———— JFrame startJf
 * 3.winJf 获胜界面对象 ———— JFrame winJf
 *
 * 重写的方法：
 * 1.void actionPerformed(ActionEvent e) 重写 actionPerformed(ActionEvent e)方法，根据不同命令设置按键功能 跳转到新页面
 *
 * 方法：
 * 1.void setGoBangUi(BiangGoBangUI goBangUi) 设置界面方法 将棋盘界面传入
 * 2.void setStartJf(JFrame startJf) 设置菜单界面方法 将开始界面传入
 * 3.
 *
 */
public class MenuListener implements ActionListener {

    BiangGoBangUI goBangUi;
    BiangGoBangListener mylis;
    //LoginListener loginListener;
    JFrame startJf ;
    JFrame winJf;
    User user0;

    public void setGoBangUi(BiangGoBangUI goBangUi) {
        this.goBangUi = goBangUi;
    }
    public void setStartJf(JFrame startJf){
        this.startJf = startJf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnStr = e.getActionCommand();
        if(btnStr.equals("双人单机")){
            startJf.setVisible(false);
            goBangUi.initBiangGoBangUI();
        }
        else if(btnStr.equals("人机对战")){

        }
        else if(btnStr.equals("读档")){
            if(user0.getConfirmSave()==0){
                JOptionPane.showMessageDialog(null,"很抱歉，并没有你的存档信息！","根本没找到存档的棋盘",1);
            }
            else{
                JOptionPane.showMessageDialog(null,"欢迎回来！","读取存档成功",1);
                goBangUi.chesses= user0.chesses;
                goBangUi.chessIndex = user0.chessIndex;
                mylis.countBlack = user0.countBlack;
                mylis.countWhite = user0.countWhite;
                mylis.countSum = user0.countSum;
                mylis.controlColor = user0.controlColor;
                goBangUi.paint(goBangUi.getGraphics());
            }

        }
        else if(btnStr.equals("查看战绩")){

            //要显示在面板上
            System.out.println(goBangUi.user0.getScore());
            System.out.println(goBangUi.user0.getBattle());
        }
        else if(btnStr.equals("退出游戏")){

        }
    }
}

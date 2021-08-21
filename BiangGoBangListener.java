package by0820;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 棋盘操作下棋监听器 (实现鼠标 鼠标拖动接口)
 * 属性：
 * 1.chessX chessY当前下棋的坐标
 * 2.pen 绘制棋子的画笔组件
 * 3.controlColor 控制棋子颜色交替  0 黑 1 白
 * 4.countBlack countWhite countSum 计数黑棋 白棋个数 下过的棋子个数（撤回不删除 撤回的也算）
 * 5.goBangUi ———— BiangGoBangUI 棋盘界面对象
 * 6.startJf  ———— JFrame 开始游戏界面对象
 * 7.winJf    ———— JFrame 获胜界面对象
 * 8.changeLocation 改变方向的 [8][2] 二维数组
 *
 * 方法：
 * 1.void setGraphics(Graphics pen)  向监听器传入界面的画笔对象
 * 2.void setGoBangUi(BiangGoBangUI goBangUi) 向监听器传入棋盘界面对象
 * 3.void setStartJf(JFrame startJf) 向监听器传入开始界面对象
 * 4.void setWinJf(JFrame winJf)  向监听器传入获胜界面
 * 5.int ifWin(int x,int y,int colorNum) 判断棋面输赢函数 返回赢方数字    2黑方 1白方 0没有
 * 6.
 *
 * 重写的方法：
 * 1.mousePressed 实现点击下棋 棋子校正 控制下棋范围 棋盘保存 不重复下棋 判断输赢并跳转赢棋界面
 * 2.actionPerformed 实现开始游戏 功能按钮控制   清空 撤回 存档 帮助
 * 3.
 */


public class BiangGoBangListener implements MouseListener , ActionListener, MouseMotionListener , BiangGoBangInterface {
    int chessX,chessY;
    Graphics pen;
    int controlColor,controlBack;
    int countBlack=0, countWhite=0,countSum=0;
    BiangGoBangUI goBangUi;
    JFrame startJf ;
    JFrame winJf;
    int[][] changeLocation =new int[][]{{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};//04 15 26 37
    User user0 ;
    User user1 ;


    public void setGraphics(Graphics pen){
        this.pen = pen;
    }
    public void setGoBangUi(BiangGoBangUI goBangUi) {this.goBangUi = goBangUi; }
    public void setStartJf(JFrame startJf) {this.startJf = startJf; }
    public void setWinJf(JFrame winJf) {this.winJf = winJf; }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("点击棋盘");
        controlBack=0;
        if(x>X-SIZE && x<X+SIZE*ROW+SIZE && y>Y-SIZE && y<Y+SIZE*LINE+SIZE ){
            //棋子校正：点中间线归上左
            int baseX = X + ((x - X) / SIZE) * SIZE;
            int baseY = Y + ((y - Y) / SIZE) * SIZE;
            if ((x - baseX) > (SIZE / 2))
                chessX = baseX + SIZE;
            else
                chessX = baseX;
            if ((y - baseY) > (SIZE / 2))
                chessY = baseY + SIZE;
            else
                chessY = baseY;
            if(goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]!=0)
                return;
            if((controlColor%2)==0) {   //偶数为黑色
                countBlack++;
                pen.setColor(Color.BLACK);
                goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]=2;
            }
            else {
                countWhite++;           //奇数为白色
                pen.setColor(Color.WHITE);
                goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]=1;
            }

            countSum++;
            System.out.println("controlColor:"+controlColor);
            controlColor=(controlColor+1)%2;
            goBangUi.chessIndex.add(new chessShape(countSum,chessX,chessY,pen.getColor(),false)); //存的是棋子圆心，画的时候要偏移
            pen.fillOval(chessX - SIZE / 2, chessY - SIZE / 2, SIZE, SIZE);
            pen.setColor(Color.WHITE);
            pen.fillRect(100,30,500,30);
            pen.setColor(Color.BLACK);
            pen.drawString("黑色棋子计数: "+countBlack,150,50);
            pen.drawString("白色棋子计数: "+countWhite,450,50);
            //System.out.println("第一次循环正常结束！");
            //System.out.println(goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]);
            if(ifWin(((chessY-Y)/SIZE),((chessX-X)/SIZE),goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE])!=0){
                //System.out.println("进入判赢棋盘");
                goBangUi.WinGame(goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]);
//                pen.drawImage(goBangUi.winImg,100,200,100,100,null);
//                if(goBangUi.chesses[(chessY-Y)/SIZE][(chessX-X)/SIZE]==2){
//                    System.out.println("黑方获胜！");
//                    pen.drawString("恭喜黑方获胜！",100,100);
//                }
//                else {
//                    System.out.println("白方获胜！");
//                    pen.drawString("恭喜白方获胜！",100,100);
//                }
            }
            System.out.println("第一次循环正常结束！");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnStr = e.getActionCommand();
        if(btnStr.equals("清空")){
            System.out.println("我感受到了清空的召唤！");
            //棋盘记录改
            for(int i = 0;i < ROW + 1;i++){
                for(int j = 0; j<LINE + 1;j++)
                    goBangUi.chesses[i][j]=0;
            }
            countBlack=0;
            countWhite=0;
            countSum=0;
            controlColor=0;
            goBangUi.chessIndex.clear();
            goBangUi.repaint();         // 可以放到前面，然后重画棋盘格写到paint方法里面

            //步数记录改
        }
        else if(btnStr.equals("撤回")){
            if(controlBack==1)
                JOptionPane.showMessageDialog(null,"你已经撤回棋子，不可以再次撤回，请下棋！","这个人企图多次撤回",1);
            else {
                controlBack=1;
                //goBangUi.paint(pen);
                if (goBangUi.chessIndex.get(countSum - 1).getColor().equals(Color.BLACK))
                    countBlack--;
                else
                    countWhite--;
                //棋盘记录改
                //            for(int i=0;i<ROW+1;i++){
                //                for(int j=0;j<LINE+1;j++){
                //                    System.out.print(goBangUi.chesses[i][j]);
                //                }
                //                System.out.println();
                //            }
                //            System.out.println();

                goBangUi.chessIndex.get(countSum - 1).setReback(true);
                System.out.println((goBangUi.chessIndex.get(countSum - 1).getChessY() - Y) / SIZE);
                System.out.println((goBangUi.chessIndex.get(countSum - 1).getChessX() - X) / SIZE);
                goBangUi.chesses[(goBangUi.chessIndex.get(countSum - 1).getChessY() - Y) / SIZE][(goBangUi.chessIndex.get(countSum - 1).getChessX() - X) / SIZE] = 0;

                //            for(int i=0;i<ROW+1;i++){
                //                for(int j=0;j<LINE+1;j++){
                //                    System.out.print(goBangUi.chesses[i][j]);
                //                }
                //                System.out.println();
                //            }
                //System.out.println("撤回前:"+goBangUi.chessIndex.size());

                goBangUi.paint(pen);
                controlColor = (controlColor + 1) % 2;

                //原本 paint 方法只是重画棋盘格，棋子的重画在监听器里面实现
                //步数记录改   如果步数记录不更改就会利于后续回放   但是如果用棋盘扫描时间复杂度会不会很高
                //            goBangUi.chessIndex.remove(goBangUi.chessIndex.size()-1);
                //            System.out.println("撤回后:"+goBangUi.chessIndex.size());
                //            for(int i=0;i<goBangUi.chessIndex.size();i++){
                //                pen.setColor(goBangUi.chessIndex.get(i).getColor());
                //                System.out.println(i);
                //                System.out.println(goBangUi.chessIndex.get(i).getChessX()+"  "+goBangUi.chessIndex.get(i).getChessY());
                //                pen.fillOval(goBangUi.chessIndex.get(i).getChessX()-SIZE/2, goBangUi.chessIndex.get(i).getChessY()-SIZE/2,SIZE,SIZE);
                //            }

                //计数板改变
                pen.setColor(Color.WHITE);
                pen.fillRect(100, 30, 500, 30);
                pen.setColor(Color.BLACK);
                pen.drawString("黑色棋子计数: " + countBlack, 150, 50);
                pen.drawString("白色棋子计数: " + countWhite, 450, 50);
            }

            //步数记录改
            //goBangUi.chessIndex.remove(goBangUi.chessIndex.size()-1);
        }
        //保存当前棋盘盘面，
        else if(btnStr.equals("存档")){
            user0.setChesses(goBangUi.chesses, goBangUi.chessIndex, countBlack,countWhite,countSum,controlColor,1);
            user1.setChesses(goBangUi.chesses, goBangUi.chessIndex, countBlack,countWhite,countSum,controlColor,1);
        }
        else if(btnStr.equals("帮助")){

        }
        else if(btnStr.equals("退出游戏")){
            JOptionPane.showConfirmDialog(null,"确认退出游戏？","退出游戏",JOptionPane.YES_NO_OPTION);
            if(user0.getConfirmSave()==0) {
                int res = JOptionPane.showConfirmDialog(null, "没有存档棋局，您缺人要离开吗？", "确认存档提醒", JOptionPane.YES_NO_OPTION);
                if(res == 0) {
                    for(int i = 0;i < ROW + 1;i++){
                        for(int j = 0; j<LINE + 1;j++)
                            goBangUi.chesses[i][j]=0;
                    }
                    countBlack=0;
                    countWhite=0;
                    countSum=0;
                    controlColor=0;
                    goBangUi.chessIndex.clear();
                    goBangUi.setVisible(false);
                    goBangUi.startGame();
                }
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int ifWin(int x,int y,int colorNum){
        int countNumFive = 4;
        int currX=x,currY = y;
        int flag = 1;
        for(int i=0;i<4;i++) {    //左上到右下  竖  右上到左下  横
            countNumFive=4;
            while (countNumFive > 0) {
                if (flag == 1) {
                    if (currX + changeLocation[i][0] >= 0 && currX + changeLocation[i][0] <= 16 && currY + changeLocation[i][1] >= 0 && currY + changeLocation[i][1] <= 16 && goBangUi.chesses[currX + changeLocation[i][0]][currY + changeLocation[i][1]] == colorNum) {
                        currX += changeLocation[i][0];
                        currY += changeLocation[i][1];
                        countNumFive--;
                    }
                    else {          //换方向
                        flag = 2;
                        currX = x;
                        currY = y;
                    }
                }
                else if (flag == 2 ) {
                    if (currX + changeLocation[i+4][0] >= 0 && currX + changeLocation[i+4][0] <= 16 && currY + changeLocation[i+4][1] >= 0 && currY + changeLocation[i+4][1] <= 16 && goBangUi.chesses[currX + changeLocation[i+4][0]][currY + changeLocation[i+4][1]] == colorNum) {
                        currX += changeLocation[i+4][0];
                        currY += changeLocation[i+4][1];
                        countNumFive--;
                    }
                    else {         //这个方向没连够
                        flag = 1;
                        currX = x;
                        currY = y;
                        break;
                    }
                }
                if (countNumFive == 0)
                    return colorNum;
            }
        }
        //if(countNumFive!=0)
            return 0;
    }
}

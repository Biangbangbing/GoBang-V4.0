package by0820;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GoBang界面
 * 继承JFRame ，重写paint方法
 *
 * 属性：
 * 1.bgimg 背景图
 * 2.bgMenuImg 菜单背景图
 * 3.winImg 获胜界面插图
 * 4.loginImg 登录界面插图
 * 5.mylis GoBang 棋盘操作监听器 ———— BiangGoBangListener mylis
 * 6.loginListener GOBang 登录页面监听器  ————— LoginListener loginListener
 * 7.menuListener GoBang 菜单界面监听器 ———— MenuListener menuListener
 * 8.winListener GoBang 获胜页面监听器 ———— WinListener winListener
 * 9.chesses 二维数组[][] chesses 存放棋盘 2 1 0 代表黑 白 空
 * 10.一位数组chessShape 数组 chessIndex 存放棋盘棋子 下棋顺序 横纵坐标 棋子颜色情况color      0-16 共 17*17个点
 *
 *
 * 方法：
 * 1.initBiangGoBangUI() 初始化界面方法 开始选择是否黑棋先手：yes 黑 no 白 不能 cancel 否则一直弹出直到完成选择 生成棋盘 + 下棋功能键 (开始 清空 撤回 存档 帮助)
 * 2.startGame()  开始游戏方法  设置菜单页面 背景图 标题 功能按键 (双人单机 人机对战 读档 查看战绩 退出游戏) 跳转棋盘页面
 * 3.loginGame()  登录游戏方法  开始实现用户名登录 跳转开始游戏界面
 * 4.winGame() 游戏获胜方法 设置获胜页面：展示获胜方 获胜庆祝图 功能按键(再战一局 回顾棋局 退出游戏)
 *
 * 重写方法：
 * 1.paint() 实现窗体刷新之后重画 背景图 棋盘 棋面上保存的棋子
 *
 */
public class BiangGoBangUI extends JFrame implements BiangGoBangInterface {
    public static final Image bgimg = new ImageIcon("img/bg50.jpg").getImage();
    public static final ImageIcon bgMenuImg = new ImageIcon("img/bgMenu50.png");
    public static final ImageIcon winImg = new ImageIcon("img/winner3.jpg");
    public static final ImageIcon loginImg = new ImageIcon("img/login.png");
    public BiangGoBangListener mylis = new BiangGoBangListener();
    public LoginListener loginListener = new LoginListener();
    public MenuListener menuListener = new MenuListener();
    public WinListener winListener = new WinListener();
    public int[][] chesses = new int[ROW+1][LINE+1];
    public ArrayList<chessShape> chessIndex = new ArrayList<chessShape>(300);
    public User user0;
//    public int[] chessIndexX = new int [];
//    public int[] ChessIndexY = new int [];

    public void initBiangGoBangUI() {
        for(int i=0;i<17;i++){
            for(int j=0;j<17;j++){
                chesses[i][j]=0;
                System.out.print("i"+i+"j"+j+"   "+chesses[i][j]);
            }
            System.out.println();
        }

        //JFrame bgb = new JFrame("Biang's五子棋v 1.0");
        this.setTitle("Biang's 五子棋 v1.0");
        this.setSize(SIZE * LINE+X*2, SIZE * ROW+Y*3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mylis.setGoBangUi(this);

        //JOptionPane chooseFirst = new JOptionPane()
        //弹窗返回结果0 1 2
        //System.out.println("选择结果："+JOptionPane.showConfirmDialog(null,"您选择黑棋先手？","选先手",JOptionPane.YES_NO_CANCEL_OPTION));
        int chooseRes = JOptionPane.showConfirmDialog(null,"您选择黑棋先手？","选先手",JOptionPane.YES_NO_CANCEL_OPTION);
        System.out.println("res: "+chooseRes);
        while(chooseRes==2) {
            chooseRes = JOptionPane.showConfirmDialog(null,"您选择黑棋先手？","选先手",JOptionPane.YES_NO_CANCEL_OPTION);
        }
        if (chooseRes == 0) {
            mylis.controlColor = 0;
        }
        else if (chooseRes == 1) {
            mylis.controlColor = 1;
        }

        //JOptionPane.showMessageDialog(null,"请选择黑棋、还是白棋先手！","选先手",JOptionPane.PLAIN_MESSAGE);

        JPanel downBar = new JPanel();
        downBar.setBackground(Color.GRAY);
        Dimension dim = new Dimension(downWidth,downWidth);
        downBar.setPreferredSize(dim);

        JButton BeginBtn = new JButton("退出游戏");
        BeginBtn.setBackground(Color.WHITE);
        BeginBtn.setSize(btnWidth,btnHeight);
        downBar.add(BeginBtn);

        JButton ClearBtn = new JButton("清空");
        ClearBtn.setBackground(Color.WHITE);
        ClearBtn.setSize(btnWidth,btnHeight);
        downBar.add(ClearBtn);

        JButton backBtn = new JButton("撤回");
        backBtn.setBackground(Color.WHITE);
        backBtn.setSize(btnWidth,btnHeight);
        downBar.add(backBtn);

        JButton saveBtn = new JButton("存档");
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setSize(btnWidth,btnHeight);
        downBar.add(saveBtn);

        JButton helpBtn = new JButton("帮助");
        helpBtn.setBackground(Color.WHITE);
        helpBtn.setSize(btnWidth,btnHeight);
        downBar.add(helpBtn);

        BeginBtn.addActionListener(mylis);
        ClearBtn.addActionListener(mylis);
        backBtn.addActionListener(mylis);
        this.add(downBar,BorderLayout.SOUTH);
        //BeginBtn.addActionListener(mylis);

//  实现下棋子下在面板之内：特别为棋盘设置面板画笔不一致，需要重新写继承JPanel的paint方法，所以换在监听器里实现
//        this.setLayout(null);
//        JPanel chessBoard = new JPanel();
//        chessBoard.setLocation(X,Y);
//        Dimension dim = new Dimension(SIZE*ROW,SIZE*LINE);
//        //chessBoard.setPreferredSize(dim);
//        chessBoard.setSize(SIZE*ROW,SIZE*LINE);
//        chessBoard.setBackground(Color.BLUE);
//        this.add(chessBoard);

        this.setVisible(true);
        this.addMouseListener(mylis);
        Graphics pen = this.getGraphics();
        mylis.setGraphics(pen);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        System.out.println("调用了paint函数！" );
        g.drawImage(bgimg,X-SIZE,Y-SIZE,(ROW+2)*SIZE,(LINE+2)*SIZE,null);
        g.setColor(Color.BLACK);
        for(int i=0;i<=ROW;i++){
            g.drawLine(X,Y+i*SIZE,X+SIZE*LINE,Y+i*SIZE);  //y不变，画横线
            g.drawLine(X+i*SIZE,Y,X+i*SIZE,Y+SIZE*ROW);   //x不变，画竖线
        }
        g.fillOval(X+3*SIZE-3,Y+3*SIZE-3,6,6);
        g.fillOval(X+3*SIZE-3,Y+8*SIZE-3,6,6);
        g.fillOval(X+3*SIZE-3,Y+13*SIZE-3,6,6);
        g.fillOval(X+8*SIZE-3,Y+3*SIZE-3,6,6);
        g.fillOval(X+8*SIZE-3,Y+8*SIZE-3,6,6);
        g.fillOval(X+8*SIZE-3,Y+13*SIZE-3,6,6);
        g.fillOval(X+13*SIZE-3,Y+3*SIZE-3,6,6);
        g.fillOval(X+13*SIZE-3,Y+8*SIZE-3,6,6);
        g.fillOval(X+13*SIZE-3,Y+13*SIZE-3,6,6);
//        for(int i=1;i<=3;i++){

//            for(int j=1;j<=3;j++){
//                g.fillOval(X+i*4*SIZE-3,Y+j*4*SIZE-3,6,6);
//            }
//        }
        g.fillOval(X+8*SIZE-4,Y+8*SIZE-4,8,8);
        for(int i=0;i<ROW+1;i++){
            for(int j=0;j<LINE+1;j++){
                if(chesses[i][j]==2){
                    g.setColor(Color.BLACK);
                    g.fillOval(X+j * SIZE-SIZE/2,Y+i*SIZE-SIZE/2,SIZE,SIZE);
                }
                else if(chesses[i][j]==1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(X + j * SIZE - SIZE / 2, Y + i * SIZE - SIZE / 2, SIZE, SIZE);
                }
            }
        }
    }

    public void startGame(){
        JFrame startjf = new JFrame("Biang 's 五子棋v3.0");
        startjf.setSize(650,379);
        startjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startjf.setLayout(null);

        JLabel bgImgLable = new JLabel(bgMenuImg);
        bgImgLable.setSize(650,379);
        bgImgLable.setLocation(0,0);

        //lable在最后面一定要放到最后加
        JLabel menuTitleLable = new JLabel("Biang' s GoBang");
        menuTitleLable.setSize(200,100);
        menuTitleLable.setLocation(275,20);

        JLabel secoTitleLable = new JLabel("—— Menu ——");
        secoTitleLable.setSize(200,100);
        secoTitleLable.setLocation(275,60);

        //startjf.add(bgImgLable);
        startjf.add(menuTitleLable);
        startjf.add(secoTitleLable);

        JButton btn1 = new JButton("双人单机");
        btn1.addActionListener(menuListener);
        btn1.setSize(100,30);
        btn1.setLocation(25,220);
        startjf.add(btn1);

        JButton btn2 = new JButton("人机对战");
        btn2.addActionListener(menuListener);
        btn2.setSize(100,30);
        btn2.setLocation(150,220);
        startjf.add(btn2);

        JButton btn3 = new JButton("读档");
        btn3.addActionListener(menuListener);
        btn3.setSize(100,30);
        btn3.setLocation(275,220);
        startjf.add(btn3);

        JButton btn4 = new JButton("查看战绩");
        btn4.addActionListener(menuListener);
        btn4.setSize(100,30);
        btn4.setLocation(400,220);
        startjf.add(btn4);

        JButton btn5 = new JButton("退出游戏");
        btn5.addActionListener(menuListener);
        btn5.setSize(100,30);
        btn5.setLocation(525,220);
        startjf.add(btn5);

        startjf.add(bgImgLable);

        startjf.setVisible(true);
        menuListener.setGoBangUi(this);   //全局？？
        menuListener.setStartJf(startjf);
    }

    public void loginGame(){
        JFrame loginJf = new JFrame("欢迎登录 Biang 's 五子棋v3.0");
        loginJf.setSize(500,500);
        loginJf.setLayout(null);
        loginJf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginJf.setIconImage(new ImageIcon("img/icon2.jpg").getImage());

        //FlowLayout fl = new FlowLayout();
        //loginJf.setLayout(fl);

        JTextField jTFLoginUser = new JTextField(12);
        JPasswordField jTFLoginPassword = new JPasswordField(12);
        jTFLoginUser.setSize(200,35);
        jTFLoginPassword.setSize(200,35);
        jTFLoginUser.setLocation(150,300);
        jTFLoginPassword.setLocation(150,350);

        JButton btn0 = new JButton("登录");
        btn0.setSize(80,30);
        btn0.setLocation(155,400);
        btn0.addActionListener(loginListener);

        JButton btn1 = new JButton("注册");
        btn1.setSize(80,30);
        btn1.setLocation(260,400);
        btn1.addActionListener(loginListener);

        JLabel logImgLabel = new JLabel(loginImg);
        logImgLabel.setSize(300,174);
        logImgLabel.setLocation(80,80);


        JLabel userLabel = new JLabel("User：");
        userLabel.setSize(50,50);
        userLabel.setLocation(100,293);

        JLabel passwordLabel = new JLabel("password：");
        passwordLabel.setSize(100,50);
        passwordLabel.setLocation(85,344);

        loginJf.add(logImgLabel);
        loginJf.add(jTFLoginUser);
        loginJf.add(jTFLoginPassword);
        loginJf.add(userLabel);
        loginJf.add(passwordLabel);
        loginJf.add(btn0);
        loginJf.add(btn1);

//        btn0.setLocation(100,200);
//        jTFLoginUser.setLocation(100,100);
//        jTFLoginPassword.setLocation(100,150);
//        btn0.setSize();

//        loginJf.add(jTFLoginUser,BorderLayout.SOUTH);
//        loginJf.add(jTFLoginPassword,BorderLayout.SOUTH);
//        loginJf.add(btn0,BorderLayout.SOUTH);

        loginJf.setVisible(true);
        loginListener.setGoBangUi(this);
        loginListener.setLoginJf(loginJf);
        loginListener.setjTFUser(jTFLoginUser);
        loginListener.setjTFPassword(jTFLoginPassword);

    }

    public void WinGame(int winNumber){
        //JOptionPane.showMessageDialog(null,"恭喜黑方获胜！","赢下一局",JOptionPane.PLAIN_MESSAGE);

        JFrame winJf = new JFrame("兄弟！太厉害了");
        winJf.setSize(400,500);
        winJf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        winJf.setLayout(null);

        JLabel winImgLable = new JLabel(winImg);
        winImgLable.setSize(200,247);
        winImgLable.setLocation(100,95);

        JLabel countLable = new JLabel("黑子："+mylis.countBlack+"     白子："+mylis.countWhite);
        countLable.setSize(200,30);
        countLable.setLocation(145,50);


        if(winNumber==2) {
            JLabel textLable = new JLabel("恭喜黑方获胜！");
            textLable.setSize(100, 30);
            textLable.setLocation(160, 20);
            winJf.add(textLable);
        }
        else{
            JLabel textLable = new JLabel("恭喜白方获胜！");
            textLable.setSize(100, 50);
            textLable.setLocation(150, 20);
            winJf.add(textLable);
        }



        JButton btn1 = new JButton("再战一局");
        btn1.addActionListener(winListener);
        btn1.setSize(100,30);
        btn1.setLocation(23,400);

        JButton btn2 = new JButton("回顾棋局");
        btn2.addActionListener(winListener);
        btn2.setSize(100,30);
        btn2.setLocation(148,400);

        JButton btn3 = new JButton("结束游戏");
        btn3.addActionListener(winListener);
        btn3.setSize(100,30);
        btn3.setLocation(273,400);

        winJf.add(winImgLable);
        winJf.add(countLable);
        winJf.add(btn1);
        winJf.add(btn2);
        winJf.add(btn3);

        winJf.setVisible(true);


        winListener.setGoBangUi(this);   //全局？？
        winListener.setWinJf(winJf);
        winListener.setMylis(mylis);

        //Graphics wpen = winJf.getGraphics();
        winListener.setGraphics(this.getGraphics());

    }

}

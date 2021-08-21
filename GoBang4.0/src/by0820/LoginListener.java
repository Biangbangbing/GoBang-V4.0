package by0820;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * LoginListener 登录界面监听器 实现登录界面按钮功能
 *
 * 属性
 * 1.JTextField jTFUser User输入框
 * 2.JTextField jTFPassword password输入框
 * 3.BiangGoBangUI goBangUi 棋盘界面对象
 * 4.JFrame loginJf 登录界面对象
 * 5.User[] users 用户数组，储存用户信息
 *
 * 重写的方法
 * 1.void actionPerformed(ActionEvent e) 重写实现按键功能 登录：检测用户用户名与密码一致性  注册：登记新用户，重新登录
 *
 * 方法
 * 1.void setjTFUser(JTextField jTFUser) 设置 User输入框框对象
 * 2.void setjTFPassword(JTextField jTFPassword) 设置 password输入框对象
 * 3.void setGoBangUi(BiangGoBangUI goBangUi) 设置棋盘界面对象
 * 4.void setLoginJf(JFrame loginJf) 设置登录界面对象
 *
 */
public class LoginListener implements ActionListener {
    private JTextField jTFUser;
    private JTextField jTFPassword;
    BiangGoBangUI goBangUi;
    JFrame loginJf ;
    ArrayList<User> users = new ArrayList<User>(1000);
    //User[] users = new User[1000];

    public void setjTFUser(JTextField jTFUser) {this.jTFUser = jTFUser; }
    public void setjTFPassword(JTextField jTFPassword) {this.jTFPassword = jTFPassword; }
    public void setGoBangUi(BiangGoBangUI goBangUi) {this.goBangUi = goBangUi; }
    //public void setStartJf(JFrame startJf) {this.startJf = startJf; }
    public void setLoginJf(JFrame loginJf) {this.loginJf = loginJf; }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnStr = e.getActionCommand();
        String jTFUserStr = jTFUser.getText();
        String jTFPasswordStr = jTFPassword.getText();
        if(btnStr.equals("登录")){
            if(jTFUserStr.equals("Stefan") && jTFPasswordStr.equals("0410")){
                loginJf.setVisible(false);
                goBangUi.startGame();
            }
        }
        else if(btnStr.equals("注册")){
            User aUser = new User(jTFUserStr,jTFPasswordStr,0,0);
            users.add(aUser);
            int res = JOptionPane.showConfirmDialog(null,"恭喜您，注册成功！您是否要直接登录游戏？","注册成功",JOptionPane.YES_NO_OPTION);
            if(res == 0) {
                loginJf.setVisible(false);
                goBangUi.startGame();
            }
        }
    }
}

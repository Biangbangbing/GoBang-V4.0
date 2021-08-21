package by0820;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import java.awt.*;

public class AiGoBang implements BiangGoBangInterface{
    public int[][] chessWeight = new int[ROW+1][LINE+1];
    public ChessHashMap chessHashMap = new ChessHashMap();
    public BiangGoBangUI goBangUI;
    public Graphics pen;
    int[][] changeLocation =new int[][]{{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};//04 15 26 37
    //public int[][] c

    public void setGoBangUI(BiangGoBangUI goBangUI) {
        this.goBangUI = goBangUI;
    }

    public void setPen(Graphics pen) {
        this.pen = pen;
    }

    public void fillChessMap(){
        //this.chessHashMap.addCode();
        System.out.println("chesses棋盘：");
        for(int i=0;i<=ROW;i++){
            for(int j=0;j<=LINE;j++){
                System.out.print(goBangUI.chesses[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
        //System.out.println(this.chessHashMap.getCodeWeight("010"));
        for(int i=0;i<=ROW;i++){
            for(int j=0;j<=LINE;j++){
                if(goBangUI.chesses[i][j]==0){
                    int score=0;
                    for(int l=0;l<8;l++) {
                        if(i+this.changeLocation[l][0]>ROW || j+this.changeLocation[l][1]>LINE || i+this.changeLocation[l][0]<0 || j+this.changeLocation[l][1]<0){
                            System.out.println("i:"+i+this.changeLocation[l][0]+" j:"+j+this.changeLocation[l][1]);
                            continue;
                        }
                        String strCode="0";
                        int chessColor = goBangUI.chesses[i+changeLocation[l][0]][j+changeLocation[l][1]];
                        System.out.println("chessColor:"+chessColor);
                        if(chessColor!=0) {
                            for (int k = 1; k <= 4; k++) {
                                int nowX=i + k * changeLocation[l][0];
                                int nowY=j + k * changeLocation[l][1];
                                if (nowX > ROW || nowY > LINE || nowX < 0 || nowY < 0) {
                                    break;
                                }
                                if (goBangUI.chesses[nowX][nowY] == chessColor) {
                                    strCode += chessColor;
                                }
                                else if(goBangUI.chesses[nowX][nowY] == 0){
                                    strCode += "0";
                                    break;
                                }
                                else
                                    break;
                            }
                        }
                        System.out.println("提前str:"+strCode);
                        if(!strCode.equals("0")) {
                            System.out.println("str:"+strCode);
                            score += this.chessHashMap.getCodeWeight(strCode);
                            System.out.println("score:"+score);
                        }
                    }
                    System.out.println("外面要加的score："+score);
                    this.chessWeight[i][j]=score;
                }

            }
        }
    }
    public void paintChess(){
        int maxWeight=0,maxI=0,maxJ=0;
        for (int i=0;i<=ROW;i++){
            for(int j=0;j<=LINE;j++){
                if(chessWeight[i][j]>maxWeight){
                    maxI=i;
                    maxJ=j;
                }
            }
        }
        //pen.fillOval(X+maxJ*SIZE-SIZE/2,Y+maxI*SIZE-SIZE/2,SIZE,SIZE);
    }
    public static void main(String[] args){
        AiGoBang ai = new AiGoBang();
        BiangGoBangUI goBangUI = new BiangGoBangUI();
        ai.setGoBangUI(goBangUI);
        goBangUI.chesses[1][1]=1;
        ai.chessHashMap.addCode();
        ai.fillChessMap();
        for(int i=0;i<=ROW;i++){
            for(int j=0;j<=LINE;j++){
                System.out.print(ai.chessWeight[i][j]+"\t");
            }
            System.out.println();
        }
        //ai.chessWeight[0][1]=1;
    }
}

package by0820;

import java.util.HashMap;

public class ChessHashMap {
    HashMap<String,Integer> CodeWeight = new HashMap<String,Integer>();
    public void addCode(){
        //活连
        CodeWeight.put("010",10);
        CodeWeight.put("0110",50);
        CodeWeight.put("01110",2000);
        CodeWeight.put("011110",5000);

        CodeWeight.put("020",10);
        CodeWeight.put("0220",50);
        CodeWeight.put("02220",2000);
        CodeWeight.put("022220",5000);

        //死连
        CodeWeight.put("01",5);
        CodeWeight.put("011",25);
        CodeWeight.put("0111",1000);
        CodeWeight.put("01111",2500);

        CodeWeight.put("02",5);
        CodeWeight.put("022",25);
        CodeWeight.put("0222",1000);
        CodeWeight.put("02222",2500);
    }
    public int getCodeWeight(String code){
        // addCode();
        //CodeWeight.
        return CodeWeight.get(code);
    }
    public static void main(String[] args){
        ChessHashMap chessHashMap = new ChessHashMap();
        chessHashMap.addCode();
        System.out.println(chessHashMap.getCodeWeight("0"));  //当找了没有的key 会报错、中断，要避免(或者检错处理try)

    }
}

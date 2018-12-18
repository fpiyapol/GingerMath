package gingermathgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameImplement{

    private ArrayList<Integer> num1; // First pack of number
    private ArrayList<Integer> num2; // Second pack of number
    private int time = -1;
    private int score; 
    private int ans; // correctly answer
    private int guess; // number that player has input
    private int no = 0; // index in pack of number (use both)
    private Timer timer;
    private Prepare pp;
    private JFrame parentFrame;
    private boolean multiChk;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea scoreLabel;
    private String name;
    
    public GameImplement() {
        timer = new Timer();
    }
    
    public GameImplement(ArrayList<Integer> num1, ArrayList<Integer> num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getTime() {
        return time;
    }
    
    public int getProblem1(){
        return num1.get(no);
    }
    
    public int getProblem2(){
        return num2.get(no);
    }
    
    public boolean getMultiChk(){
        return multiChk;
    }
    
    public void setParentFrame(JFrame parentFrame){
        timer.setPrentFrame(parentFrame);
        this.parentFrame = parentFrame;
    }
    
    public void setTimeLabel(JLabel timeLabel){
        timer.setTimerLabel(timeLabel);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    //for Quick Play only
    public void setTimeoutDialog(JDialog timeoutDialog, JTextField answerField, JLabel scoreShowLabel){
        timer.setTimeOutDialog(timeoutDialog, answerField, scoreShowLabel, multiChk);
    }
    
    public void setTimeoutMulti(JDialog timeoutMulti, JTextField answerField){
        timer.setTimeoutMulti(timeoutMulti, answerField, multiChk);
    }
    
    public void setRankingLabel(JLabel player1, JLabel player2, JLabel player3, JLabel player4){
        timer.setRankingLabel(player1, player2, player3, player4);
    }
    
    public void setScoreLabel(JTextArea scoreLabel){
        this.scoreLabel = scoreLabel;
    }
  
    
    
    public void showLeaderboard(String ldb1, String ldb2, String ldb3, String ldb4){
        String[][] msg2 = {ldb1.split("-"), ldb2.split("-"), ldb3.split("-"), ldb4.split("-")};
        
        timer.setScoreRanking(msg2, name);
        
        String[][] player1 = {msg2[0][0].split(":"),  msg2[0][1].split(":")};
        String[][] player2 = {msg2[1][0].split(":"),  msg2[1][1].split(":")};
        String[][] player3 = {msg2[2][0].split(":"),  msg2[2][1].split(":")};
        String[][] player4 = {msg2[3][0].split(":"),  msg2[3][1].split(":")};
        
        String msg = "";
        
        if(player4[0].length==2){
            if(player4[0][1].equals(name)){
                msg += " > "+player4[0][1] + " : " +player4[1][1].replace("]", "") + " <\n";
            }else{
               msg += " "+player4[0][1] + " : " + player4[1][1].replace("]", "") + "\n";
            }
        }
        
        if(player3[0].length==2){
            if(player3[0][1].equals(name)){
                msg += " > "+player3[0][1] + " : " +player3[1][1].replace("]", "") + " <\n";
            }else{
               msg += " "+player3[0][1] + " : " + player3[1][1].replace("]", "") + "\n";
            }
        }
        
        if(player2[0].length==2){
           if(player2[0][1].equals(name)){
               msg += " > "+player2[0][1] + " : " +player2[1][1].replace("]", "") + " <\n";
            }else{
               msg += " "+player2[0][1] + " : " + player2[1][1].replace("]", "") + "\n";
            }
        }
        
        if(player1[0].length==2){
            if(player1[0][1].equals(name)){
                msg += " > "+player1[0][1] + " : " +player1[1][1].replace("]", "") + " <\n";
            }else{
               msg += " "+player1[0][1] + " : " + player1[1][1].replace("]", "") + "\n";
            }
        }
        
        scoreLabel.setText(msg);
        
    }
    
    public void setStatus(Boolean pauseFlag, Boolean playerExitFlag){
        System.out.println("imple "+pauseFlag+" "+playerExitFlag);
        timer.setStatus(pauseFlag, playerExitFlag);
    }
    
    public boolean check(int answer){
        int ans = num1.get(no)+num2.get(no);
        if(answer == ans){
            no++;
            score++;
            timer.setScore(score);
            if(multiChk){
                out.println("score "+score);
            }
            return true;
        }else{
            return false;
        }
    }
    
    public void setNum1(ArrayList num1){
        this.num1 = num1;
    }
    
    public void setNum2(ArrayList num2){
        this.num2 = num2;
    }
    
    public void setSocket(Socket socket, BufferedReader in, PrintWriter out){
        System.out.println("GameImplement set socket.");
        this.socket = socket;
        this.in = in;
        this.out = out;
        multiChk = true;
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public BufferedReader getIn(){
        return in;
    }
    
    public PrintWriter getOut(){
        return out;
    }
    

    
    public void start(){
        if(num1 == null && num2 == null){
            num1 = new ArrayList<>();
            num2 = new ArrayList<>();
            NumberGenerator.genNum(num1);
            NumberGenerator.genNum(num2);
        }
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Prepare pp = new Prepare(parentFrame);
                    SoundControl.playSound("beep.wav");
                    Thread.sleep(1000);
                    pp.setPrepareText("2");
                    SoundControl.playSound("beep.wav");
                    Thread.sleep(1000);
                    pp.setPrepareText("1");
                    SoundControl.playSound("beep.wav");
                    Thread.sleep(1000);
                    pp.setPrepareText("START");
                    SoundControl.playSound("moment.wav");
                    Thread.sleep(400);
                    pp.dispose();
                    new Thread(timer).start();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
        
        if(multiChk){
            new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        String data = in.readLine(); 
                        if(data.startsWith("[")){;
                            System.out.println(" data in "+data);
                            String[] info = data.split(", ");
                            String[] ldb = info[0].split(" ");
                            showLeaderboard(ldb[0], ldb[1], ldb[2], ldb[3]);
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GameImplement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        ).start();
        }
    }
}
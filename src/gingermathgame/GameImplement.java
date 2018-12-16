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
    
    
    //for Quick Play only
    public void setTimeoutDialog(JDialog timeoutDialog, JTextField answerField, JLabel scoreShowLabel){
        timer.setTimeOutDialog(timeoutDialog, answerField, scoreShowLabel);
    }
    
    public void setTimeoutMulti(){
        
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
                    Thread.sleep(1000);
                    pp.setPrepareText("2");
                    Thread.sleep(1000);
                    pp.setPrepareText("1");
                    Thread.sleep(1000);
                    pp.setPrepareText("START");
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
                        String[] info = data.split(", ");
                        String[] ldb = info[0].split("-");
                        System.out.println("1 "+ ldb[0]);
                        
//                        System.out.println("info"+info[0]+info[1]);
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
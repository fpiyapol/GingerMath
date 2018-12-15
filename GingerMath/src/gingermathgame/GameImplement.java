package gingermathgame;

import java.util.ArrayList;
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
    
    public void setParentFrame(JFrame parentFrame){
        timer.setPrentFrame(parentFrame);
        this.parentFrame = parentFrame;
    }
    
    public void setTimeLabel(JLabel timeLabel){
        timer.setTimerLabel(timeLabel);
    }
    
    public void setAnswerField(JTextField answerField){
        timer.setTextField(answerField);
    }
    
    public void setTimeoutDialog(JDialog timeoutDialog){
        timer.setTimeOutDialog(timeoutDialog);
    }
    
    public void setScoreShowLabel(JLabel scoreShowLabel){
        timer.setShowScoreLabel(scoreShowLabel);
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
            return true;
        }else{
            return false;
        }
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
    }
}
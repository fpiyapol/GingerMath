package gingermathgame;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameImplement{

    private ArrayList<Integer> num1; // First pack of number
    private ArrayList<Integer> num2; // Second pack of number
    private int time = -1;
    private int score; 
    private int ans; // correctly answer
    private int guess; // number that player has input
    private int no = 0; // index in pack of number (use both)
    private Timer timer;
    
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
    }
    
    public void setTimeLabel(JLabel timeLabel){
        timer.setTimerLabel(timeLabel);
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
        new Thread(timer).start();
    }
}
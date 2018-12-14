/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Corvette
 */
public class Timer implements Runnable{
    
    private JLabel timeLabel;
    private JFrame parentFrame;
    private int score;
    
    public void setTimerLabel(JLabel timeLabel){
        this.timeLabel = timeLabel;
    }
    
    public void setPrentFrame(JFrame parentFrame){
        this.parentFrame = parentFrame;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void run() {
        for(int t=10; t>=0; t--) {
            try {
                timeLabel.setText(t + "   ");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        new TimeOut(parentFrame, parentFrame.getSize().width, (int)parentFrame.getSize().getHeight(), score).setVisible(true);
    }
}

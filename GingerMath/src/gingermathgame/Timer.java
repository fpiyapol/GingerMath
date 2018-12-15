/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author Corvette
 */
public class Timer implements Runnable{
    
    private JLabel timeLabel;
    private JFrame parentFrame;
    private JTextField answerField;
    private JDialog timeoutDialog;
    private JLabel showScoreLabel;
    private boolean pauseFlag;
    private boolean playerExitFlag;
    
    protected int score;
    
    public void setTimerLabel(JLabel timeLabel){
        this.timeLabel = timeLabel;
    }
    
    public void setPrentFrame(JFrame parentFrame){
        this.parentFrame = parentFrame;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void setTextField(JTextField answerField){
        this.answerField = answerField;
    }
    
    public void setTimeOutDialog(JDialog timeoutDialog){
        this.timeoutDialog = timeoutDialog;
    }
    
    public void setShowScoreLabel(JLabel showScoreLabel){
        this.showScoreLabel = showScoreLabel;
    }
    
    public void setStatus(boolean pauseFlag, boolean playerExitFlag){
        this.pauseFlag = pauseFlag;
        this.playerExitFlag = playerExitFlag;
    }
    
    public void run() {
        for(int t=60; t>=0; t--) {
            try {
                timeLabel.setText(t + "   ");
                Thread.sleep(1000);
                while(pauseFlag){
                    //Empty code for Pause States
                }
                if(playerExitFlag){
                    return;
                }
                
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
//        TimeOutDialog to = new TimeOutDialog(parentFrame, true, score);
//        GamePlayScreen.scoreShowLabel.setText(" Your Score  "+score);

        if(!playerExitFlag){
            answerField.setEditable(false);
            showScoreLabel.setText(" Your Score  "+score);
            timeoutDialog.setSize(parentFrame.getSize());
            timeoutDialog.setLocationRelativeTo(parentFrame);
            timeoutDialog.setVisible(true);
        }
    }
}

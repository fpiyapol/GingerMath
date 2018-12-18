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
    private JDialog timeoutMulti;
    private boolean multiChk;
    
    private int time = 60;
    
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    private JLabel player4Label;
    
    private String[][] msg2;
    private String name;
    private PlayerInformation pi;
    
    protected int score;
    
    
    public void setTimerLabel(JLabel timeLabel){
        this.timeLabel = timeLabel;
    }
    
    public void setPrentFrame(JFrame parentFrame){
        this.parentFrame = parentFrame;
    }
    
    public int getTime(){
        return time;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    
    public void setScoreRanking(String[][] msg2, String name){
        this.msg2 = msg2;
        this.name = name;
    }
    
    public void showRanking(){
        
        String[][] player1 = {msg2[0][0].split(":"),  msg2[0][1].split(":")};
        String[][] player2 = {msg2[1][0].split(":"),  msg2[1][1].split(":")};
        String[][] player3 = {msg2[2][0].split(":"),  msg2[2][1].split(":")};
        String[][] player4 = {msg2[3][0].split(":"),  msg2[3][1].split(":")};
        
        if(player4[0].length==2){
            if(player4[0][1].equals(name)){
                player1Label.setText(" > "+player4[0][1] + "   " +player4[1][1].replace("]", "") +" pt ");
            }else{
               player1Label.setText(" "+player4[0][1] + "   " +player4[1][1].replace("]", "") +" pt ");
            }
        }
        
        if(player3[0].length==2){
            if(player3[0][1].equals(name)){
                player2Label.setText(" > "+player3[0][1] + "   " +player3[1][1].replace("]", "") +" pt ");
            }else{
                player2Label.setText(" "+player3[0][1] + " : " +player3[1][1].replace("]", "") +" pt ");
            }
        }
        
        if(player2[0].length==2){
           if(player2[0][1].equals(name)){
               player3Label.setText(" > "+player2[0][1] + "   " +player2[1][1].replace("]", "") +" pt ");
            }else{
               player3Label.setText(" "+player2[0][1] + "   " +player2[1][1].replace("]", "") +" pt ");
            }
        }
        
        if(player1[0].length==2){
            if(player1[0][1].equals(name)){
                player4Label.setText(" > "+player1[0][1] + "   " +player1[1][1].replace("]", "") +" pt ");
            }else{
                player4Label.setText(" "+player1[0][1] + "   " +player1[1][1].replace("]", "") +" pt ");
            }
        }
        
    }
    
    public void setRankingLabel(JLabel p1, JLabel p2, JLabel p3, JLabel p4){
        player1Label = p1;
        player2Label = p2;
        player3Label = p3;
        player4Label = p4;
    }
    
    
    public void setTimeOutDialog(JDialog timeoutDialog, JTextField answerField, JLabel showScoreLabel, boolean MultiChk){
        this.timeoutDialog = timeoutDialog;
        this.answerField = answerField;
        this.showScoreLabel = showScoreLabel;
        this.multiChk = multiChk;
    }
    
    
    public void setTimeoutMulti(JDialog timeoutMulti, JTextField answerField, boolean multiChk){
        this.timeoutMulti = timeoutMulti;
        this.answerField = answerField;
        this.multiChk = multiChk;
    }
    
    public void setPlayerInfo(PlayerInformation playerInfo){
        this.pi = playerInfo;
    }
    
    
    public void setStatus(boolean pauseFlag, boolean playerExitFlag){
        this.pauseFlag = pauseFlag;
        this.playerExitFlag = playerExitFlag;
    }
    
    public void setMultiChk(boolean multiChk){
        this.multiChk = multiChk;
    }
    
    public void run() {
        for(int t=60; t>=0; t--) {
            try {
                timeLabel.setText(t + "   ");
                Thread.sleep(1000);
                time = t;
                
                if (t == 10){
                    SoundControl.playSound("countdown.wav");
                }
                if (t == 3){
                    SoundControl.playSound("end.wav");
                }
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

        if(!playerExitFlag && !multiChk){
            SoundControl.playSound("radar.wav");
            answerField.setEditable(false);
            showScoreLabel.setText(" Your Score  "+score);
            
            pi.loadInformation();
            pi.setScore(score);
            pi.saveInfo();
            
            timeoutDialog.setSize(parentFrame.getSize());
            timeoutDialog.setLocationRelativeTo(parentFrame);
            timeoutDialog.setVisible(true);
        }else if(!playerExitFlag && multiChk){
            SoundControl.playSound("radar.wav");
            answerField.setEditable(false);
            showRanking();
            timeoutMulti.setSize(parentFrame.getSize());
            timeoutMulti.setLocationRelativeTo(parentFrame);
            timeoutMulti.setVisible(true);
        }
    }
}

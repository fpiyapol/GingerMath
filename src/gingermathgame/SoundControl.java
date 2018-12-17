/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author user_
 */
public class SoundControl {
    
    private String soundToggle;

    public static void playSound(String soundName){
        
        String condition = SettingInformation.getDat();
        System.out.println(condition);
        
        SoundControl control = new SoundControl(); 
        if (condition.equals("ON")){
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }catch(Exception ex){
                System.out.println("Error with playing sound.");
                ex.printStackTrace( );
            }
        }
    }
    
}

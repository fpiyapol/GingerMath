/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 60070008
 */
public class SettingInformation {
    
    private String s;
    
    public boolean loadSetting(){
        File f = new File("setting.dat");
        if (f.exists()){
            try {
                FileInputStream fin = new FileInputStream("setting.dat");
                DataInputStream din = new DataInputStream(fin);
                this.s = din.readUTF();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Setting load error : " + ex);
                return false;
            }
        } else{
            //File does not exists -- create new file
            try {
                FileOutputStream fout = new FileOutputStream("setting.dat");
                DataOutputStream dout = new DataOutputStream(fout);
                dout.writeUTF("ON");
                this.s = "ON";
                return true;
            } catch (IOException ex) {
                Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Setting load error : " + ex);
                return false;
            }
        }
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    public static String getDat(){
        try {
            FileInputStream fin = new FileInputStream("setting.dat");
            DataInputStream din = new DataInputStream(fin);
            String dat = din.readUTF();
            return dat;
        } catch (IOException ex) {
            Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void saveSetting(String sett){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("setting.dat");
            DataOutputStream dout = new DataOutputStream(fout);
            dout.writeUTF(sett);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(SettingInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
}

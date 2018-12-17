/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 60070052
 */
public class PlayerInformation {
    
    private String name;
    private int score;
    private String timeStamp;
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;
    
    public boolean loadInformation(){
        file = new File("info.txt");
        if(file.exists()){
            try {
                reader = new BufferedReader(new FileReader(file));
                String data = reader.readLine();
                String[] inf = data.split(" ");
                name = inf[0];
                score = Integer.parseInt(inf[1]);
                System.out.println("loaded");
                return true;
            } catch (FileNotFoundException ex) {
                System.out.println("load file error : " + ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(PlayerInformation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("read file error : " + ex);
                return false;
            }
        }else{
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void saveInfo(){
        file = new File("info.txt");
        try {
            writer = new PrintWriter(file);
            writer.println(name+" "+score);
            writer.flush();
            System.out.println("OK WITH " + name);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

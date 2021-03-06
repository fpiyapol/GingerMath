/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gingermathgame;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 60070052
 */
public class Room extends javax.swing.JFrame {
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<Integer> num1;
    private ArrayList<Integer> num2;
    private boolean updateRoomFlag = true;
    private String name;
    private boolean isPlay = true;
    private Room room;
    private DefaultListModel<String> allPlayersName;
    private boolean flag = true;
    
    /**
     * Creates new form Room
     */
    public Room() {
        initComponents();
        System.out.println(getSize());
        jPanel1.setBackground(new Color(0, 0, 0, 0));
        btStart.setEnabled(false);
        btKick.setEnabled(false);
        
        room = this;
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    try {
                        if(in != null){
                            String datain = in.readLine();
                            String[] dt = datain.split(" ");
                            if(dt[0].equals("lp")){
                                String players = dt[1];
                                allPlayersName = new DefaultListModel<>();
                                
                                for(String name:players.split("-")){
                                    allPlayersName.addElement(name);
                                }
                                if(allPlayersName.size() == 1){
                                    btKick.setEnabled(false);
                                }                    
                                playerList.setModel(allPlayersName);
                                
                            }else if(dt[0].equals("st")){
                                System.out.println("cmd st");
                                num1 = new ArrayList<>();
                                num2 = new ArrayList<>();
                                for(int j=0; j<40; j++){
                                    int n = Integer.parseInt(in.readLine());
                                    num1.add(n);
                                }
                                for(int j=0; j<40; j++){
                                    int n = Integer.parseInt(in.readLine());
                                    num2.add(n);
                                }
                                
                                
                                System.out.println("let's play !!!!!!!!!!!!!!!!!!!!!!!!");
                                GameImplement game = new GameImplement();
                                game.setName(name);
                                game.setSocket(socket, in, out);
                                game.setNum1(num1);
                                game.setNum2(num2);
                                game.setOnline(true);
                                GamePlayScreen gameGUI = new GamePlayScreen(game);
                                setAlwaysOnTop(true);
                                gameGUI.setRoom(room);
                                gameGUI.setSize(getSize());
                                gameGUI.setLocationRelativeTo(null);
                                gameGUI.setVisible(true);
                                dispose();
                                break;
                                
                            }else if(dt[0].equals("ps")){
                                updateRoomFlag = false;
                            }else if(dt[0].equals("kc")){
                                // เมื่อได้รับคำว่า kc เข้ามา warning dialog ว่ามีเตะ แล้วก็ทำแบบุ่ม back;
                                JOptionPane.showMessageDialog(null, "You Know What? You were kicked", "Warning", JOptionPane.WARNING_MESSAGE);
                                out.println("bk -");
                                Lobby lobby = new Lobby();
                                lobby.setSocket(socket, in, out);
                                lobby.setListRoom();
                                setAlwaysOnTop(true);
                                lobby.setSize(getSize());
                                lobby.setLocationRelativeTo(null);
                                lobby.setVisible(true);
                                dispose();
                                break;
                            }
                                
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    public void setRoomName(String roomName){
        this.roomName.setText(roomName);
    }
    
//    public void setIsPlay(boolean isPlay){
//        this.isPlay = isPlay;
//    }
    
    public void setSocket(Socket socket, BufferedReader in, PrintWriter out){
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void updateRoom(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(updateRoomFlag){
                    try {
                        out.println("ru " + roomName.getText());
                        Thread.sleep(2000);
                    } catch (Exception ex) {
                        System.out.println("error : " + ex);
                    }
                }
                System.out.println("updateFlag false");
            }
        }).start();
    }
    
    public void setHost(){
        btStart.setEnabled(true);
        btKick.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradientPanel1 = new gingermathgame.GradientPanel();
        roomName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btKick = new javax.swing.JButton();
        btBackToLobby = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roomName.setFont(new java.awt.Font("Kanit", 0, 24)); // NOI18N
        roomName.setForeground(new java.awt.Color(255, 255, 255));
        roomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomName.setText(" ROOM NAME ");

        playerList.setFont(new java.awt.Font("Kanit", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(playerList);

        btStart.setText("START");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btKick.setText("KICK");
        btKick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKickActionPerformed(evt);
            }
        });

        btBackToLobby.setText("BACK");
        btBackToLobby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackToLobbyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btKick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(213, 213, 213)
                .addComponent(btBackToLobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btKick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btBackToLobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(137, 137, 137))
        );

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradientPanel1Layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(roomName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(110, 110, 110))
                    .addGroup(gradientPanel1Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(212, 212, 212))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBackToLobbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackToLobbyActionPerformed
        SoundControl.playSound("click.wav");
        updateRoomFlag = false;
        flag = false;
        out.println("bk -");
        Lobby lobby = new Lobby();
        lobby.setSocket(socket, in, out);
        lobby.setListRoom();
        setAlwaysOnTop(true);
        lobby.setSize(getSize());
        lobby.setLocationRelativeTo(this);
        lobby.setVisible(true);
        dispose();
    }//GEN-LAST:event_btBackToLobbyActionPerformed

    private void btKickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKickActionPerformed
        SoundControl.playSound("click.wav");
        out.println("kc " + playerList.getSelectedValue());
    }//GEN-LAST:event_btKickActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        SoundControl.playSound("click.wav");
        if(allPlayersName.size() == 1){
            JOptionPane.showMessageDialog(null, "Waiting for another players..", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            updateRoomFlag = false;
//        isPlay = false;
            out.println("ps -");
            out.println("st -");
        }
        
    }//GEN-LAST:event_btStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Room().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBackToLobby;
    private javax.swing.JButton btKick;
    private javax.swing.JButton btStart;
    private gingermathgame.GradientPanel gradientPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> playerList;
    private javax.swing.JLabel roomName;
    // End of variables declaration//GEN-END:variables
}

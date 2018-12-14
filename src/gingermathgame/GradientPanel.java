package gingermathgame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Color;

/**
 *
 * @author Corvette
 */
public class GradientPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, h, new Color(255, 146, 41), 0, 0, new Color(255, 227, 46));

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        g2d.dispose();
    }
    public GradientPanel(){
   // added default constructor
    }
}

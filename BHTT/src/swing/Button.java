/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author PhamDuy
 */
public class Button extends JButton {
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1 = Color.decode("#0099F7");
    private Color color2 = Color.decode("#F11712");
    public Button() {
        setContentAreaFilled(false);
        setForeground(Color.white);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, width, height, height, height);
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.GD_DangNhap;
import gui.GD_Loading;
import gui.GD_QuanLy;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author Trinh Cui Bap
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        GD_Loading ld = new GD_Loading();
        ld.setVisible(true);
        ld.setLocationRelativeTo(null);
        GD_DangNhap dn = new GD_DangNhap();
        for (int i = 0; i <= 100; i+=20) {
            try {
                Thread.sleep(250);
                ld.lplPhanTram.setText(Integer.toString(i) + "%");
                ld.prgLoading.setValue(i);
                if(i==100){
                    ld.setVisible(false);
                    dn.setVisible(true);
                    dn.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    dn.setLocationRelativeTo(null);
                }
            } catch (Exception e) {
            }
        }
        
    }
}

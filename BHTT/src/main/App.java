/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.GD_DangNhap;
import gui.GD_Loading;

/**
 *
 * @author Trinh Cui Bap
 */
public class App {
    public static void main(String[] args) {
        GD_Loading ld = new GD_Loading();
        ld.setVisible(true);
        ld.setLocationRelativeTo(null);
        GD_DangNhap dn = new GD_DangNhap();
        for (int i = 0; i <= 100; i+=10) {
            try {
                Thread.sleep(300);
                ld.lplPhanTram.setText(Integer.toString(i) + "%");
                ld.prgLoading.setValue(i);
                if(i==100){
                    ld.setVisible(false);
                    dn.setVisible(true);
                    dn.setLocationRelativeTo(null);
                }
            } catch (Exception e) {
            }
        }
    }
}

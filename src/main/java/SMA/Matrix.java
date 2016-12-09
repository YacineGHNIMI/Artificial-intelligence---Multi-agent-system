/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMA;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Yacine
 */
public class Matrix  extends JPanel {
    public int[][] M;
    public Matrix() {
        
        M=new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                M[i][j]=0; 
            }
            
        }
        
    }
    @Override 
    public void paint (Graphics graphic)
    {
        
       graphic.setColor(Color.white);
       graphic.fillRect(30, 30, 300, 300);
       graphic.setColor(Color.gray);
        for (int i = 2; i < 11; i++) {
            graphic.drawLine(i*30, 30, i*30, 330);
        }
        for (int i = 2; i < 11; i++) {
           graphic.drawLine(30, i*30, 330, i*30);
        }
    }
}

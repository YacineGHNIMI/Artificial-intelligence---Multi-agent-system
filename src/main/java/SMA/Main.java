/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMA;

/**
 *
 * @author Yacine
 */

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main  extends JPanel {
     static Matrix mat=new Matrix();
  
   Predateur p0=new  Predateur(mat, 1);
   Predateur p1=new  Predateur(mat, 2);
   Predateur p2=new  Predateur(mat, 3);
   Predateur p3=new  Predateur(mat, 4);
   Proie pr = new Proie(mat);
    
  
  
  @Override
  public void paint(Graphics g)
  {
      mat.paint(g);
           
          p1.paint(g);
          p0.paint(g);
          p2.paint(g);
          p3.paint(g);
          pr.paint(g);
      
  }
  
  
  
  public void deplacertout()
  {
     
    p0.deplacement(pr.pos); 
    p1.deplacement(pr.pos);
    p2.deplacement(pr.pos);
    p3.deplacement(pr.pos);
    pr.deplacement();
      
  }
    
  
    public static void main(String[] args) {
        Main main=new Main();
        JFrame frame=new JFrame();
        frame.setSize(380,400);
        frame.setTitle("Porsecution Problem");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(main);    
        while(true)
        {
                 
            try {
                main.repaint();
                main.deplacertout();
                Thread.sleep(500);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print(mat.M[i][j]);
                        System.out.print(" ");
                    }
                    System.out.println("");
                    
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
 
    }
    
    
}

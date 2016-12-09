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


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Proie extends JPanel {
   private boolean resProie=false;
   private Matrix mat;
   Position pos;
   int pas=1;
    public Proie(Matrix mat) {
        this.mat = mat;
        initposition();
    }
 
    private void initposition()
    {
        System.out.println("position initiale de la proie");
        pos=new Position(5,5);
       
    }
    
     @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect((pos.x+1)*30,(pos.y+1)*30, 30,30);
    }
    int a=0;
    
      private void test()
    {
        if(pos.x<=8 && pos.x>=1 && pos.y<=8 && pos.y>=1)
     if(mat.M[pos.x+1][pos.y]==1 && mat.M[pos.x-1][pos.y]==1 && mat.M[pos.x][pos.y+1]==1 && mat.M[pos.x][pos.y-1]==1)
         resProie=true;
    }
    public void deplacement()
    {  
        test();
        if( resProie)
        { JOptionPane.showMessageDialog(this,"Game Over!",  "",JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);}
        if(! resProie)
        {
          if(pos.x==0 || pos.x==9 || pos.y==0 || pos.y==9)
        {
            JOptionPane.showMessageDialog(this,"Well Done!", "",  JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
        } 
          else
          {
              int a=new Random().nextInt()%2;
              if(a==1)
              {
               pas=1;  
              }
              if(a==0)
              {
                  pas=-1;
              }
              int b=new Random().nextInt()%2;
              if(b==1){
                  if(pas==1 && mat.M[pos.x][pos.y+1]==0)
                  {
                      mat.M[pos.x][pos.y]=0;
                  pos.y=pos.y+pas;
                   mat.M[pos.x][pos.y]=2;}
             if(pas==-1 && mat.M[pos.x][pos.y-1]==0)
             {
                  mat.M[pos.x][pos.y]=0;
                 pos.y=pos.y+pas;
                  mat.M[pos.x][pos.y]=2;}}
              if(b==0){
                  if(pas==1 && mat.M[pos.x+1][pos.y]==0){
                   mat.M[pos.x][pos.y]=0;
                      pos.x=pos.y+pas;
                       mat.M[pos.x][pos.y]=2;}
             if(pas==-1 && mat.M[pos.x-1][pos.y]==0){
                  mat.M[pos.x][pos.y]=0;
                  pos.x=pos.x+pas;
                   mat.M[pos.x][pos.y]=2;}
          }}
        }
        test();
    }  
    
}

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
import javax.swing.JPanel;



public class Predateur  extends JPanel {
  
    //initialisation des variables
  private Matrix mat;
  private Position pos=new Position();
  private int position;
  static boolean  resPred=false; 
  int pas_a=1,pas_b=1;
    
   //constructeur avec parametre matrice et position
     public Predateur(Matrix mat, int p) {
        this.mat = mat;
        position = p;
        initpos();
    }
    // Position initiale des predateurs
    private void initpos() {
        
        switch(position)
        {
            case 0 : pos.x=8;pos.y=8;break;//da=8 db=8
                case 1 :pos.x=1;pos.y=8; break;
                    case 2 :pos.x=1;pos.y=1; break;
                        case 3 : pos.x=8;pos.y=1;break;
        }
    
        mat.M[pos.x][pos.y] = 1;

    }
    //design des predateurs
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((pos.x + 1)*30, (pos.y + 1)*30, 30, 30);
        g.setColor(Color.blue);
        g.drawString(String.valueOf(position),(pos.x + 1)*30 + 15, (pos.y + 1)*30 + 15);
    }
 public void deplacement(Position ps) {
     
        if(!resPred)
        {
            test();
            int a=new Random().nextInt()%2;//Génère le nombre pseudo-aléatoire suivant
            int b=new Random().nextInt()%2;
            //pour ne pas sortir de la matrice
            if(pos.x+pas_a>9)
                pas_a=-1;
            if(pos.x+pas_a<0)
                pas_a=1;
            if(pos.y+pas_b>9)
                pas_b=-1;
            if(pos.y+pas_b<0)
                pas_b=1;
            //a chaque déplacement horizontal ou vertical l'element suivant prend la valeur 1 et le precedent prend la valeur 0 
            if(a==0)
            {
              mat.M[pos.x][pos.y]=0;
              pos.x=pos.x+pas_a;
              mat.M[pos.x][pos.y]=1;
            }
            if(a==1)
            {
                 mat.M[pos.x][pos.y]=0;
                 pos.y=pos.y+pas_b;
                 mat.M[pos.x][pos.y]=1;
            }
            
            test(); 
        }
        if(resPred)
        {
        mat.M[pos.x][pos.y] = 0;
        pos = calcdistance(ps);
        mat.M[pos.x][pos.y] = 1;}

    }
 //si le predateur a trouvé la proie dans son champ de vision
 void test()
    {  if(pos.x<9 && pos.x>0 && pos.y<9 && pos.y>0)
        if(mat.M[pos.x+1][pos.y]==2 || mat.M[pos.x-1][pos.y]==2 || mat.M[pos.x][pos.y+1]==2 || mat.M[pos.x][pos.y-1]==2)
            resPred=true;
       if(pos.x==0 && pos.y>0 && pos.y<9)
            if(mat.M[pos.x+1][pos.y]==2 || mat.M[pos.x][pos.y+1]==2 || mat.M[pos.x][pos.y-1]==2)
            resPred=true;
       if(pos.x==9 && pos.y>0 && pos.y<9)
            if(mat.M[pos.x-1][pos.y]==2 || mat.M[pos.x][pos.y+1]==2 || mat.M[pos.x][pos.y-1]==2)
            resPred=true;
       if(pos.y==9 && pos.x>0 && pos.x<9)
            if(mat.M[pos.x][pos.y-1]==2 || mat.M[pos.x+1][pos.y]==2 || mat.M[pos.x-1][pos.y]==2)
            resPred=true;
        if(pos.y==0 && pos.x>0 && pos.x<9)
            if(mat.M[pos.x][pos.y+1]==2 || mat.M[pos.x+1][pos.y]==2 || mat.M[pos.x-1][pos.y]==2)
            resPred=true;
    }
 //
    public Position calcdistance(Position p) {
        double d;
        Position pp = new Position();
        double mindis = 999999999;

        switch (position) {
            case Var.Gauche: {
                if (pos.x < 9) {
                    d = Math.sqrt(Math.pow((pos.x+ 1) - p.x + 1, 2) + Math.pow((pos.x - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x + 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x + 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.x > 0) {
                    d = Math.sqrt(Math.pow((pos.x - 1) - p.x + 1, 2) + Math.pow((pos.y - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x - 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x- 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.y < 9) {
                    d = Math.sqrt(Math.pow(pos.x - p.x + 1, 2) + Math.pow(((pos.y + 1) - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y + 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y = pos.y + 1;
                        }
                    }
                }
                if (pos.y > 0) {
                    d = Math.sqrt(Math.pow(pos.x - p.x + 1, 2) + Math.pow(((pos.y - 1) - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y - 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y = pos.y - 1;
                        }
                    }
                }
                d = Math.sqrt(Math.pow((pos.x) - p.x + 1, 2) + Math.pow((pos.y - p.y), 2));
                if (d < mindis) {
                    mindis = d;
                    pp.x = pos.x;
                    pp.y = pos.y;
                }
                return pp;
            }

            case Var.Haut: {
                if (pos.x < 9) {
                    d = Math.sqrt(Math.pow((pos.x + 1) - p.x, 2) + Math.pow((pos.y - p.y + 1), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x + 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x + 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.x > 0) {
                    d = Math.sqrt(Math.pow((pos.x - 1) - p.x, 2) + Math.pow((pos.y - p.y + 1), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x - 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x - 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.y < 9) {
                    d = Math.sqrt(Math.pow(pos.x - p.x, 2) + Math.pow(((pos.y + 1) - p.y + 1), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y + 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y= pos.y + 1;
                        }
                    }
                }
                if (pos.y > 0) {
                    d = Math.sqrt(Math.pow(pos.x - p.x, 2) + Math.pow(((pos.y - 1) - p.y + 1), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y - 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y = pos.y - 1;
                        }
                    }
                }
                d = Math.sqrt(Math.pow((pos.x) - p.x, 2) + Math.pow((pos.y - p.y + 1), 2));
                if (d < mindis) {
                    mindis = d;
                    pp.x = pos.x;
                    pp.y= pos.y;
                }
                return pp;
            }

            case Var.Droite: {
                if (pos.x < 9) {
                    d = Math.sqrt(Math.pow((pos.x+ 1) - p.x - 1, 2) + Math.pow((pos.y - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x+ 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x+ 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.x > 0) {
                    d = Math.sqrt(Math.pow((pos.x - 1) - p.x - 1, 2) + Math.pow((pos.y - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x - 1][pos.y] == 0) {
                            mindis = d;
                            pp.x = pos.x - 1;
                            pp.y = pos.y;
                        }
                    }
                }
                if (pos.y < 9) {
                    d = Math.sqrt(Math.pow(pos.x - p.x - 1, 2) + Math.pow(((pos.y + 1) - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y + 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y = pos.y + 1;
                        }
                    }
                }
                if (pos.y > 0) {
                    d = Math.sqrt(Math.pow(pos.x - p.x - 1, 2) + Math.pow(((pos.y - 1) - p.y), 2));
                    if (d < mindis)
                    {
                        if (mat.M[pos.x][pos.y - 1] == 0) {
                            mindis = d;
                            pp.x = pos.x;
                            pp.y = pos.y - 1;
                        }
                    }
                }
                d = Math.sqrt(Math.pow((pos.x) - p.x - 1, 2) + Math.pow((pos.y - p.y), 2));
                if (d < mindis) {
                    mindis = d;
                    pp.x = pos.x;
                    pp.y = pos.y;
                }
                return pp;
            }

            case Var.Bas: {
                if(pos.x<9){
                d = Math.sqrt(Math.pow((pos.x + 1) - p.x, 2) + Math.pow((pos.y - p.y - 1), 2));
                if (d < mindis)
                {
                    if (mat.M[pos.x + 1][pos.y] == 0) {
                        mindis = d;
                        pp.x = pos.x + 1;
                        pp.y = pos.y;
                    }
                }}
                if(pos.x>0){
                d = Math.sqrt(Math.pow((pos.x - 1) - p.x, 2) + Math.pow((pos.y - p.y - 1), 2));
                if (d < mindis)
                {
                    if (mat.M[pos.x - 1][pos.y] == 0) {
                        mindis = d;
                        pp.x = pos.x - 1;
                        pp.y= pos.y;
                    }
                }}
                if(pos.y<9){
                d = Math.sqrt(Math.pow(pos.x - p.x, 2) + Math.pow(((pos.y + 1) - p.y - 1), 2));
                if (d < mindis)
                {
                    if (mat.M[pos.x][pos.y + 1] == 0) {
                        mindis = d;
                        pp.x = pos.x;
                        pp.y= pos.y + 1;
                    }
                }}
                if(pos.y>0){
                d = Math.sqrt(Math.pow(pos.x - p.x, 2) + Math.pow(((pos.y - 1) - p.y - 1), 2));
                if (d < mindis)
                {
                    if (mat.M[pos.x][pos.y - 1] == 0) {
                        mindis = d;
                        pp.x = pos.x;
                        pp.y = pos.y - 1;
                    }
                }
                }
                d = Math.sqrt(Math.pow(pos.x - p.x, 2) + Math.pow((pos.y - p.y - 1), 2));
                if (d < mindis) {
                    mindis = d;
                    pp.x= pos.x;
                    pp.y = pos.y;
                }
                return pp;
            }

        }
       return null;
        
    }
}

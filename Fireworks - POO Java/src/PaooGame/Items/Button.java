package PaooGame.Items;

import PaooGame.Graphics.FontLoader;
import PaooGame.Options.Opts;

import java.awt.*;

public class Button {

    public static int ButtonWidth=200;
    public static int ButtonHeight=100;


    int x;
    int y;
    String label;
    Rectangle bounds;
    Rectangle invisible=new Rectangle(-200,-200,0,0);

    int cooldown=0;
    boolean activated=false;
    String functionality;


    public Button(String name, int x, int y){
        this.x=x;
        this.y=y;
        label=name;
        functionality="";
        bounds=new Rectangle(x,y-70,ButtonWidth,ButtonHeight);

    }
    public Button(String name,int x,int y,String functionality)
    {
        this.label=name;
        this.x=x;
        this.y=y;
        this.functionality=functionality;

        bounds=new Rectangle(x,y-70,ButtonWidth,ButtonHeight);
    }

    public void activate(){
        activated=true;
    }


    public Rectangle getBounds() {

        return bounds;
    }
    public Rectangle timedBounds(){
        if(cooldown==0){

            cooldown=40;
            return bounds;

        }
        else
        {
            cooldown--;
            return invisible;}
    }
    public void Draw(Graphics g){


//            g.setFont(FontLoader.FireWorksFont.deriveFont(30f));

            switch(Opts.Character) {
                case 0:{   g.setColor(Color.WHITE); break;}
                case 1:{   g.setColor(Color.RED); break;}
                case 2:{   g.setColor(Color.BLACK);break;}
                case 3:{g.setColor(Color.YELLOW); break;}
                case 4:{g.setColor(Color.lightGray);break;}

            }
            g.drawString(label,x,y);

            if(activated){
                g.setColor(Color.black);
                g.setFont(FontLoader.FireWorksFont.deriveFont(40f));
                g.drawString(functionality,300,100);
            }

    }






}

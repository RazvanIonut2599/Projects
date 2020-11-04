package PaooGame.Items.Characters;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FontLoader;
import PaooGame.Options.Milestones;
import PaooGame.RefLinks;
import PaooGame.States.LoadingState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class King extends Character{


    BufferedImage kingImg;
    public int Faith=50;


    public King(RefLinks refLink,float x,float y){
        super(refLink,x,y);
    }

    public void Reset(){
        Faith=50;
    }

    public int getFaith(){return Faith;}
    public void setFaith(int x){Faith=x;}

    @Override
    public void Update() {
        if(Faith> Milestones.Highscore) {
            Milestones.Highscore = Faith;

        }

        if(Faith<75 && Faith>=50)
            kingImg= Assets.King.getFrame(0);
        else
            if(Faith<50) kingImg=Assets.King.getFrame(2);
            else
                if(Faith>=75 && Faith<100) kingImg=Assets.King.getFrame(1);
                else
                    kingImg=Assets.King.getFrame(3);


    }

    public void KingTalk(Graphics g){

        g.setFont(FontLoader.FireWorksFont.deriveFont(16f));
        g.setColor(Color.BLACK);

        if(Faith==0){
            g.drawImage(Assets.TextBubble,(int)this.x+20,(int)this.y-80,200,100,null);
            g.drawString("You are Fired ", (int)this.x+60,(int)this.y-40);
            g.drawString(" ", (int)this.x+60,(int)this.y-30);
        }


        //Faith lower than 30-24
        if(Faith>=24 && Faith<=30){
            g.drawImage(Assets.TextBubble,(int)this.x+20,(int)this.y-80,200,100,null);
            g.drawString("Move faster ", (int)this.x+60,(int)this.y-40);
            g.drawString(" ", (int)this.x+60,(int)this.y-30);
        }

        //Faith between 50-56
                if(Faith>=50 && Faith<=56) {
                    g.drawImage(Assets.TextBubble,(int)this.x+20,(int)this.y-80,200,100,null);
                    g.drawString("I hope you know ", (int)this.x+60,(int)this.y-40);
                    g.drawString("what you are doing ", (int)this.x+60,(int)this.y-30);
                }
                //Faith between 75-80
                if(Faith>=75 && Faith<=80){
                    g.drawImage(Assets.TextBubble,(int)this.x+20,(int)this.y-80,200,100,null);
                    g.drawString("Keep up the ", (int)this.x+60,(int)this.y-40);
                    g.drawString("good work  ", (int)this.x+60,(int)this.y-30);
                }

                //Faith over 100-110
                    if(Faith>=100 && Faith<=106){
                        g.drawImage(Assets.TextBubble,(int)this.x+20,(int)this.y-80,200,100,null);
                        g.drawString("Amazing ", (int)this.x+60,(int)this.y-40);
                        g.drawString("", (int)this.x+60,(int)this.y-30);
                    }


        //Faith for godlike;
        //Faith for beating highscore?

    }

    @Override
    public void Draw(Graphics g) {


        g.drawImage(kingImg,(int)x,(int)y,width,height,null);



        //Drawing Faith
        g.setFont(FontLoader.FireWorksFont.deriveFont(40f));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(Faith), (int)this.x+this.width+10,(int)this.y+50);

        KingTalk(g);

    }


}

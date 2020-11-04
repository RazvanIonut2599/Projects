package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Animation {

    double speed=8;
    int counter=0;
    int currentFrame=0;
    int nrFrames =0;
    boolean animated;
    Vector<BufferedImage> frames ;



    Animation(int FrameCount,boolean anima){
        nrFrames=FrameCount;
        frames=new Vector<BufferedImage>(10);
        animated=anima;

    }

    public void addImage(SpriteSheet from,int x,int y,int h,int w,boolean inverted){

        if(inverted==false)
            frames.add(from.crop(x,y,h,w));
        else
            frames.add(SpriteSheet.flip(from.crop(x,y,h,w)));

    }
    public void addImage(Animation img,boolean inverted){
        for(int i=0;i<img.nrFrames;i++)
            if(inverted==true)
            frames.add(SpriteSheet.flip(img.getFrame(i)));
            else
                frames.add(img.getFrame(i));
    }
    public void Empty(){

        while(frames.size()!=0)
            frames.remove(0);

        nrFrames=0;
        currentFrame=0;
        counter=0;

    }
    public void Remake(Animation img){
        Empty();
        this.nrFrames=img.nrFrames;
        this.addImage(img,false);

    }

    public void Update(){
        //animated
        if(animated){
            if(counter==speed){

                if(currentFrame==nrFrames -1 )
                    currentFrame=0;
                else
                    currentFrame++;

                counter=0;

            }
            else
                counter++;
        }
        else //nu este animata
        {
            currentFrame=0;
        }
    }
    public void Draw(Graphics g, int x, int y, int w, int h){
        g.drawImage(frames.elementAt(currentFrame),x,y,w,h,null);
        Update();
    }

    public BufferedImage getFrame(int x){return frames.elementAt(x);}
    public Vector<BufferedImage> getFrames(){return frames;}
    public void setAnima(boolean x){
        animated=x;
    }
    public void setSpeed(int x){
        speed=x;
    }
    public void setX(int x){
        currentFrame=x;
    }
    public void DrawAllFrames(Graphics g,int x,int y){
        for(int i=0;i<nrFrames;i++) {
           if(i%2==0)
               g.fillRect(x+i*50,y,50,50);
            g.drawImage(frames.elementAt(i), x + i * 50, y, 50, 50, null);

        }


    }
}


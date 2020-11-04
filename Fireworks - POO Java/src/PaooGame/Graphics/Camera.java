package PaooGame.Graphics;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;

import PaooGame.Items.Item;
import PaooGame.Tiles.Tile;

public class Camera {
    public float xOffset,yOffset;
    public static float CameraSpeed = 1.5f;


    public Camera(float xOff,float yOff){
        xOffset=xOff;
        yOffset=yOff;

    }

    public void centerOnItem(Item Hero){
        xOffset=Hero.GetX() - GameWindow.WindowWidth/2 ;
        yOffset=Hero.GetY() - GameWindow.WindowHeight/2;
    }

    public float getXoff(){return xOffset;}
    public float getYoff(){return yOffset;}

    public void setXoff(float x){xOffset=x;}
    public void setYoff(float y){yOffset=y;}

    public void move(float xAmt,float yAmt){
        xOffset+=xAmt;
        yOffset+=yAmt;


    }



}

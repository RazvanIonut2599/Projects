package PaooGame.Items.Things;

import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Thing;

import java.awt.*;

public class Fire extends Thing {
    Animation current;
    boolean visible=false;

    public static int FireWidth =30;
    public static int FireHeight=50;


    Fire(int TileX,int TileY){

        super(FireWidth,FireHeight,TileX,TileY);

        current= Assets.fire;
    }
    public void Visibility(){
        visible=!visible;
    }
    public void Invisibility(){visible=false;}

    @Override
    public void Draw(Graphics g, float xOff, float yOff) {
     if(visible)
        current.Draw(g,(int)x,(int)y,FireWidth,FireHeight);
       }
}

package PaooGame.Items.Things;

import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class FireBunch {
    public Vector<Fire> fires;
    public int fireNr;

    public int firesBurning=0;

    public FireBunch(int x,int y) {
        fireNr = 6;
        fires = new Vector<Fire>(5);
        Random rand = new Random();

        for(int i=0;i<fireNr ;i++) {
            fires.add(new Fire(x, y));
            fires.elementAt(i).setX(fires.elementAt(i).getX() + i*10 +10);
            fires.elementAt(i).setY(fires.elementAt(i).getY() + i*10 +10);

        }




    }

    public void addFire(){
        if(firesBurning<fireNr) {

       fires.elementAt(firesBurning).Visibility();
       firesBurning++;

        }
    }
    public void stopFire(){
        if(firesBurning>0)
        {

            firesBurning--;
            fires.elementAt(firesBurning).Visibility();

        }

    }
    public void ResetFires(){

            for(int i=0;i<fireNr;i++)
               fires.elementAt(i).Invisibility();
            firesBurning=0;

    }



    public void Draw(Graphics g){
        for(int i=0;i<fireNr -1 ;i++)
        {
            fires.elementAt(i).Draw(g,0,0);

        }
    }
}

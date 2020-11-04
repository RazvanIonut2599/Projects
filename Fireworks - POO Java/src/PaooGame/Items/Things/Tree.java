package PaooGame.Items.Things;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FontLoader;
import PaooGame.Items.Thing;
import PaooGame.Options.Milestones;
import PaooGame.Options.Opts;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Tree extends Thing {

    public static int defaultTreeH=Tile.TILE_HEIGHT*2 - 10;
    public static int defaultTreeW= Tile.TILE_WIDTH -10;
//TEMP MaxHp=25; - easy mode (5 more lives for trees);
    int MaxHp=25;



    int HP;
    public FireBunch fires;
    int TileX,TileY;

    public int getTileX(){return TileX;}
    public int getTileY(){return TileY;}
    public void setTileX(int x){TileX=x;}
    public void setTileY(int x){TileY=x;}

    public int getMaxHp(){return MaxHp;}
    public void setMaxHp(int x){MaxHp=x;}
    public int getHp(){return HP;}
    public void setHP(int x){HP=x;}


    RefLinks refLinks;

    int cooldown; // timpul folosit sa adaugam foc
    int stopcooldown=5; //timpul necesar pentru a stinge un alt foc
    int firecooldown;    //timpul pe care il asteapta un foc pentru a da damage



    public Tree(int TileX,int TileY,RefLinks refLinks){

        super(Tree.defaultTreeW,Tree.defaultTreeH,TileX,TileY  );
        switch (Opts.Diff){
            case 0:{cooldown =20; firecooldown=400; break;}
            case 1:{cooldown =30; firecooldown=200; break;}
            case 2:{cooldown =20;  firecooldown=170; break;}

        }


        this.TileX=TileX;
        this.TileY=TileY;
        this.refLinks=refLinks;

        fires=new FireBunch(TileX,TileY-1);
        bounds.y-=Tile.TILE_HEIGHT;
        bounds.x+=30;
        bounds.width-=50;
        bounds.height-=10;



        HP=MaxHp;

    }
    public void Update(){

       if(firecooldown==0) {
           if (fires.fireNr > 0) HP -= fires.firesBurning;
            firecooldown=240;
       }
       else
           firecooldown--;



        if(HP<0) HP=0;



    }

    public void AddFire(){

        if(cooldown==0) {

            fires.addFire();
            cooldown=180;
        }
        else
            cooldown--;
    }
    public void ResetTree(){
       //Reset fires
        ResetFires();
        //reset HPS
        MaxHp=25;
        HP=MaxHp;
        //reset cooldowns
        switch (Opts.Diff){
            case 0:{cooldown =20; firecooldown=400; break;}
            case 1:{cooldown =30; firecooldown=200; break;}
            case 2:{cooldown =20;  firecooldown=170; break;}

        }
    }

    public void ResetFires(){
        fires.ResetFires();
    }
    public void StopFire(){
        if(stopcooldown==0){
           if(this.HP >0)
           {
               if(fires.firesBurning>0)
                   refLinks.GetKing().Faith+=2;

               fires.stopFire();
               Milestones.Firesout++;

           }
           else //replant
           {

               if(MaxHp>0)
               {
                   MaxHp-=5;
                   HP=MaxHp;
                     Milestones.Treesplanted++;
                     ResetFires();
                   }
           }
            stopcooldown=20;
        }
        else
            stopcooldown--;

    }

    private void DrawHearts(Graphics g){
            int nr=MaxHp/5;
            for(int i=0;i<nr;i++)
                g.drawImage(Assets.Heart,(int)(this.x+i*10),(int)this.y+Tile.TILE_HEIGHT-20,30,30,null);

    }
    @Override
    public void Draw(Graphics g, float xOff, float yOff) {




        if(HP>0) {

            //g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
            g.drawImage(Assets.treeAlive, (int) x, (int) (y - Tile.TILE_HEIGHT), width, height, null);


            fires.Draw(g);
        }
        else {
            g.drawImage(Assets.treeDead, (int) x, (int) (y - Tile.TILE_HEIGHT), width, height, null);

        }

        DrawHearts(g);
        g.setFont(FontLoader.FireWorksFont);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(HP),(int)this.x,(int)this.y+Tile.TILE_HEIGHT-20);

    }

    //helps at loading
    public void ActivateXFires(int x){
        ResetFires();
        for(int i=0;i<x;i++)
            AddFire();
    }


}

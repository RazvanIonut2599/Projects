package PaooGame.Items;

import PaooGame.Graphics.Assets;

import PaooGame.Tiles.Tile;

import java.awt.*;

public abstract class Thing {

    //unde se afla obiectul (top left corner)
    protected float x;
    protected float y;
    protected int width;
    protected int height;


    protected Rectangle bounds;

    protected boolean Solid=true;

    public Thing(int w,int h,int TileX,int TileY)
    {
        this.x= TileX* Tile.TILE_WIDTH;
        this.y= TileY*Tile.TILE_HEIGHT;
        this.width=w;
        this.height=h;



        bounds= new Rectangle((int)(this.x),(int)(this.y),width,height);
    }

    public float getX(){return x;}
    public float getY(){return y;}

    public void setX(float x){this.x=x;}
    public void setY(float y){this.y=y;}

    public Rectangle getBounds(){return bounds;}
    public abstract void Draw(Graphics g,float xOff,float yOff);




}

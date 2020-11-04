package PaooGame.Maps;


import PaooGame.Items.Things.TreeBunch;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import java.awt.*;

import java.util.Random;



/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width=10;          /*!< Latimea hartii in numar de dale.*/
    private int height=10;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles  =new int[width][height];     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/


    private TreeBunch trees;

    public Map(RefLinks refLink)
    {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        trees=new TreeBunch(8,refLink);


    }
    public TreeBunch getTrees(){return trees;}
    public void reset(){

        trees.EmptyVector();

        LoadWorld();

        for(int i=0;i<trees.treeNr;i++)
            trees.trees.elementAt(i).ResetTree();
    }
    public void updateTrees(){
        trees.Update();

    }
    public void burnTrees() {
        Random rand=new Random();

        trees.trees.elementAt(rand.nextInt(trees.treeNr)).AddFire();

        }
    public  void Update() {
        updateTrees();
        burnTrees();



    }



    public void DrawVisibile(Graphics g) {
        int i, j;

        //BIG MAP DRAW
        for (i = 0; i < width; i++)
            for (j = 0; j < height; j++) {
                //Deseneaza solul
                GetTile(i, j).Draw(g, (int) (i * Tile.TILE_WIDTH), (int) (j * Tile.TILE_HEIGHT ));

                //Deseneaza obiectele din camera

            }



    }
    public void DrawTrees(Graphics g){
        for(int i=0;i<trees.treeNr;i++){
            trees.trees.elementAt(i).Draw(g,0,0);
        }
    }
    public void Draw(Graphics g) {

        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        DrawVisibile(g);
        DrawTrees(g);



    }
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            System.out.println("T == NULL");
            return Tile.grassTile;
        }
        return t;
    }
    public void LoadWorld()
    {
        MapGenerator.GenerateMap(tiles,trees,8,width-1,height-1);

    }
}

package PaooGame.Tiles;



import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;



import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    //Tile-urile sunt orientate dupa cum ar putea fi folosite (endTileDown-are intrarea spre N , endTileLeft are intrarea spre E)
        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    //Others
    public static Tile grassTile           = new GrassTile(0);
    public static Tile centerTile          = new Center(1);






    public static final int TILE_WIDTH  = GameWindow.WindowWidth/10;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = GameWindow.WindowHeight/10;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/


    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;


    }


    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {


    }


    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala

        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
       }
    public void Draw(Graphics g, int x, int y,int w,int h)
    {
        /// Desenare dala


            g.drawImage(img, x, y, w, h, null);
        }

    public int GetId()
    {
        return id;
    }
}

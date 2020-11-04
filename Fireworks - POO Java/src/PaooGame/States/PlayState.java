package PaooGame.States;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Items.Characters.Hero;
import PaooGame.Items.Characters.King;
import PaooGame.Options.Milestones;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private King king;




    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
//se citesc din baza de date milestones-urile;


        ///Construieste harta jocului
        map = new Map(refLink);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        ///Construieste eroul si regele
        king=new King(refLink,10,GameWindow.WindowHeight-100);
        refLink.SetKing(king);
        hero = new Hero(refLink,15, GameWindow.WindowHeight/2- Tile.TILE_HEIGHT);
        refLink.SetHero(hero);



    }



    /*! \fn public void Update()
            \brief Actualizeaza starea curenta a jocului.
         */
    @Override
    public void Update()
    {
        map.Update();
        hero.Update();
        king.Update();

        if(refLink.GetKeyManager().interact) {

            refLink.GetGame().setState("PAUSE");

        }
        if(king.Faith == 0) {

            refLink.GetGame().setState("OVER");

        }


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);
        king.Draw(g);
    }
}

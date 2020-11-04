package PaooGame.States;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FontLoader;
import PaooGame.Items.Button;
import PaooGame.Items.Characters.Hero;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
   Hero hero;
   Button [] list;
   int tutorial=0;
   int cooldown=0;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        hero=new Hero(refLink,GameWindow.WindowWidth/2,GameWindow.WindowHeight/2);
        list=new Button[4];
        list[0]=new Button("Start Game",GameWindow.WindowWidth-300,100);
        list[1]=new Button("About",GameWindow.WindowWidth-400,GameWindow.WindowHeight-400);
        list[2]=new Button("Options",GameWindow.WindowWidth-300,GameWindow.WindowHeight-300);
        list[3]=new Button("Quit Game",GameWindow.WindowWidth-400,GameWindow.WindowHeight-100);


    }
    public Button[] getButtons(){return list;}
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        hero.Update();
        if(StateSwap>0)
        StateSwap--;

        if(refLink.GetKeyManager().tutorial)
            if(cooldown==0)
        {

            if(tutorial<2)
                tutorial++;
            else
                tutorial=0;
            cooldown=50;
        }
        if(cooldown>0)
        cooldown--;



    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    { g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);

        g.setColor(Color.RED);
//        g.setFont(FontLoader.FireWorksFont.deriveFont(72f));
        g.drawString("FireWorks",200,100);

        for(int i=0;i<list.length;i++)
            list[i].Draw(g);

        //MAKE TUTORIAL PAGE
        if(tutorial==1)
        {
            g.drawImage(Assets.tutorial1,0,0,800,400,null);
            g.setFont(FontLoader.FireWorksFont.deriveFont(30f));
            g.setColor(Color.BLACK);
            g.drawString("Press T  to go to the next page", 100, GameWindow.WindowHeight - 200);

        }
        if(tutorial==2)
        {
            g.drawImage(Assets.tutorial2,-8,0,800,400,null);
            g.setFont(FontLoader.FireWorksFont.deriveFont(30f));
            g.setColor(Color.BLACK);
            g.drawString("Press T  to close the tutorial ", 100, GameWindow.WindowHeight - 200);

        }
        if(tutorial==0)
        {
//            g.setFont(FontLoader.FireWorksFont.deriveFont(30f));
            g.setColor(Color.BLACK);
            g.drawString("Press T  to see a tutorial ", 100, GameWindow.WindowHeight - 200);
        }

        hero.Draw(g);


    }
}

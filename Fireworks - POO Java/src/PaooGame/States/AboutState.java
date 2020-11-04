package PaooGame.States;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Button;
import PaooGame.Items.Characters.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.concurrent.BlockingDeque;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    Hero hero;
    Button[] list;


    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
        hero=new Hero(refLink,GameWindow.WindowWidth/2,GameWindow.WindowHeight/2);

        list =new Button[1];
        list[0]=new Button("Back",GameWindow.WindowWidth-300,100);

    }
    public Button[] getButtons(){return list;}

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        hero.Update();
        if(StateSwap>0)
        StateSwap--;
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        g.setColor(Color.black);
        g.drawString("  This is a project for PAOO made by Luca Razvan from 1209B. ",0,200) ;
        g.drawString("  I used sprites from Pinterest for the trees and sprites  " ,0,300);
        g.drawString("from Shovel Knight for the characters. " ,0,400);


        g.drawString("  The font Ace Records was found online from 1001 FONTS."  ,0,500);
        g.drawString("  Thanks photoshop for making this easier." ,0,600);



            list[0].Draw(g);
        hero.Draw(g);

    }
}

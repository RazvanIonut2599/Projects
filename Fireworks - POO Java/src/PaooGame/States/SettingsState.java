package PaooGame.States;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Button;
import PaooGame.Items.Characters.Hero;
import PaooGame.Options.Milestones;
import PaooGame.Options.Opts;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    Hero hero;
    Button[] list;
    static String Difficulty;

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
        hero=new Hero(refLink,GameWindow.WindowWidth/2,GameWindow.WindowHeight/2);

        list =new Button[3];
        list[0]=new Button("Back",GameWindow.WindowWidth-300,100);
        list[1]=new Button("Change Character",100,100);
        list[1].getBounds().width=list[1].getBounds().width*2;
        list[2]=new Button("Change Difficulty",100,300);
        list[2].getBounds().width=list[2].getBounds().width*2;


    }
    public Button[] getButtons(){return list;}
    public static void SetCharacter(){

        switch (Opts.Character)
        {
            case 0:{

                Assets.heroIdle.Remake(Assets.plagueheroIdle);
                Assets.heroRight.Remake(Assets.plagueheroRight);
                Assets.heroLeft.Remake(Assets.plagueheroLeft);
                Assets.heroAttackRight.Remake(Assets.plagueheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.plagueheroAttackLeft);

                break;}
            case 1:{
                Assets.heroIdle.Remake(Assets.moleheroIdle);
                Assets.heroRight.Remake(Assets.moleheroRight);
                Assets.heroLeft.Remake(Assets.moleheroLeft);
                Assets.heroAttackRight.Remake(Assets.moleheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.moleheroAttackLeft);
                break;}
            case 2:{
                Assets.heroIdle.Remake(Assets.RedheroIdle);
                Assets.heroRight.Remake(Assets.RedheroRight);
                Assets.heroLeft.Remake(Assets.RedheroLeft);
                Assets.heroAttackRight.Remake(Assets.RedheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.RedheroAttackLeft);
                break;}
            case 3:{
                Assets.heroIdle.Remake(Assets.KingheroIdle);
                Assets.heroRight.Remake(Assets.KingheroRight);
                Assets.heroLeft.Remake(Assets.KingheroLeft);
                Assets.heroAttackRight.Remake(Assets.KingheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.KingheroAttackLeft);
                break;}
            case 4:{
                Assets.heroIdle.Remake(Assets.TinkerheroIdle);
                Assets.heroRight.Remake(Assets.TinkerheroRight);
                Assets.heroLeft.Remake(Assets.TinkerheroLeft);
                Assets.heroAttackRight.Remake(Assets.TinkerheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.TinkerheroAttackLeft);
                break;}


        }
    }
    public static void SetDifficulty(){

        switch(Opts.Diff){
            case 0:{Difficulty="Easy";break;}
            case 1:{Difficulty="Medium";break;}
            case 2:{Difficulty="Hard";break;}

        }
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {

        hero.Update();


        if(StateSwap>0)
        StateSwap--;


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);
        for(int i=0;i<list.length;i++) {
            list[i].Draw(g);

            switch(i){
                case 1:{
                    String string="";
                        switch(Opts.Character){
                            case 0:{string="Plague Knight";break;}
                            case 1:{string="Mole Knight";break;}
                            case 2:{string="Red the Artist";break;}
                            case 3:{string="King";break;}
                            case 4:{string="Tinker Knight";break;}
                        }
                        g.drawString(string,300,100);break;}
                case 2:{

                SetDifficulty();
                    g.drawString(Difficulty,300,300);

            }

        }
        Milestones.UpdateMilestones();
        if(!Milestones.OK) {
            g.drawString("You did not unlock this character yet", 20, 500);
            g.drawString("You have to get ",20,400);
            switch(Opts.Character){

                case 1:{g.drawString("50 fires put out  You have "+Milestones.Firesout,300,400);;break;}
                case 2:{g.drawString("25 trees planted  You have "+Milestones.Treesplanted,300,400);break;}
                case 3:{g.drawString("a highscore of 150  You have "+Milestones.Highscore,300,400);break;}
                case 4:{g.drawString("a highscore of 100  You have "+Milestones.Highscore,300,400);break;}


            }
        }
        hero.Draw(g);


    }}



}

package PaooGame.Items.Characters;

import java.awt.*;
import java.rmi.AlreadyBoundException;
import java.util.Collection;
import java.util.Vector;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Animation;


import PaooGame.Items.Button;
import PaooGame.Items.Things.TreeBunch;
import PaooGame.Maps.Map;
import PaooGame.Options.Milestones;
import PaooGame.Options.Opts;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.*;
import PaooGame.Tiles.Tile;

import javax.swing.*;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    //private BufferedImage image;
    private Animation current;
    private int state;
    private char facing;

    private int Collision;
    private char CollisionTowards;




     /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        //super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        super(refLink,x,y);
            ///Seteaza imaginea de start a eroului
        current=Assets.heroIdle;
        state=0;
        facing='D'; //down

        bounds.width=width/2;



    }

    public void Reset(){
        this.x=15;
        this.y=GameWindow.WindowHeight/2- Tile.TILE_HEIGHT;

        state=0;
        facing='D'; //down
    }

    private void UpdateState(){

        if(refLink.GetKeyManager().space)
            state=2; //attacking
        else
        if(refLink.GetKeyManager().left ||refLink.GetKeyManager().right ||refLink.GetKeyManager().down ||refLink.GetKeyManager().up )
            state=1; //walking
        else
            state=0; //idle
    }
    private void UpdateFace(){
        //facing update;
        if(state==0)
        {
            current=Assets.heroIdle;


            Assets.heroRight.setX(0);
            Assets.heroLeft.setX(0);
            Assets.heroAttackRight.setX(0);
            Assets.heroAttackLeft.setX(0);
        }
            // Right Left
        else
        if(state==1) {
             if (refLink.GetKeyManager().left) {
                current = Assets.heroLeft;
                facing='L';
                 Assets.heroIdle.setX(0);
                 Assets.heroRight.setX(0);
                 Assets.heroAttackRight.setX(0);
                 Assets.heroAttackLeft.setX(0);

            }
            if (refLink.GetKeyManager().right) {
                current = Assets.heroRight;
                facing='R';
                Assets.heroIdle.setX(0);
                Assets.heroLeft.setX(0);
                Assets.heroAttackRight.setX(0);
                Assets.heroAttackLeft.setX(0);
            }
            if(refLink.GetKeyManager().up || refLink.GetKeyManager().down)
            {

                if(facing=='L'){ current=Assets.heroLeft;
                    Assets.heroIdle.setX(0);
                    Assets.heroRight.setX(0);
                    Assets.heroAttackRight.setX(0);
                    Assets.heroAttackLeft.setX(0); }
                else
                {current=Assets.heroRight;
                    Assets.heroIdle.setX(0);
                    Assets.heroLeft.setX(0);
                    Assets.heroAttackRight.setX(0);
                    Assets.heroAttackLeft.setX(0);}


            }

        }
        else
        if(state==2)
        {switch (facing){
            case 'L':{current=Assets.heroAttackLeft;
                Assets.heroIdle.setX(0);
                Assets.heroRight.setX(0);
                Assets.heroLeft.setX(0);
                Assets.heroAttackRight.setX(0);

            break;}
            case 'R':{current=Assets.heroAttackRight;
                Assets.heroIdle.setX(0);
                Assets.heroRight.setX(0);
                Assets.heroLeft.setX(0);

                Assets.heroAttackLeft.setX(0);
            break;}
            default:{break;}

        }}
        //vedem in ce directie ataca
    }
    private void UpdateBounds(){
        bounds.x=(int)x+10;
        bounds.y=(int)y +10;
        bounds.height=(int)height-20;
    }
    private int Collision(){


        if(facing=='R')
            attackBounds.x=(int)(x+width);
        else
            attackBounds.x=(int)(x-attackBounds.width);

        attackBounds.y=(int)y;

        TreeBunch trees=refLink.getTrees();

            if(!refLink.GetKeyManager().space)
            {

        for(int i=0;i<trees.treeNr;i++) {

            if (this.bounds.intersects(trees.trees.elementAt(i).getBounds())) {

                if(trees.trees.elementAt(i).getBounds().x>this.bounds.x) //coliziune din dreapta
                CollisionTowards='R';
                else if(trees.trees.elementAt(i).getBounds().x<=this.bounds.x) //coliziune din stanga
                    CollisionTowards='L';
                else if(trees.trees.elementAt(i).getBounds().y<=this.bounds.y) //coliziune de sub
                    CollisionTowards='U';
                else if(trees.trees.elementAt(i).getBounds().y>this.bounds.y) //coliziune de deasupra
                    CollisionTowards='D';


                return i; // 0->7
            }
        }
                if(this.bounds.intersects(refLink.GetKing().getBounds())) {
                    if(refLink.GetKing().getBounds().x>this.bounds.x) //coliziune din dreapta
                        CollisionTowards='R';
                    else if(refLink.GetKing().getBounds().x<=this.bounds.x) //coliziune din stanga
                        CollisionTowards='L';
                    else if(refLink.GetKing().getBounds().y<=this.bounds.y) //coliziune de sub
                        CollisionTowards='U';
                    else if(refLink.GetKing().getBounds().y>this.bounds.y) //coliziune de deasupra
                        CollisionTowards='D';
                    return 8;
                }

        return -1;}
            else
            {
                for(int i=0;i<trees.treeNr;i++) {

                    if (this.attackBounds.intersects(trees.trees.elementAt(i).getBounds())) {

                        return i; // 0->7
                    }
                }
                return -1;
            }





    }
    private boolean Collision(Rectangle player,Rectangle button){
        if(player.intersects(button)) return true;
        else
            return false;
    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        Move();
            ///Actualizeaza imaginea

        UpdateState();
        UpdateFace();



    }


    private void StateInput(){
        if(!refLink.GetKeyManager().space) {
            if (refLink.GetKeyManager().up)
                if (y - speed > 0)
                    yMove = -speed;
            if (refLink.GetKeyManager().down)
                if (y + speed < GameWindow.WindowHeight - height - 20)
                    yMove = speed;
            if (refLink.GetKeyManager().left)
                if (x - speed > 0)
                    xMove = -speed;
            if (refLink.GetKeyManager().right)
                if (x + speed < GameWindow.WindowWidth - width)
                    xMove = speed;
        }


        if(State.GetState() instanceof MenuState)
        {if(refLink.GetKeyManager().space &&State.StateSwap==0)
        {
            Button[] list = (((MenuState) State.GetState()).getButtons());
            for(int i=0;i<list.length;i++)
                if(Collision(this.bounds,list[i].getBounds()))
                    switch(i){
                        // 0 is Start
                        case 0:{
                            refLink.GetGame().setState("LOAD");

                            break;}
                        //1 is About
                        case 1:{
                            refLink.GetGame().setState("ABOUT");
                            break;}
                        //2 is Options
                        case 2:{
                            refLink.GetGame().setState("OPT");
                            break;}
                        //3 is Quit
                        case 3:{
                            refLink.GetGame().StopGame();
                            break;}



                    }
        }

        }
        else
        if (refLink.GetKeyManager().space && State.StateSwap==0) {
            Button [] list = null;
            if(State.GetState() instanceof AboutState)
            {list = ( ((AboutState) State.GetState()).getButtons());

                for (int i = 0; i < list.length; i++)
                    if (Collision(this.bounds, list[i].getBounds()))
                        switch (i) {
                            // 0 is Back
                            case 0: {
                                refLink.GetGame().setState(State.GetPreviousState());
                                break;
                            }
                            default: {
                                break;
                            }

                        }
            }
            else
            if(State.GetState() instanceof PauseState){
                list = ( ((PauseState) State.GetState()).getButtons());

                for (int i = 0; i < list.length; i++)
                    if (Collision(this.bounds, list[i].getBounds()))
                        switch (i) {
                            // 0 is Back
                            case 0: {
                                refLink.GetGame().setState("Play");
                                break;
                            }
                            case 1: {
                                refLink.GetGame().setState("Opt");
                                break;
                            }
                            case 2: {
                                refLink.GetGame().setState("Menu");
                                break;
                            }
                            case 3:{
                                Game.DBManager.Load();

                                refLink.GetGame().setState("LOAD");
                                break;
                            }
                            case 4:{
                                list[i].activate();
                                Game.DBManager.Save();



                                break;
                            }
                            default: {
                                break;
                            }

                        }
            }
            else
            if(State.GetState() instanceof SettingsState)
            {list = ( ((SettingsState) State.GetState()).getButtons());

                for (int i = 0; i < list.length; i++)
                    if (Collision(this.bounds, list[i].timedBounds()))
                        switch (i) {
                            // 0 is Back
                            case 0: {
                                if(Milestones.OK)
                                refLink.GetGame().setState(State.GetPreviousState());

                                break;
                            }
                            case 1: {

                                if(Opts.Character==4)
                                    Opts.Character=0;
                                else
                                    Opts.Character++;

                                //0 plague 1 mole 2 red 3 king 4 tinker
                                SettingsState.SetCharacter();
                                break;
                            }
                            case 2: {
                                if(Opts.Diff==2)
                                    Opts.Diff = 0;
                                else
                                    Opts.Diff++;



                                break;
                            }


                            default: {
                                break;
                            }

                        }
            }


        }




}
    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        UpdateBounds();
        xMove = 0;
        yMove = 0;

        if(State.GetState() instanceof PlayState)
        {
            Collision = Collision();

            if (!refLink.GetKeyManager().space) {
                if (Collision == -1) {    ///Verificare apasare tasta "sus"
                    if (refLink.GetKeyManager().up) {
                        if (y - speed > 0)
                            yMove = -speed;

                    }

                    ///Verificare apasare tasta "jos"
                    if (refLink.GetKeyManager().down) {
                        if (y + speed < GameWindow.WindowHeight - height - 20)
                            yMove = speed;


                    }

                    ///Verificare apasare tasta "left"
                    if (refLink.GetKeyManager().left) {

                        if (x - speed > 0)
                            xMove = -speed;


                    }
                    ///Verificare apasare tasta "dreapta"
                    if (refLink.GetKeyManager().right) {

                        if (x + speed < GameWindow.WindowWidth - width)
                            xMove = speed;

                    }


                } else //coliziune vrem sa putem merge in directia opusa
                {
                    if (refLink.GetKeyManager().up && CollisionTowards == 'D') {
                        if (y - speed > 0)
                            yMove = -speed;

                    }
                    ///Verificare apasare tasta "jos"
                    if (refLink.GetKeyManager().down && CollisionTowards == 'U') {
                        if (y + speed < GameWindow.WindowHeight)
                            yMove = speed;
                    }

                    ///Verificare apasare tasta "left"
                    if (refLink.GetKeyManager().left && CollisionTowards == 'R') {

                        if (x - speed > 0)
                            xMove = -speed;
                    }
                    ///Verificare apasare tasta "dreapta"
                    if (refLink.GetKeyManager().right && CollisionTowards == 'L') {

                        if (x + speed < GameWindow.WindowWidth)
                            xMove = speed;

                    }


                }
            } else {
                if (Collision < 8 && Collision != -1) {
                    refLink.getTrees().trees.elementAt(Collision).StopFire();

                }

            }

        }
        else //suntem in alt state
            StateInput();






    }

    
    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {

        current.Draw(g,(int)(x),(int)(y),width,height);


/*
        g.setColor(Color.RED);
        if(refLink.GetKeyManager().space)
            g.fillRect(attackBounds.x,attackBounds.y,attackBounds.width,attackBounds.height);

 */

    }
}

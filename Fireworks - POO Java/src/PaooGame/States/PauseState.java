package PaooGame.States;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Button;
import PaooGame.Items.Characters.Hero;
import PaooGame.RefLinks;

import java.awt.*;

public class PauseState extends State {
    Hero hero;
    Button[] list;

    public PauseState(RefLinks refLink){
        super(refLink);
        hero=new Hero(refLink,GameWindow.WindowWidth/2,GameWindow.WindowHeight/2);
        list =new Button[5];
        list[0]=new Button("Back",GameWindow.WindowWidth-300,100);
        list[1]=new Button("Options",GameWindow.WindowWidth-300,300);
        list[2]=new Button("Main Menu",GameWindow.WindowWidth-300,500);

        list[3]=new Button("Load Game",GameWindow.WindowWidth-600,200);
        list[4]=new Button("Save Game", GameWindow.WindowWidth-300,200,"Saved");


    }
    public Button[] getButtons(){return list;}
    @Override
    public void Update() {
        hero.Update();

        if(StateSwap>0)
        StateSwap--;
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);

        for(int i=0;i<list.length;i++)
            list[i].Draw(g);
        hero.Draw(g);
    }
}

package PaooGame.States;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FontLoader;
import PaooGame.Options.Milestones;
import PaooGame.RefLinks;

import java.awt.*;

public class GameOverState extends State {
    public GameOverState(RefLinks refLink){
        super(refLink);
    }

    @Override
    public void Update() {
        if(refLink.GetKeyManager().space)
            refLink.GetGame().setState("MENU");

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);

        g.setColor(Color.GREEN);
        g.setFont(FontLoader.FireWorksFont.deriveFont(100f));
        g.drawString("Game Over", GameWindow.WindowWidth/3,GameWindow.WindowHeight/3);


        g.setColor(Color.BLACK);
        g.setFont(FontLoader.FireWorksFont.deriveFont(50f));
        g.drawString("Press Space to go back to the main menu", 100,GameWindow.WindowHeight/3+100);


    }
}

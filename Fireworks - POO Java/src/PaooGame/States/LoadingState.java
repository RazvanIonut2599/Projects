package PaooGame.States;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FontLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.Random;

public class LoadingState extends State{
    int cooldown=100;
    String [] loadingTexts={
            "Did you know FireWorks has unlockables? Head to the options menu.",
            "The King is a playable character you can unlock. His majesty got bored",
            "Red is a character that shouldn't be in this game",
            "Plague Knight is the first character created in this game",
            "Tinker Knight has a weird way of putting out fires"
    };
    Random rand=new Random();
    int randomText;

   public LoadingState(RefLinks refLink){
       super(refLink);
   }



    @Override
    public void Update() {
       if(cooldown==0) {

           if (refLink.GetKeyManager().space)
           {
               refLink.GetKing().Reset();
               refLink.GetMap().reset();
               refLink.GetHero().Reset();

               refLink.GetGame().setState("PLAY");
           cooldown=100;
           randomText=rand.nextInt(5);
           }

       }
       else
           cooldown--;


        if(StateSwap>0)
       StateSwap--;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.BG,0,0, GameWindow.WindowWidth,GameWindow.WindowHeight,null);
        Assets.heroRight.Draw(g,30,GameWindow.WindowHeight-200,50,70);
        g.setFont(FontLoader.FireWorksFont.deriveFont(50f));
        g.setColor(Color.black);
        if(cooldown==0)
            g.drawString("Press space to start",100,GameWindow.WindowHeight-100);
        else
            g.drawString("Loading",100,GameWindow.WindowHeight-100);

        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
        g.setColor(Color.black);


        g.drawString(loadingTexts[randomText],50,300);
    }
}

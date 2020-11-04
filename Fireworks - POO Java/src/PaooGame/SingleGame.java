package PaooGame;

import PaooGame.GameWindow.GameWindow;

public class SingleGame {
    private static final Game game=new Game("FireWorks",GameWindow.WindowWidth, GameWindow.WindowHeight);

    private SingleGame(){

    }
    public static Game getGame(){
        return game;
    }

}

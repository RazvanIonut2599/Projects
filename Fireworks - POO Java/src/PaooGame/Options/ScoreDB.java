package PaooGame.Options;

import PaooGame.Game;
import PaooGame.SingleGame;

public class ScoreDB {
    public static int score;

    public static void UpdateScore(){
        score= SingleGame.getGame().getRefLink().GetKing().Faith;
    }

}

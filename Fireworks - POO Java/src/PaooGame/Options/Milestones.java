package PaooGame.Options;

import PaooGame.Game;

import java.sql.ResultSet;

public class Milestones {
    public static int Highscore;
    public static int Treesplanted;
    public static int Firesout;
    public static boolean OK=false;

    public static void UpdateMilestones(){
        switch (Opts.Character){
            case 0:{Milestones.OK=true;break;}
            case 1:{if(Milestones.Firesout>=50) Milestones.OK=true;
            else Milestones.OK=false;
                break;}
            case 2:{
                if(Milestones.Treesplanted>=25) Milestones.OK=true;
                else Milestones.OK=false;
                break;}
            case 3:{
                if(Milestones.Highscore>=150) Milestones.OK=true;
                else Milestones.OK=false;
                break;}
            case 4:{
                if(Milestones.Highscore>=100) Milestones.OK=true;
                else Milestones.OK=false;
                break;}

        }
    }




}

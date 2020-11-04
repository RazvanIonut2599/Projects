package PaooGame.Options;
import PaooGame.Items.Things.Tree;

import javax.xml.crypto.Data;
import java.sql.*;


public class DataBaseManager {

    private static Connection con =null;
    public static boolean hasData=false;



    private void getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:Fireworks.db");
            Initialise();
        }
        catch(Exception e){System.out.println(e);}
    }
    private void Initialise(){
        if(!hasData) {
        //1st time
            try {
                hasData = true;
                Statement state = con.createStatement();
                ResultSet res=state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='options'");
                if(!res.next())
                {
                    System.out.println("Building OPTIONS with prepopulated values");
                    Statement state2=con.createStatement();
                    state2.execute("CREATE TABLE options (character integer,"+"difficulty integer);");

                    PreparedStatement prep=con.prepareStatement("INSERT INTO options VALUES (?,?);");
                    prep.setInt(1,0);
                    prep.setInt(2,1);
                    prep.execute();
                }
                res=state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='milestones'");
                if(!res.next())
                {
                    System.out.println("Building MILESTONES with prepopulated values");
                    Statement state2=con.createStatement();
                    state2.execute("CREATE TABLE milestones(highscore integer,"+"firesout integer,"+"treesplanted integer);");

                    state2.execute("INSERT INTO milestones VALUES (0,0,0);");


                }
                res=state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='trees'");
                if(!res.next()){
                    System.out.println("Building TREES with prepopulated values");
                    Statement state2=con.createStatement();
                    state2.execute("CREATE TABLE trees (ID integer, x integer , y integer , fires integer , maxhp integer, currenthp integer);");


                    state2.execute("INSERT INTO trees VALUES"+
                            "(0,0,0,0,0,0) ,"
                            +"(1,0,0,0,0,0) ,"
                            +"(2,0,0,0,0,0),"
                            +"(3,0,0,0,0,0),"
                            +"(4,0,0,0,0,0),"
                            +"(5,0,0,0,0,0),"
                            +"(6,0,0,0,0,0),"
                            +"(7,0,0,0,0,0)"

                            + ";");

                }
                res=state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='scores'");
                if(!res.next()){
                    System.out.println("Building SCORES with prepopulated values");
                    Statement state2=con.createStatement();
                    state2.execute("CREATE TABLE scores (score integer);");

                    state2.execute("INSERT INTO scores VALUES ( 0 );");


                }


            }
            catch(Exception e){System.out.println(e);}

        }
    }

    public void getOptions() {
        if (con == null) {
            System.out.println("Connected");
            getConnection();
        }
        try {
            Statement state = con.createStatement();
            ResultSet rs= state.executeQuery("SELECT character, difficulty FROM options");
            if(rs!=null){
                Opts.Character=rs.getInt("character");
                Opts.Diff=rs.getInt("difficulty");
            }
        }
        catch(Exception e){}

    }
    public void getMilestones() {
        if (con == null) {
            getConnection();
        }
        try {
            Statement state = con.createStatement();
            ResultSet rs=state.executeQuery("SELECT highscore , firesout , treesplanted FROM milestones");
            if(rs!=null){
                Milestones.Highscore=rs.getInt("highscore");
                Milestones.Firesout=rs.getInt("firesout");
                Milestones.Treesplanted=rs.getInt("treesplanted");
            }
        }
        catch(Exception e){System.out.println(e);}

    }
    //LOAD
    public void loadTree(int x,Statement state) throws SQLException{

        ResultSet rs=state.executeQuery("SELECT * FROM trees WHERE ID='"+x+"';");
        TreesDB.treeData[x]=new TreeData(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));

    }
    public void getTrees(){
        if (con == null) {
            getConnection();
        }
        try {
            Statement state = con.createStatement();
            for(int i=0;i<8;i++)
                loadTree(i,state);
            TreesDB.LoadData();
        }
        catch(Exception e){System.out.println(e);}
    }

    public void getScore(){
        if (con == null) {
            getConnection();
        }
        try {
            Statement state = con.createStatement();

            ResultSet rs=state.executeQuery("SELECT score FROM scores");

            if(rs!=null){
                ScoreDB.score=rs.getInt(1);
            }
        }
        catch(Exception e){System.out.println(e);}
    }


    public void pasteOptions(){
        if(con==null){
            getConnection();
        }
        try{
            Statement state=con.createStatement();
            state.executeUpdate("UPDATE options "+"SET character='"+Opts.Character+"',difficulty='"+Opts.Diff+"';");
        }
        catch(Exception e){System.out.println(e);}
    }
    public void pasteMilestones(){
            if(con==null){
                getConnection();
            }
            try{
                Statement state=con.createStatement();
                state.executeUpdate("UPDATE milestones "+"SET highscore" +
                        "='"+Milestones.Highscore+"',firesout='"+Milestones.Firesout+"',treesplanted='"+Milestones.Treesplanted+"';");
            }
            catch(Exception e){System.out.println(e);}
    }
    //SAVE
    public void saveTree(int x, Statement state) throws SQLException{
        state.executeUpdate("UPDATE trees SET x='"+TreesDB.treeData[x].x+"',y='"+TreesDB.treeData[x].y+"',fires='"+TreesDB.treeData[x].fires+"',maxhp='"+TreesDB.treeData[x].maxHP+"',currenthp='"+TreesDB.treeData[x].currentHP+"' WHERE ID="+x+";");
    }
    public void pasteTrees(){
        if(con==null){
            getConnection();
        }
        try{
            Statement state = con.createStatement();
            TreesDB.SaveData();

            for(int i=0;i<8;i++)
                saveTree(i,state);




        }
        catch(Exception e){System.out.println(e);}
    }
    public void pasteScore(){
        if(con==null){
            getConnection();
        }
        try{
            Statement state=con.createStatement();
            ScoreDB.UpdateScore();
            System.out.println(ScoreDB.score);
            state.executeUpdate("UPDATE scores "+"SET score" + "='"+ScoreDB.score+"';");
        }
        catch(Exception e){System.out.println(e);}
    }

    public void Load(){
        getScore();
        getTrees();
    }
    public void Save(){
        pasteScore();
        pasteTrees();
    }

}

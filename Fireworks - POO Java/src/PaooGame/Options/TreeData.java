package PaooGame.Options;

public class TreeData{
    int x;
    int y;
    int fires;
    int maxHP;
    int currentHP;



    TreeData(int x,int y,int fires,int maxhp,int currentHP){
        this.x=x;
        this.y=y;
        this.fires=fires;
        this.maxHP=maxhp;
        this.currentHP=currentHP;
    }
    public void print(){
        System.out.println("X :"+x+" Y: "+y+" fires "+fires+" maxHP "+maxHP+ " current "+currentHP);
    }
}
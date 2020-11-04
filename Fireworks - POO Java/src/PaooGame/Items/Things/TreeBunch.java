package PaooGame.Items.Things;

import PaooGame.Game;
import PaooGame.RefLinks;

import java.util.Vector;

public class TreeBunch {
    public Vector<Tree> trees;
    public int treeNr;
    public RefLinks refLinks;

    int cooldown=120;

    public TreeBunch(int TreesNr, RefLinks refLinks){
        treeNr=TreesNr;
        this.refLinks=refLinks;
        trees=new Vector<Tree>(treeNr);
    }

    public void Update(){
        for(int i=0;i<treeNr;i++)
            trees.elementAt(i).Update();

        if(cooldown==0) {
            deadTrees();
            cooldown=120;
        }
        else
            cooldown--;
    }

    public void deadTrees(){
        int s=0;
        for(int i=0;i<treeNr;i++)
            if(trees.elementAt(i).HP<=0)
                s++;

           if( refLinks.GetKing().Faith>0)
            refLinks.GetKing().Faith-=s;
           if(refLinks.GetKing().Faith<0) refLinks.GetKing().Faith=0;
    }
    public void EmptyVector(){
        if(trees.size()>0) {
            System.out.println("TREE NR : "+treeNr+" SIZE: "+trees.size());
            for (int i = 0; i < treeNr; i++)
                trees.remove(0);


        }

    }




}

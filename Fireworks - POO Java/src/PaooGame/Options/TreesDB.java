package PaooGame.Options;

import PaooGame.Items.Things.TreeBunch;
import PaooGame.SingleGame;



public class TreesDB {


   public static TreeData [] treeData= new TreeData[8];

   public static void SaveData(){
      TreeBunch trees= SingleGame.getGame().getRefLink().GetMap().getTrees();

      for(int i=0;i<trees.treeNr;i++)
      {
         treeData[i]=new TreeData(trees.trees.elementAt(i).getTileX(),trees.trees.elementAt(i).getTileY(),trees.trees.elementAt(i).fires.firesBurning,trees.trees.elementAt(i).getMaxHp(),trees.trees.elementAt(i).getHp());

      }

   }
   public static void LoadData(){
      TreeBunch trees= SingleGame.getGame().getRefLink().GetMap().getTrees();

      for(int i=1;i<trees.treeNr;i++)
      {
         trees.trees.elementAt(i).setTileX(treeData[i].x);
         trees.trees.elementAt(i).setTileY(treeData[i].y);
         trees.trees.elementAt(i).setHP(treeData[i].currentHP);
         trees.trees.elementAt(i).setMaxHp(treeData[i].maxHP);

         trees.trees.elementAt(i).ActivateXFires(treeData[i].fires);
      }



   }



}




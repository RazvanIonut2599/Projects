package PaooGame.Maps;

import PaooGame.Items.Things.Tree;
import PaooGame.Items.Things.TreeBunch;
import PaooGame.RefLinks;


import java.io.File;
import java.util.Random;
import java.util.Vector;


public class MapGenerator {

//tiles 10X10 
    public static void GenerateMap(int [][]tiles, TreeBunch trees,int treeNumber,int width,int height) {
        int i,j;
        System.out.println("GENERATING NEW MAP");


        for(i=0;i<height;i++)
            for(j=0;j<width;j++)
                tiles[i][j]=0;

        Random rand=new Random();
            int x,y;
        while(treeNumber>0)
        {
            x=rand.nextInt(7)+2; // nu poate fi pe primele 2 coloane
            y=rand.nextInt(5)+3; //nu poate fi pe primele sau ultimele 2 linii
            //verifica pe raza de 1 distanta stanga dreapta si 2 sus jos
            while(// copaci N S E W
                    tiles[x-1][y] ==1 || tiles[x+1][y] ==1 || tiles[x][y-1] ==1 || tiles[x][y+1] ==1 || tiles[x][y+2]==1 || tiles[x][y-2] ==1 )
            {

                x=rand.nextInt(7)+2;
                y=rand.nextInt(5)+2;
                if(y==4)
                    while(y==4)
                        y=rand.nextInt(5)+2;

            }

            if(tiles[x][y]!=1){
                
                treeNumber--;
                tiles[x][y]=1;
                trees.trees.add(new Tree(x,y,trees.refLinks));
                System.out.println(x+" "+y);

            }


        }

    }


}






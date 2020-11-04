package PaooGame.Graphics;

import PaooGame.Options.Opts;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice utilizate in joc.
    //Background
    public static BufferedImage BG;

    //HERO - this is the refference and we make animations for everyone and swap em when needed;
    public static Animation heroIdle=new Animation(10,true);
    public static Animation heroLeft=new Animation(10,true);
    public static Animation heroRight=new Animation(10,true);
    public static Animation heroAttackLeft=new Animation(10,true);
    public static Animation heroAttackRight=new Animation(10,true);
    //plague knight
    public static Animation plagueheroIdle = new Animation(1,false);
    public static Animation plagueheroLeft  = new Animation(6,true);
    public static Animation plagueheroRight = new Animation(6,true);
    public static Animation plagueheroAttackLeft = new Animation(4,true);
    public static Animation plagueheroAttackRight = new Animation(4,true);
    //mole knight
    public static Animation moleheroIdle=new Animation(1,false);
    public static Animation moleheroLeft=new Animation(3,true);
    public static Animation moleheroRight=new Animation(3,true);
    public static Animation moleheroAttackLeft=new Animation(3,true);
    public static Animation moleheroAttackRight=new Animation(3,true);
    //RED
    public static Animation RedheroIdle=new Animation(1,false);
    public static Animation RedheroLeft=new Animation(4,true);
    public static Animation RedheroRight=new Animation(4,true);
    public static Animation RedheroAttackLeft=new Animation(8,true);
    public static Animation RedheroAttackRight=new Animation(8,true);
    //king knight
    public static Animation KingheroIdle=new Animation(10,true);
    public static Animation KingheroLeft=new Animation(6,true);
    public static Animation KingheroRight=new Animation(6,true);
    public static Animation KingheroAttackLeft=new Animation(7,true);
    public static Animation KingheroAttackRight=new Animation(7,true);
    //tinker knight
    public static Animation TinkerheroIdle=new Animation(1,false);
    public static Animation TinkerheroLeft=new Animation(4,true);
    public static Animation TinkerheroRight=new Animation(4,true);
    public static Animation TinkerheroAttackLeft=new Animation(2,true);
    public static Animation TinkerheroAttackRight=new Animation(2,true);


    //TextBox
    public static BufferedImage TextBubble;
    //Hearts
    public static BufferedImage Heart;
    //Fire
    public static Animation fire = new Animation(4,true);
    //Trees
    public static BufferedImage treeAlive;
    public static BufferedImage treeDead;

    public static BufferedImage singleAlive;
    public static BufferedImage singleDead;
    //King
    public static Animation King = new Animation(4,false);

    //TILES
    public static BufferedImage tileGrass;
    public static BufferedImage tileCircle;
    //TUTORIAL
    public static BufferedImage tutorial1;
    public static BufferedImage tutorial2;



    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        new FontLoader().Font();

        SpriteSheet extras  = new SpriteSheet(ImageLoader.LoadImage("/textures/BGS.png"));
        SpriteSheet sprites = new SpriteSheet(ImageLoader.LoadImage("/textures/FIREWORKS.png"));
        SpriteSheet tutorial = new SpriteSheet(ImageLoader.LoadImage("/textures/TUTORIAL.png"));
        SpriteSheet characters=new SpriteSheet(ImageLoader.LoadImage("/textures/CHARS.png"));


        //TUTORIAL
        tutorial1=tutorial.crop(0,0,423,498);
        tutorial2=tutorial.crop(500,0,423,498);

        //BG
        BG=extras.crop(0,0,320,492);
        //King
        King.addImage(sprites,0,147,48,41,false);
        King.addImage(sprites,44,147,48,44,false);
        King.addImage(sprites,88,147,48,41,false);
        King.addImage(sprites,130,147,48,44,false);


        //TREES
        treeAlive=sprites.crop(5,249,121,119);
        treeDead=sprites.crop(136,253,115,109);
        singleAlive=extras.crop(571,0,69,61);
        singleDead=extras.crop(502,0,69,61);

        //TILES
        tileGrass=sprites.crop(869,0,124,131);
        tileCircle=sprites.crop(869,0,124,131);

        //Fires
        fire.addImage(sprites,212,0,48,44,false);
        fire.addImage(sprites,88,0,48,44,false);
        fire.addImage(sprites,132,0,48,44,false);
        fire.addImage(sprites,170,0,48,44,false);
        fire.speed=60;



        //HEROES
            //PLAGUE KNIGHT
        plagueheroIdle.addImage(sprites, 0, 0, 47, 44, false);
        plagueheroRight.addImage(sprites, 0, 51, 46, 44, false);
        plagueheroRight.addImage(sprites, 44, 49, 48, 44, false);
        plagueheroRight.addImage(sprites, 88, 49, 48, 44, false);
        plagueheroRight.addImage(sprites, 132, 49, 48, 44, false);
        plagueheroRight.addImage(sprites, 176, 49, 48, 44, false);
        plagueheroRight.addImage(sprites, 220, 49, 48, 44, false);
        plagueheroLeft.addImage(plagueheroRight, true);
        plagueheroAttackRight.addImage(sprites, 0, 100, 46, 44, false);
        plagueheroAttackRight.addImage(sprites, 44, 100, 46, 44, false);
        plagueheroAttackRight.addImage(sprites, 88, 100, 46, 44, false);
        plagueheroAttackRight.addImage(sprites, 132, 100, 46, 44, false);
        plagueheroAttackLeft.addImage(plagueheroAttackRight, true);

        //RED
        RedheroIdle.addImage(characters,0,0,47,44,false);
        RedheroRight.addImage(characters,0,47,47,44,false);
        RedheroRight.addImage(characters,44,47,47,44,false);
        RedheroRight.addImage(characters,88,47,47,44,false);
        RedheroRight.addImage(characters,132,47,47,44,false);
        RedheroLeft.addImage(RedheroRight,true);

        RedheroAttackRight.addImage(characters, 0,100,47,44,false);
        RedheroAttackRight.addImage(characters,44,100,47,44,false);
        RedheroAttackRight.addImage(characters,88,100,47,44,false);
        RedheroAttackRight.addImage(characters,132,100,47,44,false);
        RedheroAttackRight.addImage(characters,176,100,47,44,false);
        RedheroAttackRight.addImage(characters,220,100,47,44,false);
        RedheroAttackRight.addImage(characters,264,100,47,44,false);
        RedheroAttackRight.addImage(characters,298,100,47,44,false);

        RedheroAttackLeft.addImage(RedheroAttackRight,true);

        //MOLE
        moleheroIdle.addImage(characters,0,145,47,44,false);
        moleheroRight.addImage(characters,44,192,47,44,false);
        moleheroRight.addImage(characters,88,192,47,44,false);
        moleheroRight.addImage(characters,0,192,47,44,false);
        moleheroLeft.addImage(moleheroRight,true);
        moleheroAttackRight.addImage(characters,0,241,47,44,false);
        moleheroAttackRight.addImage(characters,44,241,47,44,false);
        moleheroAttackRight.addImage(characters,88,241,47,44,false);
        moleheroAttackLeft.addImage(moleheroAttackRight,true);

        //KING

        KingheroIdle.addImage(characters,342,0,49,43,false);
        KingheroIdle.addImage(characters,385,0,49,43,false);
        KingheroIdle.addImage(characters,428,0,49,43,false);
        KingheroIdle.addImage(characters,471,0,49,43,false);
        KingheroIdle.addImage(characters,514,0,49,43,false);
        KingheroIdle.addImage(characters,557,0,49,43,false);
        KingheroIdle.addImage(characters,600,0,49,43,false);
        KingheroIdle.addImage(characters,643,0,49,43,false);
        KingheroIdle.addImage(characters,686,0,49,43,false);
        KingheroIdle.addImage(characters,729,0,49,43,false);


        KingheroRight.addImage(characters,342,48,49,43,false);
        KingheroRight.addImage(characters,385,48,49,43,false);
        KingheroRight.addImage(characters,428,48,49,43,false);
        KingheroRight.addImage(characters,471,48,49,43,false);
        KingheroRight.addImage(characters,514,48,49,43,false);
        KingheroRight.addImage(characters,557,48,49,43,false);

        KingheroLeft.addImage(KingheroRight,true);


        KingheroAttackRight.addImage(characters,342,96,49,43,false);
        KingheroAttackRight.addImage(characters,385,96,49,43,false);
        KingheroAttackRight.addImage(characters,428,96,49,43,false);
        KingheroAttackRight.addImage(characters,471,96,49,43,false);
        KingheroAttackRight.addImage(characters,514,96,49,43,false);
        KingheroAttackRight.addImage(characters,557,96,49,43,false);
        KingheroAttackRight.addImage(characters,600,96,49,43,false);

        KingheroAttackLeft.addImage(KingheroAttackRight,true);

        //TINKER
        TinkerheroIdle.addImage(characters,344,151,43,38,false);

        TinkerheroRight.addImage(characters,348,196,43,38,false);
        TinkerheroRight.addImage(characters,389,196,43,38,false);
        TinkerheroRight.addImage(characters,434,196,43,38,false);
        TinkerheroRight.addImage(characters,475,196,43,38,false);

        TinkerheroLeft.addImage(TinkerheroRight,true);

        TinkerheroAttackRight.addImage(characters,349,246,43,38,false);
        TinkerheroAttackRight.addImage(characters,390,246,43,38,false);

        TinkerheroAttackLeft.addImage(TinkerheroAttackRight,true);


        //Hearts
            Heart=sprites.crop(176,100,46,44);

        //TextBox
            TextBubble=sprites.crop(176,147,46,88);

            //SET START HERO
        switch (Opts.Character)
        {
            case 0:{

                Assets.heroIdle.Remake(Assets.plagueheroIdle);
                Assets.heroRight.Remake(Assets.plagueheroRight);
                Assets.heroLeft.Remake(Assets.plagueheroLeft);
                Assets.heroAttackRight.Remake(Assets.plagueheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.plagueheroAttackLeft);

                break;}
            case 1:{
                Assets.heroIdle.Remake(Assets.moleheroIdle);
                Assets.heroRight.Remake(Assets.moleheroRight);
                Assets.heroLeft.Remake(Assets.moleheroLeft);
                Assets.heroAttackRight.Remake(Assets.moleheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.moleheroAttackLeft);
                break;}
            case 2:{
                Assets.heroIdle.Remake(Assets.RedheroIdle);
                Assets.heroRight.Remake(Assets.RedheroRight);
                Assets.heroLeft.Remake(Assets.RedheroLeft);
                Assets.heroAttackRight.Remake(Assets.RedheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.RedheroAttackLeft);
                break;}
            case 3:{
                Assets.heroIdle.Remake(Assets.KingheroIdle);
                Assets.heroRight.Remake(Assets.KingheroRight);
                Assets.heroLeft.Remake(Assets.KingheroLeft);
                Assets.heroAttackRight.Remake(Assets.KingheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.KingheroAttackLeft);
                break;}
            case 4:{
                Assets.heroIdle.Remake(Assets.TinkerheroIdle);
                Assets.heroRight.Remake(Assets.TinkerheroRight);
                Assets.heroLeft.Remake(Assets.TinkerheroLeft);
                Assets.heroAttackRight.Remake(Assets.TinkerheroAttackRight);
                Assets.heroAttackLeft.Remake(Assets.TinkerheroAttackLeft);
                break;}


        }

    }


}

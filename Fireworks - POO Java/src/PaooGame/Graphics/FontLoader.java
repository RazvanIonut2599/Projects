package PaooGame.Graphics;

import PaooGame.Options.Opts;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class FontLoader {
   public static Font FireWorksFont ;


    public void Font() {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("AceRecords.ttf"));
            font=font.deriveFont(20f);
            font=font.deriveFont(Font.PLAIN);
        } catch (Exception ex) {

            System.out.println(ex);

        }
        FireWorksFont= font;
    }
}

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Hero {

    private int heroY;
    BufferedImage spriteSheet;
	BufferedImage[] heroBuffImgRunning = new BufferedImage[12];
	Image[] heroImgsRunning = new Image[12];


    public Hero(){

        heroY = 325;

        try {
            spriteSheet = ImageIO.read(new File("res/dinosaur-sprite-sheet.png"));
			for(int x=0;x<heroBuffImgRunning.length;x++){
                heroBuffImgRunning[x]=spriteSheet.getSubimage(x*55+7,202,55,50);
                heroImgsRunning[x]=heroBuffImgRunning[x].getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
}
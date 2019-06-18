import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Hero {

    private int heroY;
    private int heroX;

    private BufferedImage spriteSheet;
	private BufferedImage[] heroBuffImgRunning = new BufferedImage[12];
    private Image[] heroImgsRunning = new Image[12];
    private BufferedImage[] heroBuffImgJumping = new BufferedImage[7];
    private Image[] heroImgsJumping = new Image[7];
    
    private int imgCount = 0;
    private int jumpImgCount = 0;

    private boolean jumpUP = false;
    private boolean jumpDown = false;
    
    private Rectangle heroHitBox;
    private Rectangle collTop;
    private Rectangle collBot;

    public Hero(){

        heroY = 325;
        heroX = 200;

        heroHitBox = new Rectangle(heroX, heroY+10, 120, 90);
        collTop = new Rectangle(heroX, heroY + 55, 120, 45);
        collBot = new Rectangle(heroX, heroY + 10, 120, 45);

        try {
            spriteSheet = ImageIO.read(new File("res/dinosaur-sprite-sheet.png"));
			for(int x=0;x<heroBuffImgRunning.length;x++){
                heroBuffImgRunning[x]=spriteSheet.getSubimage(x*55+7,202,55,50);
                heroImgsRunning[x]=heroBuffImgRunning[x].getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            }
            /*for (int i=0;i<heroBuffImgJumping.length;i++){
                heroBuffImgJumping[i] = spriteSheet.getSubimage(i*50+7, 56, 50, 50);
                heroImgsJumping[i]= heroBuffImgJumping[i].getScaledInstance(120, 120, Image.SCALE_DEFAULT);   
            }*/

        } catch (IOException e) {
            //TODO: handle exception
        }

    }
    
    public void moveRight(){
        imgCount++;
        if(imgCount/2>=heroBuffImgRunning.length)
            imgCount=0;
    }
    
    public void live(){
        
        if (jumpUP){
            heroY-=4;
            //if (jumpImgCount<6)
            //    jumpImgCount++;
            if (heroY<150){
                jumpUP = false;
                jumpDown = true;
            }
        }
        if (jumpDown){
            heroY+=4;
            //System.out.println(jumpImgCount);
            //if (jumpImgCount>0)
            //    jumpImgCount--;
            if (heroY>325){
                jumpDown = false;
            }
        }
        heroHitBox = new Rectangle(heroX, heroY+10, 120, 90);
        
    }
    
    public void jump(){
        if (jumpUP == false && jumpDown == false)
        jumpUP = true;
        
    }
    
    public Rectangle getHeroHitBox(){
        return heroHitBox;
    }

    public Rectangle collTop(){
        return collTop;
    }

    public Rectangle collBot(){
        return collBot;
    }


    public Image getImage(){
        return heroImgsRunning[imgCount/2];
    }
    
    public int getHeroY() {
        return this.heroY;
    }

    public void setHeroY(int heroY) {
        this.heroY = heroY;
    }

    public int getHeroX() {
        return this.heroX;
    }

    public void setHeroX(int heroX) {
        this.heroX = heroX;
    }

    public int getImgCount() {
        return this.imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }

    public BufferedImage getSpriteSheet() {
        return this.spriteSheet;
    }

    public Image[] getHeroImgsRunning() {
        return this.heroImgsRunning;
    }

    public boolean getJumpUP() {
        return this.jumpUP;
    }

    public void setJumpUP(boolean jumpUP) {
        this.jumpUP = jumpUP;
    }

    public boolean getJumpDown() {
        return this.jumpDown;
    }

    public void setJumpDown(boolean jumpDown) {
        this.jumpDown = jumpDown;
    }


}
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Block {

    private int blockX;
    private int blockY;
    private Rectangle blockRect;
    private BufferedImage image;

    public Block(int blockX, int blockY){
        this.blockX = blockX;
        this.blockY = blockY;
        blockRect = new Rectangle(blockX, blockY, 50, 50);

        try{
            image = ImageIO.read(new File("res/Brick_Block.png"));
        }catch(IOException e){}
    }

    public void move(){
        blockX--;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getBlockX() {
        return this.blockX;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public int getBlockY() {
        return this.blockY;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public Rectangle getBlockRect() {
        return this.blockRect;
    }

    public void setBlockRect(Rectangle blockRect) {
        this.blockRect = blockRect;
    }

}
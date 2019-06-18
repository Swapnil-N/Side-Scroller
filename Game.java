import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Game extends JPanel implements KeyListener, Runnable{

	private int heroY;
	private int backX;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage spriteSheet;
	BufferedImage[] heroBuffImgRunning = new BufferedImage[12];
	Image[] heroImgsRunning = new Image[12];

	BufferedImage[] heroBuffImgJumping = new BufferedImage[7];
	Image[] heroImgsJumping = new Image[7];

	boolean restart = false;
	int imgCount = 0;
	int jumpImgCount = 0;

	BufferedImage[] bgs = new BufferedImage[5];
	Image[] bgImgs = new Image[5];

	private boolean right = false;
	private boolean left = false;
	private boolean up = false;

	private boolean jumpUP = false;
	private boolean jumpDown = false;

	public Game(){

		frame = new JFrame();
		heroY = 325;
		gameOn=true;

		try {
			spriteSheet = ImageIO.read(new File("res/dinosaur-sprite-sheet.png"));
			for(int x=0;x<heroBuffImgRunning.length;x++)
				heroBuffImgRunning[x]=spriteSheet.getSubimage(x*55+7,202,55,50);
			
			for (int i=0;i<heroBuffImgJumping.length;i++)
				heroBuffImgJumping[i] = spriteSheet.getSubimage(i*50+7, 60, 50, 50);

			for (int i=0;i<5;i++)
				bgs[i] = ImageIO.read(new File("res/layer_0"+(i+1)+"_1920 x 1080.png" ));

		}
		catch (IOException e) {
			System.out.println("Hello?");
		}
		
		for(int x=0;x<bgs.length;x++)
			bgImgs[x]=bgs[x].getScaledInstance(1920, 500, Image.SCALE_DEFAULT);
		
		for(int x=0;x<heroImgsRunning.length;x++)
			heroImgsRunning[x]=heroBuffImgRunning[x].getScaledInstance(120, 120, Image.SCALE_DEFAULT);

		for(int i=0;i<heroImgsJumping.length;i++)
			heroImgsJumping[i]= heroBuffImgJumping[i].getScaledInstance(120, 120, Image.SCALE_DEFAULT);

		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(800,540);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
	}

	public void run(){

		while(true){
			if(gameOn){
				//Math happens here!
				if (right){
					backX-=3;
					imgCount++;
					if(imgCount>=heroBuffImgRunning.length)
						imgCount=0;
				}
				if (left){
					backX+=3;
					imgCount--;
					if(imgCount<0)
						imgCount=11;
				}
				if (up){
					if (jumpUP == false && jumpDown == false)
						jumpUP = true;
				}
				if (jumpUP){
					heroY-=2;
					System.out.println(jumpImgCount);
					if (jumpImgCount<6)
						jumpImgCount++;
					if (heroY<250){
						jumpUP = false;
						jumpDown = true;
					}
				}
				if (jumpDown){
					heroY+=2;
					if (jumpImgCount>0)
						jumpImgCount--;
					if (heroY>325){
						jumpDown = false;
					}
				}

				if(backX<-1920)
					backX=0;
				if(backX>1920)
					backX=0;	
				repaint();
			}
			if(restart){
				restart=false;
				gameOn=true;
			}
			try{
				t.sleep(10);
			}catch(InterruptedException e){}
		}
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!
		for (Image myImage: bgImgs){
			g2d.drawImage(myImage, backX-960-1920, 0, null);
			g2d.drawImage(myImage, backX-960, 0, null);
			g2d.drawImage(myImage, backX+960, 0, null);
		}
		//if (right)
			g2d.drawImage(heroImgsRunning[imgCount],100,heroY,null);
		if (up)
			g2d.drawImage(heroImgsJumping[jumpImgCount],100,heroY,null);
		//g2d.drawImage(spriteSheet,100,100,null);

	}
	public void keyPressed(KeyEvent key){

		//System.out.println(key.getKeyCode());

		if(key.getKeyCode()==39) // right
			right = true;
		if(key.getKeyCode()==37) // left
			left = true;
		if(key.getKeyCode()==38) // up
			up = true;
		if(key.getKeyCode()==40) //down
			down = true;
			
		if(key.getKeyCode()==82) // R
			restart=true;
	}
	public void keyReleased(KeyEvent key){

		if(key.getKeyCode()==39) // right
			right = false;
		if(key.getKeyCode()==37) // left
			left = false;
		if(key.getKeyCode()==38) // up
			up = false;
		if(key.getKeyCode()==40) //down
			down = false;

	}
	public void keyTyped(KeyEvent key)
	{
	}
	public static void main(String args[])
	{
		Game app=new Game();
	}
}
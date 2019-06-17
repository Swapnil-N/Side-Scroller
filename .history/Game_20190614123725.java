import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Game extends JPanel implements KeyListener, Runnable
{
	private int x;
	private int y;
	private int backX;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage spriteSheet;
	BufferedImage[] marioBuffImg = new BufferedImage[12];
	Image[] marioImgs = new Image[12];

	boolean restart=false;
	int imgCount=0;


	BufferedImage[] bgs = new BufferedImage[5];
	Image[] bgImgs = new Image[5];


	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	public Game()
	{
		frame=new JFrame();
		x=100;
		y=100;
		gameOn=true;

		try {
			spriteSheet = ImageIO.read(new File("res/mario.gif"));
			for(int x=0;x<marioBuffImg.length;x++)
				marioBuffImg[x]=spriteSheet.getSubimage(x*20+30,120,20,25);

			for (int i=0;i<5;i++)
				bgs[i] = ImageIO.read(new File("res/layer_0"+(i+1)+"_1920 x 1080.png" ));

		}
		catch (IOException e) {
			System.out.println("Hello?");

		}
		
		for(int x=0;x<bgs.length;x++)
		{
			bgImgs[x]=bgs[x].getScaledInstance(1920, 500, Image.SCALE_DEFAULT);
		}
		
		for(int x=0;x<marioImgs.length;x++)
			marioImgs[x]=marioBuffImg[x].getScaledInstance(100, 100, Image.SCALE_DEFAULT);

		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(800,500);
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
					//x+=3;
					backX-=3;
					imgCount++;
					if(imgCount>10)
						imgCount=0;
				}
				if (left){
					//x-=3;
					backX+=3;
					imgCount--;
					if(imgCount<0)
						imgCount=10;
				}
				if (up){
					y-=5;
					imgCount--;
					if(imgCount<0)
						imgCount=10;
				}
				if (down){
					y+=5;
					imgCount++;
					if(imgCount>10)
						imgCount=0;
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
		g2d.drawImage(marioBuffImg[imgCount],y,100,null);
		//g2d.drawImage(spriteSheet,100,100,null);

	}
	public void keyPressed(KeyEvent key){

		System.out.println(key.getKeyCode());

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
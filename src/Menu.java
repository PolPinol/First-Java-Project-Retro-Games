import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.lang.String;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.imageio.ImageIO;

public class Menu extends Thread  implements KeyListener{
	
	Finestra f;
	Graphics grafics;
	
	JocPong jocpong;
	JocSnake jocsnake;
	Joc joc;
	MenuImatge joc1;
	MenuImatge joc2;
	MenuImatge joc3;
	Instrucciones ins;
	Font myFont;
	
	int posicio;
	int triar;
	int inst;
	Menu(Finestra f) {
		f.addKeyListener(this);
		this.f=f;
		grafics = f.g;

	}
	
	public void executa() {
		inicialitza();
		while(true) {
			repintaPantalla();
			
			
			
			
			if(triar==2) {
				f.removeKeyListener(this);
				jocpong =new JocPong(f);
				jocpong.executa();
				triar=0;
				f.addKeyListener(this);
			}
			if(triar==1) {
				f.removeKeyListener(this);
				jocsnake =new JocSnake(f);
				jocsnake.executa();
				triar=0;
				f.addKeyListener(this);
			}
			if(triar==3) {
				f.removeKeyListener(this);
				joc =new Joc(f);
				joc.executa();
				triar=0;
				f.addKeyListener(this);
			}
			if(inst==1) {
				f.removeKeyListener(this);
				ins=new Instrucciones(f);
				ins.executa();
				inst=0;
				f.addKeyListener(this);
			}
			
			
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		

		
		
		
	}
	

	
	void inicialitza(){
		inst=0;
		triar=0;
		posicio=1;
		cargarImagenes();
		joc1= new MenuImatge(100,200,100,100,1);
		joc2= new MenuImatge(250,200,100,100,2);
		joc3= new MenuImatge(400,200,100,100,3);
		
		try
	    {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/font.ttf")).deriveFont(10f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/font.ttf")));
		    
	    }
		catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		    myFont = new Font("Helvetica", Font.BOLD, 30);
		    System.err.println("caught error");
		}
		grafics.setColor(Color.WHITE);
	    grafics.setFont(myFont);
		
		
		
	}
	
	
	
	
	


	
	
	void repintaPantalla() {
		

		
		f.g.setColor(Color.BLACK);
		f.g.fillRect(0,0,615,600);
		
		f.g.setColor(Color.GRAY);
		f.g.fillRect(0,0,615,100);
		f.g.fillRect(0,0,50,600);
		f.g.fillRect(550,0,65,600);
		f.g.fillRect(0,400,615,200);
		
		f.g.setColor(Color.DARK_GRAY);
		f.g.fillRect(50,415,500,160);
		
		
		f.g.setColor(Color.BLACK);
		
		f.g.fillOval(455,425,40,30);
		f.g.fillOval(455,475,40,30);
		
		f.g.fillOval(395,450,40,30);
		f.g.fillOval(395,500,40,30);
		
		f.g.fillOval(335,475,40,30);
		f.g.fillOval(335,525,40,30);
		
		
		f.g.setColor(Color.RED);
		f.g.fillOval(465,425,40,30);
		f.g.fillOval(465,475,40,30);
		
		f.g.fillOval(405,450,40,30);
		f.g.fillOval(405,500,40,30);
		
		f.g.fillOval(345,475,40,30);
		f.g.fillOval(345,525,40,30);
		
		Color griset = new Color(15, 15, 15);
		
		f.g.setColor(griset);
		f.g.fillOval(120,525,55,30);
		
		f.g.setColor(Color.BLACK);
		f.g.fillOval(120,425,50,50);
		f.g.fillRect(135,450,20,95);
		
		f.g.setColor(Color.GRAY);
		f.g.fillRect(140,450,20,95);
		
		
		
		
		f.g.setColor(Color.RED);
		f.g.fillOval(125,425,50,50);
		
		
		
		
		joc1.pinta(f.g);
		joc2.pinta(f.g);
		joc3.pinta(f.g);
		
		grafics.setColor(Color.WHITE);
	    grafics.setFont(myFont);
		grafics.drawString("P U L S A R    [ 1 ]    P A R A    I N S T R U C C I O N E S", 50, 50);
		grafics.drawString("P U L S A R    [ < ]    o    [ > ]    P A R A    M O V E R", 50, 70);
		grafics.drawString("P U L S A R    [ ENTER ]    P A R A    J U G A R", 50, 90);
		
		
		
		if(posicio==1) {
			f.g.setColor(Color.WHITE);
			f.g.fillRect(joc1.x-10,joc1.y-5,5,110);
			f.g.fillRect(joc1.x-10,joc1.y-5,115,5);
			f.g.fillRect(joc1.x+105,joc1.y-5,5,110);
			f.g.fillRect(joc1.x-10,joc1.y+100,115,5);
		}
		
		else if(posicio==2){
			f.g.setColor(Color.WHITE);
			f.g.fillRect(joc2.x-10,joc1.y-5,5,110);
			f.g.fillRect(joc2.x-10,joc1.y-5,115,5);
			f.g.fillRect(joc2.x+105,joc1.y-5,5,110);
			f.g.fillRect(joc2.x-10,joc1.y+100,115,5);
		}
		
		else {
			f.g.setColor(Color.WHITE);
			f.g.fillRect(joc3.x-10,joc1.y-5,5,110);
			f.g.fillRect(joc3.x-10,joc1.y-5,115,5);
			f.g.fillRect(joc3.x+105,joc1.y-5,5,110);
			f.g.fillRect(joc3.x-10,joc1.y+100,115,5);
		}
		
		
		
		
		
		
		
		
		
		f.repaint();
		
	}
	
	
	void cargarImagenes(){
		try {
			MenuImatge.img1 = ImageIO.read(new File("../img/imgtipus1.png"));
			MenuImatge.img2 = ImageIO.read(new File("../img/imgtipus2.png"));
			MenuImatge.img3 = ImageIO.read(new File("../img/imgtipus3.png"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	

	

	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent k) { 
					
				
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			if(posicio==1) {
				
			}
			else {
				posicio--;
			}
		}
		
		if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(posicio==3) {
				
			}
			else {
				posicio++;
			}
		}	
		
		if(k.getKeyCode() == KeyEvent.VK_ENTER) {
			triar=posicio;
		}
		
		if(k.getKeyCode() == KeyEvent.VK_1) {
			inst=1;
		}
			
		
	
        
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}

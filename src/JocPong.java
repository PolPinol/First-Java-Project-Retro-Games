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

public class JocPong extends Thread  implements KeyListener{
	
	Finestra f;
	Graphics grafics;
	int stopjoc;
	CotxePong c[]=new CotxePong[2];
	BolaPong bola;
	int up1,down1;
	int stop1up;
	int stop1down;
	int up2,down2;
	int stop2up;
	int stop2down;
	int vx,vy;
	int score0,score1;
	int end;
	int bolaup;
	int boladown;
	
	JocPong(Finestra f) {
		f.addKeyListener(this);
		this.f=f;
		grafics = f.g;

	}
	
	public void executa() {
		inicialitza();
		repintaPantalla();
		while(end==0) {
			while(stopjoc==0 && end==0) {					
				repintaPantalla();
				moviments();
				xocs();	
				try {
					Thread.sleep(12);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	void inicialitza(){
		Color groc = new Color(186, 178, 54);
		end=0;
		f.g.setColor(groc);
		f.g.fillRect(0,0,615,150);
		Font myFont2;
		try
	    {
			myFont2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/pongfont.ttf")).deriveFont(40f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/pongfont.ttf")));
		    
	    }
		catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		    myFont2 = new Font("Helvetica", Font.BOLD, 30);
		    System.err.println("caught error");
		}
		f.g.setFont(myFont2);
		f.g.setColor(Color.BLACK);
		grafics.drawString("P O N G", 225, 100);
		
		
		
		Font myFont3;
		try
	    {
			myFont3 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/pongfont2.ttf")).deriveFont(50f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/pongfont2.ttf")));
		    
	    }
		catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		    myFont3 = new Font("Helvetica", Font.BOLD, 30);
		    System.err.println("caught error");
		}
		
		
		grafics.setFont(myFont3);
		stopjoc=0;
		stop1up=0;
		stop1down=0;
		stop2up=0;
		stop2down=0;
		//stopjoc=1;
		up1=0;
		down1=0;
		up2=0;
		down2=0;
		score1=0;
		score0=0;
		
		int c1;
		c1=(int) Math.floor(Math.random()*4);
		
		if(c1==0) {
			vx=1;
			vy=1;
			bolaup=0;
			boladown=1;
		}
		
		else if(c1==1) {
			vx=-1;
			vy=1;
			bolaup=0;
			boladown=1;
		}
		
		else if(c1==2) {
			vx=1;
			vy=-1;
			bolaup=1;
			boladown=0;
		}
		else {
			vx=-1;
			vy=-1;
			bolaup=1;
			boladown=0;
		}
		
		
		
		
		
		
		
		
		
		
		bola=new BolaPong(300,275,5,5);
		c[0]=new CotxePong(170,250,5,30);
		c[1]=new CotxePong(430,250,5,30);
		
		
		
		
	}
	
	
	
	void moviments() {
		if(up1==1) {
			c[1].up();
		}
		else if(down1==1) {
			c[1].down();
		}
		else {
			
		}
		
		
		if(up2==1) {
			c[0].up();
		}
		else if(down2==1) {
			c[0].down();
		}
		else {
			
		}
		
		bola.moure(vx,vy);
		
	}
	
	

	
	void xocs() {
		
		int x1,x2,y1,y2,w1,w2,h1,h2;
		
		
		
		
		
		
		x1=100;
		y1=0;
		w1=600;
		h1=150;
		
		x2=c[1].x; 
		y2=c[1].y;
		w2=1;
		h2=c[1].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stop1up=1;
			up1=0;
		}
		

		x1=100;
		y1=400;
		w1=600;
		h1=150;
		
		x2=c[1].x; 
		y2=c[1].y;
		w2=1;
		h2=c[1].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stop1down=1;
			down1=0;
		}
		
		///////////////////////
		
		x1=100;
		y1=0;
		w1=600;
		h1=150;
		
		
		x2=c[0].x; 
		y2=c[0].y;
		w2=1;
		h2=c[0].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stop2up=1;
			up2=0;
		}
		

		x1=100;
		y1=400;
		w1=600;
		h1=150;
		
		x2=c[0].x; 
		y2=c[0].y;
		w2=1;
		h2=c[0].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stop2down=1;
			down2=0;
		}
		
		
		////////////////////////////
		//xoc bola-cotxe
		int aux1,aux2;
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=c[0].x; 
		y2=c[0].y;
		w2=1;
		h2=c[0].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			if(vx<0) {
				vx=-vx;
				
				if(bolaup==1) {
					aux1=(int) Math.floor(Math.random()*3);//numero entre 0 i 1
					aux2=(int) Math.floor(Math.random()*6);
					if(aux1==0) {
						vy=1;
					}
					else if(aux1==1) {
						vy=2;
					}
					else {
						vy=aux2;
					}
				}
				else {
					aux1=(int) Math.floor(Math.random()*3);//numero entre 0 i 1
					aux2=(int) Math.floor(Math.random()*6);
					if(aux1==0) {
						vy=-1;
					}
					else if(aux1==1) {
						vy=-2;
					}
					else {
						vy=-aux2;
					}
					
				}
				
				
				if(vy>0) {
					bolaup=1;
					boladown=0;
				}
				else {
					boladown=1;
					bolaup=0;
				}
			}

			
		}
		
		
		
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=c[1].x; 
		y2=c[1].y;
		w2=1;
		h2=c[1].alt; 
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			if(vx>0) {
				vx=-vx;
				
				if(bolaup==1) {
					aux1=(int) Math.floor(Math.random()*3);//numero entre 0 i 1
					aux2=(int) Math.floor(Math.random()*6);
					if(aux1==0) {
						vy=1;
					}
					else if(aux1==1) {
						vy=2;
					}
					else {
						vy=aux2;
					}
				}
				else {
					aux1=(int) Math.floor(Math.random()*3);//numero entre 0 i 1
					aux2=(int) Math.floor(Math.random()*6);
					if(aux1==0) {
						vy=-1;
					}
					else if(aux1==1) {
						vy=-2;
					}
					else {
						vy=-aux2;
					}
					
				}
				
				
				if(vy>0) {
					bolaup=1;
					boladown=0;
				}
				else {
					boladown=1;
					bolaup=0;
				}
			}

		}
		
		
		
		
		
		
		
		///////////////////
		//xoc bola rebot adalt abaix
		
		
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=100;
		y2=0;
		w2=600;
		h2=152;
		
		
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			if(vy<0) {
				vy=-vy;
				if(vy>0) {
					bolaup=1;
					boladown=0;
				}
				else {
					boladown=1;
					bolaup=0;
				}
			}			
		}
		
		
		
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=100;
		y2=400;
		w2=600;
		h2=150;
		
		
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			if(vy>0) {
				vy=-vy;
				if(vy>0) {
					bolaup=1;
					boladown=0;
				}
				else {
					boladown=1;
					bolaup=0;
				}
			}
			
			
		}
		
		//////////////////
		//xoc final
		
		
		
		
		
		
		int c1;
		c1=(int) Math.floor(Math.random()*4);
		
		
		
		
		
		
		
		
		//xoc 0
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=100;
		y2=0;
		w2=1;
		h2=600;
		
		
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			score1++;
			bola.x=300;
			bola.y=275;
			repintaPantalla();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(c1==0) {
				vx=1;
				vy=1;
				bolaup=0;
				boladown=1;
			}
			
			else if(c1==1) {
				vx=-1;
				vy=1;
				bolaup=0;
				boladown=1;
			}
			
			else if(c1==2) {
				vx=1;
				vy=-1;
				bolaup=1;
				boladown=0;
			}
			else {
				vx=-1;
				vy=-1;
				bolaup=1;
				boladown=0;
			}
		}
		
		
		
		
		//xoc 1
		x1=bola.x;
		y1=bola.y;
		w1=bola.ample;
		h1=bola.alt;
		
		x2=500;
		y2=0;
		w2=1;
		h2=600;
		
		
			
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			score0++;
			bola.x=300;
			bola.y=275;
			repintaPantalla();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(c1==0) {
				vx=1;
				vy=1;
				bolaup=0;
				boladown=1;
			}
			
			else if(c1==1) {
				vx=-1;
				vy=1;
				bolaup=0;
				boladown=1;
			}
			
			else if(c1==2) {
				vx=1;
				vy=-1;
				bolaup=1;
				boladown=0;
			}
			else {
				vx=-1;
				vy=-1;
				bolaup=1;
				boladown=0;
			}
		}	
		
		
	}


	
	
	void repintaPantalla() {
		

		
		f.g.setColor(Color.BLACK);
		f.g.fillRect(100,150,615,600);
		Color groc = new Color(186, 178, 54);
		
		f.g.setColor(groc);
		f.g.fillRect(0,0,100,600);
		f.g.fillRect(0,480,600,200);
		f.g.fillRect(500,0,115,600);
		
		
		
		
		f.g.fillRect(100,435,500,100);
		/*f.g.fillRect(100,435,50,100);
		f.g.fillRect(250,435,100,100);
		f.g.fillRect(450,435,50,100);^*/
		
		
		f.g.fillRect(100,400,600,35);
		
		
		f.g.setColor(Color.WHITE);
		
		
		for(int i=0;i<25;i++) {
			f.g.fillRect(300,150+10*i,1,7);
		}
		
		
		f.g.setColor(Color.WHITE);
		
		
		
		
		/*f.g.fillRect(100,150,1,250);
		
		f.g.fillRect(500,150,1,250);
		
		f.g.fillRect(100,150,400,1);
		f.g.fillRect(100,400,400,1);*/
		
		c[0].pinta(f.g);
		c[1].pinta(f.g);
		bola.pinta(f.g);
		
		f.g.setColor(Color.WHITE);
		String auxiliar = String.format("%d", score0);
		grafics.drawString(auxiliar, 255, 200);		
		
		String auxiliar2 = String.format("%d", score1);
		grafics.drawString(auxiliar2, 322, 200);
		
		f.g.setColor(groc);
		f.g.fillRect(0,120,600,30);
				
		
		
		
		
		
		f.repaint();
		
	}
	

	
	void cargarImagenes(){
		/*try {
			Cotxe.dino = ImageIO.read(new File("dino.png"));
			Cactus1.cactus1 = ImageIO.read(new File("cactus1.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	

	

	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP ) {
			up1=0;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			down1=0;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W ) {
			up2=0;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			
			down2=0;
		}	
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) { 
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			stopjoc=0;
		}			
				
		if(e.getKeyCode() == KeyEvent.VK_UP && stop1up==0) {
			up1=1;
			stop1down=0;
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN && stop1down==0) {
			down1=1;
			stop1up=0;
		}		
			
		
		if(e.getKeyCode() == KeyEvent.VK_W && stop2up==0) {
			up2=1;
			stop2down=0;
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S && stop2down==0) {
			down2=1;
			stop2up=0;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			end=1;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}

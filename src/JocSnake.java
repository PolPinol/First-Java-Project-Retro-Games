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

public class JocSnake extends Thread  implements KeyListener{
	
	Finestra f;
	Graphics grafics;
	CotxeSnake c[]=new CotxeSnake[9999];
	BalaSnake bala;
	int esquerra,dreta,up,down;
	int fills;
	int stopjoc;
	int score;
	int xoc;
	int hiscoreNum;
	String hiscore;
	int restart;
	int end;
	
	JocSnake(Finestra f) {
		f.addKeyListener(this);
		this.f=f;
		grafics = f.g;

	}
	
	void executa() {
		restart=0;
		end=0;
		while(end==0) {
			if(restart==0) {
				try {
					records();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inicialitza();
				while(stopjoc==0 && end==0) {
					xoc=0;
					try {
						guardarRecords();
					} catch (IOException e) {
						e.printStackTrace();
					}
					repintaPantalla();
					moviments();
					xocs();			
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				restart=1;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
	}
	

	
	void inicialitza(){
		Font myFont;
		try
	    {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/fontsnake.ttf")).deriveFont(15f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/fontsnake.ttf")));
		    
	    }
		catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		    myFont = new Font("Helvetica", Font.BOLD, 30);
		    System.err.println("caught error");
		}
		cargarImagenes();
		grafics.setColor(Color.WHITE);
	    grafics.setFont(myFont);		
		stopjoc=0;
		esquerra=0;
		dreta=0;
		up=0;
		down=0;
		fills=0;
		score=0;
		bala=new BalaSnake(300,300,10,10);
		for(int i=0;i<9999;i++) {
			c[i]=new CotxeSnake(300,200-10*i,10,10,10,0);
		}
		
		c[fills].crea();
		fills++;
	}
	
	
	
	void moviments() {
		if(esquerra==1) {
			for(int i=9998;i>=1;i--) {
				c[i].x=c[i-1].x;
				c[i].y=c[i-1].y;
			}
			c[0].moureesquerra();
			
		}
		if(dreta==1) {
			for(int i=9998;i>=1;i--) {
				c[i].x=c[i-1].x;
				c[i].y=c[i-1].y;
			}
			c[0].mouredreta();
		}
		if(up==1) {
			for(int i=9998;i>=1;i--) {
				c[i].x=c[i-1].x;
				c[i].y=c[i-1].y;
			}
			c[0].moureup();
		}
		if(down==1){
			for(int i=9998;i>=1;i--) {
				c[i].x=c[i-1].x;
				c[i].y=c[i-1].y;
			}
			c[0].mouredown();
		}
	}
	
	
	void crear() {
		c[fills].crea();
		fills++;
	}
	
	void xocs() {
		
		int x1,x2,y1,y2,w1,w2,h1,h2;
		int probx,proby,aux;
		
		//FALTA QUE NO COINCIDEIXI AMB SNAKE
		
		
		
		
		
		aux=(int) Math.floor(Math.random()*37);
		probx=10*aux+120;
		// 110 - 480
		
		
		aux=(int) Math.floor(Math.random()*25); 
		proby=10*aux+150;
		
		x1=bala.x;
		y1=bala.y;
		w1=bala.ample;
		h1=bala.alt;
		
		for(int i=0;i<fills;i++) {
			x2=c[i].x+1; 
			y2=c[i].y+1;
			w2=c[i].ample-2;
			h2=c[i].alt-2; 
			
			if( x1 > x2+w2 ){
			}
			else if ( x1+w1 < x2 ){
			}
			else if ( y1 > y2+h2 ){
			}
			else if ( y1+h1 < y2 ) {
			}
			else{
				bala.x=probx;
				bala.y=proby;
				crear();
				score++;
			}
		}
		
		
		
		
		
		
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		for(int i=1;i<fills;i++) {
			x1=c[i].x;
			y1=c[i].y;
			w1=c[i].ample;
			h1=c[i].alt;
					
			if( x1 > x2+w2 ){
			}
			else if ( x1+w1 < x2 ){
			}
			else if ( y1 > y2+h2 ){
			}
			else if ( y1+h1 < y2 ) {
			}
			else{
				stopjoc=1;
			}
		}
		
		
		
		//XOC FORA MAPA 4 LINEAS
		
		
		
		x1=119;
		y1=150;
		w1=1;
		h1=250;
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stopjoc=1;
		}	
		
		
		
		x1=119;
		y1=149;
		w1=375;
		h1=1;
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stopjoc=1;
		}
		
		

		x1=495;
		y1=149;
		w1=1;
		h1=250;
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stopjoc=1;
		}
		

		
		x1=120;
		y1=400;
		w1=375;
		h1=1;
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			stopjoc=1;
		}
		
		
	}


	
	
	void repintaPantalla() {
		//f.g.clearRect(0, 0, f.AMPLE,f.ALT);
		Color verd = new Color(153, 185, 136);
		Color verdfosc = new Color(109, 150, 88);
		f.g.setColor(verd);
		f.g.fillRect(60,0,550,f.ALT);
		
		f.g.setColor(verd);
		f.g.fillRect(120,150,370,250);
		
		
		
		f.g.setColor(verdfosc);
		f.g.fillRect(0, 0, 60,900);
		f.g.fillRect(550, 0,115,900);
		
		f.g.fillRect(0, 0, 600,100);
		f.g.fillRect(0, 500,600,200);
		
		
		f.g.setColor(Color.BLACK);
		f.g.fillRect(115,145,5,260);
		f.g.fillRect(115,145,380,5);
		f.g.fillRect(490,145,5,260);
		f.g.fillRect(115,400,380,5);
		
		f.g.setColor(Color.BLACK);
				  
        
        String score1 = String.format("%04d", score);
        
        String[] score2 = score1.split("(?<=.)"); //sempre tenim 5 zeros
        
        String part1=score2[0];
        String part2=score2[1];
        String part3=score2[2];
        String part4=score2[3];
        
        String score3= String.format("S C O R E  :  %s %s %s %s",part1,part2,part3,part4);
        String score4= String.format("H I - S C O R E  :  %s %s %s %s",part1,part2,part3,part4);
		grafics.drawString(score3, 115, 435);
		
		
		if(score>hiscoreNum) {
			grafics.drawString(score4, 316, 435);	
		}
		else {
			grafics.drawString(hiscore, 316, 435);	
		}		
		
		
		
		for(int i=0;i<fills;i++) {
			c[i].pinta(f.g);
		}
		
		bala.pinta(f.g);
		

		
		

		f.repaint();
		
	}
	
	int xocprevistup(){
		
		int auxX,auxY;
		int x1,x2,y1,y2,w1,w2,h1,h2;
		
		auxX=c[2].x;
		auxY=c[2].y;
		
		
		
		c[2].x=c[1].x;
		c[2].y=c[1].y;
		c[0].moureup();
		
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		
		x1=c[2].x;
		y1=c[2].y;
		w1=c[2].ample;
		h1=c[2].alt;
					
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			c[0].mouredown();
			c[2].x=auxX;
			c[2].y=auxY;
			
			return 1;
		}
		c[0].mouredown();
		c[2].x=auxX;
		c[2].y=auxY;
		return 0;
	}
	
	
	int xocprevistdown(){
		
		int auxX,auxY;
		int x1,x2,y1,y2,w1,w2,h1,h2;
		
		auxX=c[2].x;
		auxY=c[2].y;
		
		c[2].x=c[1].x;
		c[2].y=c[1].y;
		c[0].mouredown();
		
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		
		x1=c[2].x;
		y1=c[2].y;
		w1=c[2].ample;
		h1=c[2].alt;
					
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			c[0].moureup();
			c[2].x=auxX;
			c[2].y=auxY;
			
			return 1;
		}
		c[0].moureup();
		c[2].x=auxX;
		c[2].y=auxY;
		return 0;
	}
	
	
	
	int xocprevistesquerra(){
		
		int auxX,auxY;
		int x1,x2,y1,y2,w1,w2,h1,h2;
		
		auxX=c[2].x;
		auxY=c[2].y;
		
		c[2].x=c[1].x;
		c[2].y=c[1].y;
		c[0].moureesquerra();
		
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		
		x1=c[2].x;
		y1=c[2].y;
		w1=c[2].ample;
		h1=c[2].alt;
					
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			c[0].mouredreta();
			c[2].x=auxX;
			c[2].y=auxY;
			
			return 1;
		}
		c[0].mouredreta();
		c[2].x=auxX;
		c[2].y=auxY;
		return 0;
	}
	
	
	
	int xocprevistdreta(){
		
		int auxX,auxY;
		int x1,x2,y1,y2,w1,w2,h1,h2;
		
		
		auxX=c[2].x;
		auxY=c[2].y;
		
		c[2].x=c[1].x;
		c[2].y=c[1].y;
		c[0].mouredreta();
		
		
		x2=c[0].x+1; 
		y2=c[0].y+1;
		w2=c[0].ample-2;
		h2=c[0].alt-2; 
		
		
		x1=c[2].x;
		y1=c[2].y;
		w1=c[2].ample;
		h1=c[2].alt;
					
		if( x1 > x2+w2 ){
		}
		else if ( x1+w1 < x2 ){
		}
		else if ( y1 > y2+h2 ){
		}
		else if ( y1+h1 < y2 ) {
		}
		else{
			c[0].moureesquerra();
			c[2].x=auxX;
			c[2].y=auxY;
			
			return 1;
		}
		c[0].moureesquerra();
		c[2].x=auxX;
		c[2].y=auxY;
		return 0;
	}
	
	
	void cargarImagenes(){
		try {
			CotxeSnake.img = ImageIO.read(new File("../img/imgsnake.png"));
			BalaSnake.img = ImageIO.read(new File("../img/imgsnake2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void records() throws IOException{
		
		FileInputStream input = null;
		try {
			input = new FileInputStream("../txt/hi-scoressnake.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader file = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		
		String score1;
		score1 = file.readLine();
		
		hiscoreNum=Integer.valueOf(score1);
		
		String[] score2 = score1.split("(?<=.)"); //sempre tenim 5 zeros
        
        String part1=score2[0];
        String part2=score2[1];
        String part3=score2[2];
        String part4=score2[3];
        
        String score3= String.format("H I - S C O R E : %s %s %s %s",part1,part2,part3,part4);
		
        hiscore=score3;

		
	}
	
	
	void guardarRecords() throws IOException{
		if(score>hiscoreNum) {
			BufferedWriter bw = null;
		    try {
		    	String mycontent = String.format("%04d", score);
		    	File file = new File("../txt/hi-scoressnake.txt");
		    	if (!file.exists()) {
		    		file.createNewFile();
		    	}

		    	FileWriter fw = new FileWriter(file);
		    	bw = new BufferedWriter(fw);
		    	bw.write(mycontent);
		    } catch (IOException ioe) {
		    	ioe.printStackTrace();
			}
			finally{ 
				try{
					if(bw!=null)
						bw.close();
				}catch(Exception ex){
				}
			}
		}
		
	}
	

	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) { 
		
		//XOC UN MATEIX
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart=0;
		}			
				
				
				
		if(e.getKeyCode() == KeyEvent.VK_UP && down==0 && xocprevistup()==0) {
			esquerra=0;
			dreta=0;
			up=1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN && up==0 && xocprevistdown()==0) {
			esquerra=0;
			dreta=0;
			down=1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT && dreta==0 && xocprevistesquerra()==0) {
			esquerra=1;
			up=0;
			down=0;
					
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT && esquerra==0 && xocprevistdreta()==0) {
			dreta=1;
			up=0;
			down=0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			end=1;
		}
		
		else {
			
		}
        
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}

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

public class Joc extends Thread  implements KeyListener{
	Finestra f;
	Graphics grafics;
	int y;
	int stopesquerra;
	int stopdreta;
	int stopjoc;
	int stopjoc2;
	int credits;
	int vidas;
	int score;
	int reanimar; //per no fer bug al pintar al reanimar en la nau moventse amb Keypressed
	int hiscoreNum;
	int nivell;
	int reinicia;
	int dreta;
	int esquerra;
	int end;
	String hiscore;
	int col[];
	int posicio[]; //anar cap a la dreta=1 sino 0
	Cotxe c[]=new Cotxe[55];
	Nau nau;
	Escut escut[]=new Escut[4];
	Bala bala[]=new Bala[9999];
	BalaDolenta balaDolenta[]=new BalaDolenta[9999];
	
	int balamoguda[]; //totes les bales
	int balaAmmo; //numero bala
	
	
	Joc(Finestra f) {
		f.addKeyListener(this);
		this.f=f;
		y=50;
		grafics = f.g;

	}
	
	void executa() {
		int bucle;
		credits=1;
		nivell=1;
		end=0;
		while(end==0) {
			if(nivell!=1) {
				if(vidas<3) {
					vidas++;
				}
			}
			reinicia=0;
			if(credits!=0 && end==0) {
				bucle=1;
				try {
					records();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inicialitza();
				pintaConstants();
				while(vidas>=1 && reinicia==0 && end==0) {
					nivellGuanyat();
					calculaMoviments();
					disparaEnemics();
					comprovaXocs();
					repintaPantalla();
					
					for(int i=0;i<c.length;i++) {
						if(bucle<10) { //velocitat marcianitos
							c[i].mov0();
						}
						else {
							c[i].mov1();
						}
						
					}
					bucle++;
					if(bucle==21) {
						bucle=1;
					}
					
					
					//repintaPantalla();
					
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(reinicia==0) {
					credits--;
					nivell=1;
				}
				try {
					guardarRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				repintaPantalla();
				if(end==1) {
					break;
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	void pintaConstants() {
		/*grafics.setFont(new Font("TimesRoman", Font.PLAIN, 30));*/
		Font myFont;
		try
	    {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("../font/font.ttf")).deriveFont(15f);
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
		
		grafics.setColor(Color.DARK_GRAY);
		grafics.fillRect(0, 0, 110,900);
		grafics.fillRect(505, 0,115,900);
		
		
	}
	
	void inicialitza(){

		int t=0;
		stopesquerra=0;
		stopdreta=0;
		balaAmmo=0;
		reanimar=0;
		dreta=0;
		esquerra=0;
		if(nivell==1) {
			score=0;
			vidas=3;
		}
		balamoguda = new int[9999];
		posicio = new int[c.length];
		col = new int[c.length];
		nau=new Nau(300,500,20,15,0);
							/* 40,40*/
		
		
		for(int i=0;i<4;i++) {
			escut[i]=new Escut(137+(i)*100,465,40,20,7);
		}
		
		
		for(int i=0;i<9999;i++) {
			bala[i]=new Bala(310,490,3,10);
			balamoguda[i]=0;
		}		
		for(int j=0;j<5;j++){
			for(int i=0;i<11;i++){
				balaDolenta[t]=new BalaDolenta(100+28*(i+1)+8,100+40*(j+1)+20,3,10);
				c[t]=new Cotxe(100+28*(i+1),100+40*(j+1),20,14,1,j,nivell);
														/*20,20*/
				col[t]=0;
				posicio[t]=1;
				t++;
			}
		}
		
		cargarImagenes();
		
		

	}
	
	
	void disparaEnemics() {
		int morts=0,a,b,prob=1;
		int v; //velocitat disparar //com mes petit es el numero mes rapid disparen
		
		v=(41-2*nivell);
		
		a=(int) Math.floor(Math.random()*v); 
		b=(int) Math.floor(Math.random()*11); //un numero del 0 al 10 per saber columna q dispara
		
		for(int i=0;i<c.length;i++) { //comptem morts per pujar la probabilitat de disparar
			if(c[i].mort==1) {
				morts++;
			}
		}
		
		
		if(morts<10) {
			prob=2;
		}
		else if(morts<20) {
			prob=3;
		}
		else if(morts<25) {
			prob=4;
		}
		else if(morts<30) {
			prob=6;
		}
		else if(morts<35) {
			prob=8;
		}	
		else if(morts<40) {
			prob=10;
		}	
		else if(morts<45) {
			prob=12;
		}	
		else {
			prob=15;
		}
		
		
		
		if(a<prob) { // dispara amb probabilitat random
			for(int k=0;k<11;k++) {
				if(b==k) {
					for(int i=4;i>=0;i--) {
						if(c[k+11*i].mort==0) {
							balaDolenta[k+11*i].disparada=1;
							break;
						}
					}
					break;
				}
			}
		}
	}
		
		
	
	
	
	void calculaMoviments() {
		int aux;
		
		
		
		
		
		if(esquerra==1 && stopesquerra==0) {
			if(reanimar==0) {
				nau.mourexesquerra();
				for(int i=0;i<9999;i++) {
					if(balamoguda[i]==0) {
						bala[i].mourexesquerra();
					}
				}
			}
			dreta=0;
			
		}
		if(dreta==1 && stopdreta==0) {
			if(reanimar==0) {
				nau.mourexdreta();
				for(int i=0;i<9999;i++) {
					if(balamoguda[i]==0) {
						bala[i].mourexdreta();
					}
				}
			}
			esquerra=0;
			
		}
		
		
		
		
		
		
		
		
		
		
		if(nivell==1) {
			aux=1;
		}
		else if(nivell==2) {
			aux=1;
		}
		else if(nivell==3) {
			aux=1;
		}
		else if(nivell==4) {
			aux=2;
		}
		else if(nivell==5) {
			aux=2;
		}
		else {
			aux=3;
		}
		for(int i=0;i<c.length;i++) {
			if(posicio[i]==1) {
				balaDolenta[i].mourexdreta();
				c[i].mourexdreta();
				if(col[i]==(int) Math.floor(50/aux)) {
					posicio[i]=0;
					balaDolenta[i].moureyabaixmov();
					c[i].mourey();
					col[i]=col[i]-2;
				}
				col[i]++;
			}
			else{
				balaDolenta[i].mourexesquerra();
				c[i].mourexesquerra();
				if(col[i]==0) {
					posicio[i]=1;
					balaDolenta[i].moureyabaixmov();
					c[i].mourey();
					col[i]=col[i]+2;
				}
				col[i]--;
			}
			if(c[i].mort==1) {
				c[i].x=0;
				c[i].y=0;
			}
		}
		for(int i=0;i<9999;i++) {
			if(balamoguda[i]==1) {
				if(bala[i].mort==0) {
					bala[i].moureyamunt();
				}
				else {
					bala[i].x=0;
					bala[i].y=0;
				}
			}
		}
		for(int i=0;i<c.length;i++) {
			if(balaDolenta[i].disparada==1) {
				if(balaDolenta[i].mort==0) {
					balaDolenta[i].moureyabaix();
				}
				else {
					balaDolenta[i].x=0;
					balaDolenta[i].y=0;
				}
			}
		}
	}
			
	
	void comprovaXocs() {
		
		int x1,y1,w1,h1;
		int x2,y2,w2,h2;
		// Rectángulo 1 con esquina superior izquierda en (x1,y1) ancho w1 y alto h1
		// Rectángulo 2 con esquina superior izquierda en (x2,y2) ancho w2 y alto h2		
		
		
		//PARAR XOC ESQUERRA NAU
		x1=nau.x;
		y1=nau.y;
		w1=20;
		h1=15;	
		
		x2=100; //un punt menys pq te v=2;
		y2=0;
		w2=20;
		h2=900; 
		if( x1 > x2+w2 ){
			stopesquerra=0;
		}
		else if ( x1+w1 < x2 ){
			stopesquerra=0;
		}
		else if ( y1 > y2+h2 ){
			stopesquerra=0;
		}
		else if ( y1+h1 < y2 ) {
			stopesquerra=0;
		}
		else{
			stopesquerra=1;
		}
		
		
		
		//PARAR XOC DRETA NAU
		x2=495; 
		y2=0;
		w2=20;
		h2=900; 
		if( x1 > x2+w2 ){
			stopdreta=0;
		}
		else if ( x1+w1 < x2 ){
			stopdreta=0;
		}
		else if ( y1 > y2+h2 ){
			stopdreta=0;
		}
		else if ( y1+h1 < y2 ) {
			stopdreta=0;
		}
		else{
			stopdreta=1;
		}
		
		
		//PARAR JOC QUAN ES PERD amb marcianitos arribant abaix
		
		x2=0;
		y2=450;
		w2=800;
		h2=1;
		
		for(int i=0;i<c.length;i++) {
			x1=c[i].x;
			y1=c[i].y;
			w1=20;
			h1=14;
			
			if( x1 > x2+w2 ){
			}
			else if ( x1+w1 < x2 ){
			}
			else if ( y1 > y2+h2 ){
			}
			else if ( y1+h1 < y2 ) {
			}
			else{
				vidas=0;
				break;
			}
			
			
		}
		
		
		//PARAR JOC QUAN ES PERD amb balaDolenta
		
		x2=nau.x;
		y2=nau.y;
		w2=20;
		h2=15;
		
		for(int i=0;i<c.length;i++) {
			x1=balaDolenta[i].x;
			y1=balaDolenta[i].y;
			w1=3;
			h1=10;
			
			if( x1 > x2+w2 ){
			}
			else if ( x1+w1 < x2 ){
			}
			else if ( y1 > y2+h2 ){
			}
			else if ( y1+h1 < y2 ) {
			}
			else{
				vidas--;
				reanimar=1;
				
				
				//retornem totes les bales
				for(int j=0;j<c.length;j++) {
					if(c[j].mort==0) {
						balaDolenta[j].mort=0;
						balaDolenta[j].disparada=0;
						balaDolenta[j].x=c[j].x+8;
						balaDolenta[j].y=c[j].y+20;
					}
					else {
						balaDolenta[j].mort=1;
						balaDolenta[j].x=0;
						balaDolenta[j].y=0;
					}
				}
				
				//soluciona bug amb bala
				if(balaDolenta[i].mort==0) {
					balaDolenta[i].disparada=0;
					balaDolenta[i].x=c[i].x+8;
					balaDolenta[i].y=c[i].y+20;
				}
				
				else {
					balaDolenta[i].mort=1;
					balaDolenta[i].x=0;
					balaDolenta[i].y=0;
				}
				
				
				
			
				
				repintaPantalla();
				
				nau.naverota();
				nau.pinta(f.g);
				
				repintaPantalla();
				f.repaint();
				
				
				
				if(vidas!=0) { //efecte reanimar
					
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					nau.navebe();
					nau.pinta(f.g);
					f.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					nau.naverota();
					nau.pinta(f.g);
					f.repaint();
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					nau.navebe();
					nau.pinta(f.g);
					f.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					nau.naverota();
					nau.pinta(f.g);
					f.repaint();
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					nau.navebe();
					nau.pinta(f.g);	
					reanimar=0;
					
					
				}

				
				
				
				
				
				break;
			}
			
			
		}
		
		
		
		//xoc bala en moviment amb marcianitos
		
		for(int j=0;j<balaAmmo;j++) {
			x2=bala[j].x;
			y2=bala[j].y;
			w2=3;
			h2=10;
			
			for(int i=0;i<c.length;i++) {
				x1=c[i].x;
				y1=c[i].y;
				w1=20;
				h1=14;
				
				if( x1 > x2+w2 ){
				}
				else if ( x1+w1 < x2 ){
				}
				else if ( y1 > y2+h2 ){
				}
				else if ( y1+h1 < y2 ) {
				}
				else{
					c[i].mort=1;
					bala[j].mort=1;
					if(balaDolenta[i].disparada==0) {
						balaDolenta[i].mort=1;
						balaDolenta[i].x=0;
						balaDolenta[i].y=0;
					}
					
					if(0<=i && i<=10 && c[i].x!=0) {
						score=score+5;
					}
					else if(11<=i && i<=21 && c[i].x!=0) {
						score=score+3;
					}
					else if(22<=i && i<=32 && c[i].x!=0) {
						score=score+3;
					}
					else if(33<=i && i<=43 && c[i].x!=0) {
						score=score+1;
					}
					else if(44<=i && i<=54 && c[i].x!=0) {
						score=score+1;
					}

					
				}
				
				
			}
		}
		
		
		
		//xoc balaDolenta en moviment amb Escuts
		
		for(int j=0;j<4;j++) {
			x2=escut[j].x;
			y2=escut[j].y;
			w2=40;
			h2=10;
			
			for(int i=0;i<c.length;i++) {
				x1=balaDolenta[i].x;
				y1=balaDolenta[i].y;
				w1=3;
				h1=10;
				
				if( x1 > x2+w2 ){
				}
				else if ( x1+w1 < x2 ){
				}
				else if ( y1 > y2+h2 ){
				}
				else if ( y1+h1 < y2 ) {
				}
				else{
					escut[j].vidas--;
					if(c[i].mort==1) {
						balaDolenta[i].mort=1;
						balaDolenta[i].x=0;
						balaDolenta[i].y=0;
					}
					else {
						balaDolenta[i].disparada=0;
						balaDolenta[i].x=c[i].x+8;
						balaDolenta[i].y=c[i].y+20;
					}
				}
				
				
			}
		}
		
		
		//xoc bala en moviment amb Escuts
		
		for(int j=0;j<4;j++) {
			x2=escut[j].x;
			y2=escut[j].y;
			w2=40;
			h2=10;
			
			for(int i=0;i<9999;i++) {
				x1=bala[i].x;
				y1=bala[i].y;
				w1=3;
				h1=10;
				
				if( x1 > x2+w2 ){
				}
				else if ( x1+w1 < x2 ){
				}
				else if ( y1 > y2+h2 ){
				}
				else if ( y1+h1 < y2 ) {
				}
				else{
					bala[i].mort=1;
					bala[i].x=0;
					bala[i].y=0;
				}
				
				
			}
		}
		
		//retorna BalaDolenta
		
		x2=100;
		y2=550;
		w2=900;
		h2=1;
		
		for(int i=0;i<c.length;i++) {
			x1=balaDolenta[i].x;
			y1=balaDolenta[i].y;
			w1=3;
			h1=10;
			
			if( x1 > x2+w2 ){
			}
			else if ( x1+w1 < x2 ){
			}
			else if ( y1 > y2+h2 ){
			}
			else if ( y1+h1 < y2 ) {
			}
			else{
				if(c[i].mort==1) {
					balaDolenta[i].mort=1;
					balaDolenta[i].x=0;
					balaDolenta[i].y=0;
				}
				else {
					balaDolenta[i].disparada=0;
					balaDolenta[i].x=c[i].x+8;
					balaDolenta[i].y=c[i].y+20;
				}
			}
			
			
		}
		
		
	}
	
	void nivellGuanyat() {
		int aux=0;
		for(int i=0;i<c.length;i++) {
			if(c[i].mort==1) {
				aux++;
			}
		}
		if(aux==55) {
			reinicia=1;
			nivell++;
			
						
			
		}


		
		
		
		
		
		
		
		
	}
	
	
	void repintaPantalla() {
		//f.g.clearRect(0, 0, f.AMPLE,f.ALT);
		f.g.setColor(Color.BLACK);
		f.g.fillRect(110,0,395,f.ALT);
		
		f.g.setColor(Color.WHITE);
		grafics.drawString("S C O R E  < 1 >", 140, 70);
				  
        
        String score1 = String.format("%05d", score);
        
        String[] score2 = score1.split("(?<=.)"); //sempre tenim 5 zeros
        
        String part1=score2[0];
        String part2=score2[1];
        String part3=score2[2];
        String part4=score2[3];
        String part5=score2[4];
        
        String score3= String.format("%s %s %s %s %s",part1,part2,part3,part4,part5);
		grafics.drawString(score3, 140, 100);
		
		
		
		
		
		grafics.drawString("H I - S C O R E", 360, 70);
		if(score>hiscoreNum) {
			grafics.drawString(score3, 400, 100);	
		}
		else {
			grafics.drawString(hiscore, 400, 100);	
		}
		
		if(credits==0) {
			grafics.drawString("C R E D I T   0 0", 350, 560);
		}
		else if(credits==1) {
			grafics.drawString("C R E D I T   0 1", 350, 560);
		}
		else if(credits==2) {
			grafics.drawString("C R E D I T   0 2", 350, 560);
		}
		else if(credits==3) {
			grafics.drawString("C R E D I T   0 3", 350, 560);
		}
		else if(credits==4) {
			grafics.drawString("C R E D I T   0 4", 350, 560);
		}
		else if(credits==5) {
			grafics.drawString("C R E D I T   0 5", 350, 560);
		}
		else if(credits==6) {
			grafics.drawString("C R E D I T   0 6", 350, 560);
		}
		else {
			grafics.drawString("C R E D I T   + +", 350, 560);
		}
		
		
		
		if(vidas==0) {
			grafics.drawString("G A M E   O V E R", 140, 560);
		}
		else if(vidas==1) {
			grafics.drawString("V I D A S  :  1", 140, 560);
		}
		else if(vidas==2) {
			grafics.drawString("V I D A S  :  2", 140, 560);
		}
		
		else {
			grafics.drawString("V I D A S  :  3", 140, 560);
		}
		
		//f.g.setColor(Color.WHITE);
		//f.g.drawLine(50, 50, 300, y);
		
		for(int i=0;i<c.length;i++) {
			c[i].pinta(f.g);
			balaDolenta[i].pinta(f.g);
		}
		for(int i=0;i<balaAmmo;i++) {
			bala[i].pinta(f.g);
		}
		nau.pinta(f.g);
		for(int i=0;i<4;i++) {
			escut[i].pinta(f.g);
		}
		f.repaint();
		
	}
	
	void records() throws IOException{
		
		FileInputStream input = null;
		try {
			input = new FileInputStream("../txt/hi-scores.txt");
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
        String part5=score2[4];
        
        String score3= String.format("%s %s %s %s %s",part1,part2,part3,part4,part5);
		
        hiscore=score3;

		
	}
	
	void guardarRecords() throws IOException{
		if(score>hiscoreNum) {
			BufferedWriter bw = null;
		    try {
		    	String mycontent = String.format("%05d", score);
		    	File file = new File("../txt/hi-scores.txt");
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
	
	
	
	void cargarImagenes(){
		try {
			Nau.img = ImageIO.read(new File("../img/nave.png"));
			Cotxe.calamar1 = ImageIO.read(new File("../img/calamar1.png"));
			Cotxe.calamar2 = ImageIO.read(new File("../img/calamar2.png"));
			Cotxe.marciano1 = ImageIO.read(new File("../img/marciano1.png"));
			Cotxe.marciano2 = ImageIO.read(new File("../img/marciano2.png"));
			Cotxe.cotxe1 = ImageIO.read(new File("../img/cotxe1.png"));
			Cotxe.cotxe2 = ImageIO.read(new File("../img/cotxe2.png"));
			Escut.escudo1 = ImageIO.read(new File("../img/escudo1.png"));
			Escut.escudo2 = ImageIO.read(new File("../img/escudo2.png"));
			Escut.escudo3 = ImageIO.read(new File("../img/escudo3.png"));
			Escut.escudo4 = ImageIO.read(new File("../img/escudo4.png"));
			Escut.escudo5 = ImageIO.read(new File("../img/escudo5.png"));
			Escut.escudo6 = ImageIO.read(new File("../img/escudo6.png"));
			Escut.escudo7 = ImageIO.read(new File("../img/escudo7.png"));
			BalaDolenta.baladolenta1 = ImageIO.read(new File("../img/baladolenta1.png"));
			Bala.bala = ImageIO.read(new File("../img/bala.png"));
			Nau.naverota = ImageIO.read(new File("../img/naverota.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			balamoguda[balaAmmo]=1;
			balaAmmo++;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && stopesquerra==0) {
			esquerra=0;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && stopdreta==0) {
			dreta=0;
			
		}	
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) { 
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			credits++;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && stopesquerra==0) {
			esquerra=1;
			dreta=0;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && stopdreta==0) {
			dreta=1;
			esquerra=0;
			
		}	
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			end=1;
		}
        
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}

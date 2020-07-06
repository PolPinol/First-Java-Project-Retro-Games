import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Cotxe {
	int x;
	int y;
	int ample;
	int alt;
	double v;
	int mort;
	int fila;
	int moviment; //canvien les img
	int nivell;
	
	static Image calamar1;
	static Image calamar2;
	static Image marciano1;
	static Image marciano2;	
	static Image cotxe1;
	static Image cotxe2;
	
	Cotxe(int x,int y,int ample,int alt,double v,int fila,int nivell) { //fila 0..4
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.v=v;
		this.mort=0;
		this.fila=fila;
		this.moviment=0;
		this.nivell=nivell;
	}
	void mourexdreta() {
		if(nivell==1) {
			x+=v;
		}
		else if(nivell==2) {
			x+=v;
		}
		else if(nivell==3) {
			x+=v;
		}
		else if(nivell==4) {
			x+=v+1;
		}
		else if(nivell==5) {
			x+=v+1;
		}
		else {
			x+=v+3;
		}
	}
	void mourexesquerra() {
		if(nivell==1) {
			x-=v;
		}
		else if(nivell==2) {
			x-=v;
		}
		else if(nivell==3) {
			x-=v;
		}
		else if(nivell==4) {
			x-=v+1;
		}
		else if(nivell==5) {
			x-=v+1;
		}
		else {
			x-=v+3;
		}
	}
	void mourey() {
		y=y+10;
	}
	void mov0() {
		moviment=0;
	}
	void mov1() {
		moviment=1;
	}
	void pinta(Graphics g) {
		g.setColor(Color.YELLOW);
		if(fila==0) {
			if(moviment==0) {
				g.drawImage(calamar1,x,y, ample,alt, null);
			}
			else {
				g.drawImage(calamar2,x,y, ample,alt, null);
			}
			
		}
		else if(fila==1 || fila==2) {
			if(moviment==0) {
				g.drawImage(marciano1,x,y, ample,alt, null);
			}
			else {
				g.drawImage(marciano2,x,y, ample,alt, null);
			}
			
			
		}
		else {
			if(moviment==0) {
				g.drawImage(cotxe1,x,y, ample,alt, null);
			}
			else {
				g.drawImage(cotxe2,x,y, ample,alt, null);
			}
		}
		
		
		
		//lineas
		
		
		/*
		g.drawLine(100, 0,100,900);
		g.drawLine(550, 0,550,900);
		
		g.drawLine(100, 455,150,455);
		//g.drawLine(150, 465,180,465);
		g.drawLine(180, 455,230,455);
		//g.drawLine(230, 465,260,465);
		g.drawLine(260, 455,310,455);
		//g.drawLine(310, 465,340,465);
		g.drawLine(340, 455,390,455);
		//g.drawLine(390, 465,420,465);
		g.drawLine(420, 455,470,455);
		//g.drawLine(470, 465,500,465);
		g.drawLine(500, 455,550,455);
		
			
		g.drawLine(100, 550,550,550);*/
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Nau {
	int x;
	int y;
	int ample;
	int alt;
	int rota;
	
	static Image img;
	static Image naverota;
	
	Nau(int x,int y,int ample,int alt,int rota) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.rota=rota;
		
	}
	void mourexdreta() {
		x+=7;
	}
	void mourexesquerra() {
		x-=7;
	}
	void naverota() {
		rota=1;
	}
	void navebe() {
		rota=0;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		if(rota==0) {
			g.drawImage(img,x,y, ample,alt, null);
		}
		else {
			g.drawImage(naverota,x,y, ample,alt, null);
		}
		
	}
}

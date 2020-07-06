import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Bala {
	int x;
	int y;
	int ample;
	int alt;
	int mort;
	
	static Image bala;
	
	Bala(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.mort=0;
	}
	void moureyamunt() {
		y-=30;
	}
	void moureyabaix() {
		y-=30;
	}
	void moureyabaixmov() {
		y+=10;
	}
	void mourexdreta() {
		x+=7;
	}
	void mourexesquerra() {
		x-=7;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawImage(bala,x,y, ample,alt, null);
		//g.drawRect(x, y, ample,alt);
	}
}

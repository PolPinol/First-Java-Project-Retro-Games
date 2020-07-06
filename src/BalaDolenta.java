import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BalaDolenta {
	int x;
	int y;
	int ample;
	int alt;
	int mort;
	int disparada;

	static Image baladolenta1;
	
	BalaDolenta(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.mort=0;
		this.disparada=0;
	}
	void moureyabaix() {
		y+=20;
	}
	void moureyabaixmov() {
		y+=10;
	}
	void mourexdreta() {
		x+=1;
	}
	void mourexesquerra() {
		x-=1;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		if(disparada==1) {
			g.drawImage(baladolenta1,x,y, ample,alt, null);
			//g.drawRect(x, y, ample,alt);
		}
	}
}

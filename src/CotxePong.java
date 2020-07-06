import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class CotxePong {
	int x;
	int y;
	int ample;
	int alt;
	
	
	CotxePong(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
	}
	void up() {
		y-=4;
	}
	
	void down() {
		y+=4;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,ample,alt);
		
		
	}
}

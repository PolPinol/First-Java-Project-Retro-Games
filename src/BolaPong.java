import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BolaPong {
	int x;
	int y;
	int ample;
	int alt;
	
	
	BolaPong(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
	}
	void moure(int vx, int vy) {
		x=x+vx;
		y=y+vy;
	}
	
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,ample,alt);
		
		
	}
}

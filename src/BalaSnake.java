import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BalaSnake {
	int x;
	int y;
	int ample;
	int alt;
	int mort;
	
	static Image img;
	
	BalaSnake(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.mort=0;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawImage(img,x,y, ample,alt, null);
	}
}

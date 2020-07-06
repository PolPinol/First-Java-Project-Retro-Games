import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

class InstruccionsImatge {
	int x;
	int y;
	int ample;
	int alt;
	
	static Image img1;
	
	InstruccionsImatge(int x,int y,int ample,int alt) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
	}
	
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawImage(img1,x,y, ample,alt, null);
		
		//g.drawRect(x, y, ample,alt);
	}
}

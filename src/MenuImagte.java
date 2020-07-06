import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

class MenuImatge {
	int x;
	int y;
	int ample;
	int alt;
	int mort;
	int tipus;
	
	static Image img1;
	static Image img2;
	static Image img3;
	
	MenuImatge(int x,int y,int ample,int alt, int tipus) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.tipus=tipus;
	}
	
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		if(tipus==1) {
			g.drawImage(img1,x,y, ample,alt, null);
		}
		else if(tipus==2) {
			g.drawImage(img2,x,y, ample,alt, null);
		}
		else {
			g.drawImage(img3,x,y, ample,alt, null);
		}
		
		//g.drawRect(x, y, ample,alt);
	}
}

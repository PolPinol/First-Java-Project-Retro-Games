import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Escut {
	int x;
	int y;
	int ample;
	int alt;
	int vidas;
	
	static Image escudo1;
	static Image escudo2;
	static Image escudo3;
	static Image escudo4;
	static Image escudo5;
	static Image escudo6;
	static Image escudo7;
	
	Escut(int x,int y,int ample,int alt,int vidas) {
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.vidas=vidas;
		
	}

	void pinta(Graphics g) {
		g.setColor(Color.GREEN);
		if(vidas==7) {
			g.drawImage(escudo1,x,y, ample,alt, null);
		}
		else if(vidas==6) {
			g.drawImage(escudo2,x,y, ample,alt, null);
		}
		else if(vidas==5) {
			g.drawImage(escudo3,x,y, ample,alt, null);
		}
		else if(vidas==4) {
			g.drawImage(escudo4,x,y, ample,alt, null);
		}
		else if(vidas==3) {
			g.drawImage(escudo5,x,y, ample,alt, null);
		}
		else if(vidas==2) {
			g.drawImage(escudo6,x,y, ample,alt, null);
		}
		else if(vidas==1) {
			g.drawImage(escudo7,x,y, ample,alt, null);
		}
		else {
			x=0;
			y=0;
		}
		
		
		
		//g.drawRect(x, y, ample,alt);
	}
}

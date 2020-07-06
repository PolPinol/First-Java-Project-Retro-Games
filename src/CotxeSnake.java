import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class CotxeSnake {
	int x;
	int y;
	int ample;
	int alt;
	double v;
	int viu;
	
	static Image img;
	
	CotxeSnake(int x,int y,int ample,int alt,double v,int viu) { //fila 0..4
		this.x=x;
		this.y=y;
		this.ample=ample;
		this.alt=alt;
		this.v=v;
		this.viu=viu;
	}
	void crea() {
		viu=1;
	}
	void mouredreta() {
		x+=v;
	}
	void moureesquerra() {
		x-=v;
	}
	void moureup() {
		y-=v;
	}
	void mouredown() {
		y+=v;
	}
	void pinta(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawImage(img,x,y, ample,alt, null);
		
		
	}
}

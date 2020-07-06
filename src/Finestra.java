import java.awt.*;
import java.lang.*;
import java.awt.event.*;

public class Finestra extends Frame implements WindowListener{

	int AMPLE=615;
	int ALT=600;
	
	Menu menu;
	Image img;
	Graphics g;
	
	
	
	public static void main(String[] args) {
		new Finestra();
	}
	Finestra() {
		this.setSize(AMPLE, ALT);
		this.setVisible(true);
		addWindowListener(this);
		img=createImage(AMPLE,ALT);
		g=img.getGraphics();
		
		menu=new Menu(this);
		menu.executa();
		
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowClosing(WindowEvent e){System.exit(0);}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
		
	

}

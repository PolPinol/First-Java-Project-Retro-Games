import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.lang.String;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.imageio.ImageIO;

public class Instrucciones extends Thread  implements KeyListener{
	
	Finestra f;
	Graphics grafics;
	InstruccionsImatge joc;
	int end;
	
	

	Instrucciones(Finestra f) {
		f.addKeyListener(this);
		this.f=f;
		grafics = f.g;

	}
	
	public void executa() {
		inicialitza();
		while(end==0) {
			repintaPantalla();
			
			
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		

		
		
		
	}
	

	
	void inicialitza(){
		cargarImagenes();
		joc= new InstruccionsImatge(50,50,500,520);
		
		end=0;
		
	}
	
	
	
	
	


	
	
	void repintaPantalla() {
		

		
		f.g.setColor(Color.BLACK);
		f.g.fillRect(0,0,615,600);
		joc.pinta(f.g);
				
		
		
		f.repaint();
		
	}
	
	
	void cargarImagenes(){
		try {
			InstruccionsImatge.img1 = ImageIO.read(new File("../img/inst.png"));		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	

	

	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent k) { 
					
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			end=1;
		}
			
		
	
        
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}

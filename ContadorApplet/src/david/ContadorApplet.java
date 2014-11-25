package david;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContadorApplet extends Applet implements Runnable, ActionListener{
	private static final long serialVersionUID = 1L;
	private Thread hilo = null;
	private boolean parar;
	long contador = 0;
	private Font fuente;
	private Button b1, b2;
	
	public void init(){
		setBackground(Color.green);
		add(b1 = new Button("Iniciar contador"));
		b1.addActionListener(this);
		
		add(b2 = new Button("Parar contador"));
		b2.addActionListener(this);
	}
	
	public void start(){
	}
	
	public void run(){
		parar = false;
		Thread hiloActual = Thread.currentThread();
		while (hilo == hiloActual && !parar) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			repaint();
			contador++;
		}
	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, 400, 400);
		g.setFont(fuente);
		g.drawString(Long.toString((long)contador), 80, 100);
	}

	public void actionPerformed(ActionEvent e) {
		b1.setLabel("Continuar");
		if(e.getSource() == b1){
			if(hilo != null && hilo.isAlive()){
				
			}else{
				hilo = new Thread(this);
				hilo.start();
			}
		}else if(e.getSource() == b2){
			parar = true;
		}
		
	}

}

package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.PanelJuego;
import base.Pantalla;

public class PantallaMuerte implements Pantalla{
	PanelJuego panelJuego;
	BufferedImage imagenOriginal;
	Image imagenReescalada;
	Font fuentePuntuacion;
	Font fuenteVolverAJugar;
	private int puntuacion;
	
	public PantallaMuerte(PanelJuego panelJuego) {
		this.panelJuego=panelJuego;
	}
	
	@Override
	public void inicializarPantalla() {
		fuentePuntuacion=new Font("Impact", Font.BOLD, 36);
		fuenteVolverAJugar=new Font("Impact", Font.ITALIC, 24);
		try {
			imagenOriginal=ImageIO.read(new File("Imagenes/gameover.jpg"));
			imagenReescalada=imagenOriginal.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(imagenReescalada, 0, 0, null);
		pintarPuntuacion(g);
		pintarVolverAJugar(g);
	}

	@Override
	public void ejecutarFrame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		if((e.getY()>(panelJuego.getHeight()/2)+(panelJuego.getHeight()/3)-fuenteVolverAJugar.getSize())) {
			PantallaInicial pantallaInicial= new PantallaInicial(panelJuego);
			pantallaInicial.inicializarPantalla();
			panelJuego.setPantallaActual(pantallaInicial);
		}
		
	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		imagenReescalada=imagenOriginal.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);

		
	}
	private void pintarPuntuacion(Graphics g) {
		int posX=(panelJuego.getWidth()/2)-(panelJuego.getWidth()/12),
				posY=(panelJuego.getHeight()/2)+(panelJuego.getHeight()/4);
		g.setColor(Color.RED);
		g.setFont(fuentePuntuacion);
		g.drawString("Puntuacion: "+Integer.toString(puntuacion), posX, posY);
	}
	private void pintarVolverAJugar(Graphics g) {
		int posX=(panelJuego.getWidth()/2)-(panelJuego.getWidth()/12),
				posY=(panelJuego.getHeight()/2)+(panelJuego.getHeight()/3);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Volver A Jugar", posX, posY);
	}
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}

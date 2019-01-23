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

public class PantallaInicial implements Pantalla {
	PanelJuego panelJuego;

	BufferedImage imagenOriginal;
	Image imagenReescalada;
	Font fuenteInicio = new Font("Impact", Font.BOLD, 36);
	Color colorLetras = Color.RED;

	public PantallaInicial(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}

	@Override
	public void inicializarPantalla() {
		try {
			imagenOriginal = ImageIO.read(new File("Imagenes/inicio.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(imagenReescalada, 0, 0, null);
		if (panelJuego.getWidth() > 0)
			reescalarImagen();

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

		int posY = e.getY();
		if ((posY < panelJuego.getHeight() / 2) && (posY > panelJuego.getHeight()/ 3) ) {
			PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
			pantallaJuego.inicializarPantalla();
			panelJuego.setPantallaActual(pantallaJuego);
		}

	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		reescalarImagen();

	}

	private void reescalarImagen() {
		imagenReescalada = imagenOriginal.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);

	}
}

package base;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import pantallas.PantallaInicial;

public class PanelJuego extends JPanel implements Runnable, MouseListener, ComponentListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pantalla pantallaActual;
	/**
	 * Constructor de PanelJuego. - Inicializa el arrayList de cuadrados. - Asigna
	 * el mouse listener que implementa la propia clase para lanazar nuevos
	 * cuadrados. - Inicia un hilo para actualizar el juego periódicamente.
	 */
	public PanelJuego() {

		this.addMouseListener(this);
		this.addComponentListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();

		pantallaActual=new PantallaInicial(this);
		pantallaActual.inicializarPantalla();

	}

	public Pantalla getPantallaActual() {
		return pantallaActual;
	}

	public void setPantallaActual(Pantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

	/**
	 * Sobreescritura del método paintComponent. Este método se llama
	 * automáticamente cuando se inicia el componente, se redimensiona o bien
	 * cuando se llama al método "repaint()". Nunca llamarlo directamente.
	 * 
	 * @param g
	 *            Es un Graphics que nos proveé JPanel para poner pintar el
	 *            componente a nuestro antojo.
	 */
	@Override
	public void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pantallaActual.ejecutarFrame();
			Toolkit.getDefaultToolkit().sync();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pantallaActual.pulsarRaton(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		pantallaActual.redimensionarPantalla(e);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pantallaActual.moverRaton(e);
	}
}

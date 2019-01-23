package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage buffersPersonaje[] = new BufferedImage[4];
	String rutaImagenesPersonaje[] = new String[4];
	private BufferedImage buffer;
	private Image imagenReescalada;
	private Color color = Color.RED;
	// Variables de dimensión
	private int ancho;
	private int alto;
	// Variables de colocación
	private int posX;
	private int posY;
	// Variables para la velocidad
	private int velocidadX;
	private int velocidadY;
	// Ruta de la imagen
	private String rutaImagen;

	/**
	 * Constructor simple para un Sprite con imagen  sin velocidad.
	 * 
	 * @param ancho Ancho que ocupa el Sprite (en pixels)
	 * @param alto  Altura que ocupa el Sprite (en pixels)
	 * @param posX  posicion horizontal del sprite en el mundo.
	 * @param posY  posicion vertical del Sprite en el mundo. El origen se sitúa
	 *              en la parte superior.
	 */
	public Sprite(int ancho, int alto, int posX, int posY, String rutaImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	/**
	 * Constructor para un Sprite con array de ruta imagen.
	 * 
	 * @param ancho      Ancho que ocupa el Sprite (en pixels)
	 * @param alto       Altura que ocupa el Sprite (en pixels)
	 * @param posX       posición horizontal del sprite en el mundo.
	 * @param posY       posición vertical del Sprite en el mundo. El origen se
	 *                   sitúa en la parte superior.
	 * @param velocidadX velocidad horizontal del Sprite.
	 * @param velocidadY velocidad vertical del Sprite.
	 * @param rutaImagenes Array de rutas a ficheros de Imagen
	 */
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, String[] rutaImagenes) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		for (int i = 0; i < rutaImagenes.length; i++) {
			this.rutaImagenesPersonaje[i] = rutaImagenes[i];
			try {
				this.buffersPersonaje[i] = ImageIO.read(new File(rutaImagenesPersonaje[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		actualizarBuffer();
	}
	/**
	 * Constructor para un Sprite con Imagen
	 * @param ancho 
	 * @param alto
	 * @param posX
	 * @param posY
	 * @param velocidadX
	 * @param velocidadY
	 * @param rutaImagen
	 */
	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, String rutaImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	/**
	 * Metodo para actualizar el buffer que guarda cada Sprite.
	 */
	public void actualizarBuffer() {
		buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();

		try {
			BufferedImage imagenSprite = ImageIO.read(new File(rutaImagen));
			// pinto en el buffer la imagen
			imagenReescalada = imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			g.drawImage(imagenReescalada, 0, 0, null);

		} catch (Exception e) {
			g.setColor(color);
			g.fillRect(0, 0, ancho, alto);
			g.dispose();
		}

	}

	/**
	 * Metodo para comprobar si el Sprite colisiona con otro.
	 * 
	 * @param otroSprite El otro Sprite con el que se comprueba si hay colisión.
	 * @return verdadero si ambos Sprites colisionan.
	 */
	public boolean colisionan(Sprite otroSprite) {
		// Checkeamos si comparten algún espacio a lo ancho:
		boolean colisionAncho = false;
		if (posX < otroSprite.getPosX()) { // El Sprite actual se encuentra más cerca del eje de las X.
			colisionAncho = posX + ancho >= otroSprite.getPosX();
		} else { // El otro Sprite se encuentra más cerca del eje de las X.
			colisionAncho = otroSprite.getPosX() + otroSprite.getAncho() >= posX;
		}

		// Checkeamos si comparten algún espacio a lo alto:
		boolean colisionAlto = false;
		if (posY < otroSprite.getPosY()) {
			colisionAlto = alto > otroSprite.getPosY() - posY;
		} else {
			colisionAlto = otroSprite.getAlto() > posY - otroSprite.getPosY();
		}

		return colisionAncho && colisionAlto;
	}

	/**
	 * Método para mover el Sprite por el mundo.
	 * 
	 * @param anchoMundo ancho del mundo sobre el que se mueve el Sprite
	 * @param altoMundo  alto del mundo sobre el que se mueve el Sprite
	 */
	public void moverSprite(int anchoMundo, int altoMundo) {
		if (posX >= anchoMundo - ancho) { // por la derecha
			velocidadX = -1 * Math.abs(velocidadX);
		}
		if (posX <= 0) {// por la izquierda
			velocidadX = Math.abs(velocidadX);
		}
		if (posY >= altoMundo - alto) {// por abajo
			velocidadY = -1 * Math.abs(velocidadY);
		}
		if (posY <= 0) { // Por arriba
			velocidadY = Math.abs(velocidadY);
		}
		posX = posX + velocidadX;
		posY = posY + velocidadY;
	}

	public void moverSprite() {
		posX = posX + velocidadX;
		posY = posY + velocidadY;
	}

	/**
	 * Método que pinta el Sprite en el mundo teniendo en cuenta las
	 * características propias del Sprite.
	 * 
	 * @param g Es el Graphics del mundo que se utilizará para pintar el Sprite.
	 */
	public void pintarSpriteEnMundo(Graphics g) {
		g.drawImage(buffer, posX, posY, null);
	}

	// Métodos para obtener:
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	public void setImage(int indice) {
		imagenReescalada =buffersPersonaje[indice].getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	// métodos para cambiar:
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setBuffer(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		actualizarBuffer();

	}

}

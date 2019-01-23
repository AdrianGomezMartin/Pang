package base;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class VentanaPrincipal {
	private JFrame ventana;
	private PanelJuego panelJuego;
	final static int ANCHO_VENTANA = 800, ALTO_VENTANA = 800;

	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(0, 0, ANCHO_VENTANA, ALTO_VENTANA);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Inicializar() {
		ventana.setVisible(true);
		InicializarComponentes();
	}

	private void InicializarComponentes() {
		ventana.setLayout(new GridLayout(1, 1));
		panelJuego= new PanelJuego();
		ventana.add(panelJuego);
		
	}

}

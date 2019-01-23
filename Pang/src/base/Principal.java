package base;

import java.awt.EventQueue;



public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			try {
				VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
				ventanaPrincipal.Inicializar();
			}catch (Exception e) {
				e.printStackTrace();
			}
		});

	}
}

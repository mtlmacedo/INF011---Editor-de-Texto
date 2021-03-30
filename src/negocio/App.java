package negocio;
import java.awt.EventQueue;

import javax.swing.JFrame;

import interfaces.ILangFactory;
import ui.MainFrame;

public class App {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
}


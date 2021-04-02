package inf011.app;
import java.awt.EventQueue;

import javax.swing.JFrame;

import inf011.services.PluginService;
import inf011.ui.MainFrame;

public class App {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					PluginService pluginService = new PluginService();
					JFrame frame = new MainFrame(pluginService);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
}


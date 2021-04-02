package inf011.ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import inf011.factorys.CppFactory;
import inf011.factorys.JavaFactory;
import inf011.interfaces.ILangFactory;
import inf011.services.FileService;
import inf011.services.PluginService;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Insets;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JFileChooser fileChooser;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblAbrirArquivo;
	private JButton btnAbrir;
	private JButton btnBuscar;
	private FileService fileService;
	
	public MainFrame() {	
		this.fileService = new FileService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.fileChooser = new JFileChooser();
		this.lblAbrirArquivo = new JLabel("Abrir Arquivo:");
		this.textField = new JTextField();		
		this.btnBuscar = new JButton("Buscar");		
		this.btnAbrir = new JButton("Abrir");
		
		this.initComponents();
	}
	private void initComponents() {
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CodeFiles", "java", "cpp");	
		this.fileChooser.addChoosableFileFilter(filter);
		this.fileChooser.setFileFilter(filter);
		
		this.initLayout();
				
		this.btnBuscar.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnBuscarOnClick();
	      	}
		});
		
		this.btnAbrir.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnAbrirOnClick();
	      	}
		});
		
	}
	private void initLayout() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{36, 67, 249, 65, 0};
		gbl_contentPane.rowHeights = new int[]{86, 23, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_lblAbrirArquivo = new GridBagConstraints();
		gbc_lblAbrirArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblAbrirArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbrirArquivo.gridx = 1;
		gbc_lblAbrirArquivo.gridy = 1;
		contentPane.add(lblAbrirArquivo, gbc_lblAbrirArquivo);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		contentPane.add(btnBuscar, gbc_btnBuscar);
		
		GridBagConstraints gbc_btnAbrir = new GridBagConstraints();
		gbc_btnAbrir.anchor = GridBagConstraints.NORTH;
		gbc_btnAbrir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbrir.insets = new Insets(0, 0, 0, 5);
		gbc_btnAbrir.gridx = 2;
		gbc_btnAbrir.gridy = 2;
		contentPane.add(btnAbrir, gbc_btnAbrir);
	}
		
	private void btnBuscarOnClick() {
		try {
			int response = this.fileChooser.showOpenDialog(this);
			
			if(response == JFileChooser.APPROVE_OPTION) {				
				this.textField.setText(this.fileChooser.getSelectedFile().getPath());
			}else {
				throw new Exception("NO FILE CHOOSEN!");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void btnAbrirOnClick() {
		try {
			String filePath = this.textField.getText();
			String extension = this.fileService.getExtension(filePath);

			fileService.validateExtension(filePath);
			
			fileService.validateExtension(filePath);
			PluginService pluginService = new PluginService();
			ILangFactory factory = pluginService.loadFactoryByExtension(extension); ;
			JFrame frame = factory.createTextArea(filePath);
			frame.setVisible(true);
			
			this.dispose();   	
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
	}
}

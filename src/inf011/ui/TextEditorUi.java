package inf011.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import inf011.factorys.CppFactory;
import inf011.factorys.JavaFactory;
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;
import inf011.services.FileService;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;

public class TextEditorUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private RTextScrollPane sp;
	private JPanel cp; 
	private RSyntaxTextArea textArea;
	private JButton btnBuild;
	private JButton btnLoad;
	private JButton btnSave;
	private BufferedReader bufferedReader;
		
	private String filePath;
	private IBuilder builder;
	private FileService fileService;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JPanel panel;
	
	public TextEditorUi(RSyntaxTextArea textArea, String filePath, IBuilder builder) {	
		this.fileService = new FileService();
		this.filePath = filePath;
		this.builder = builder;
		this.textArea = textArea;
		this.cp = new JPanel();
		this.fileChooser = new JFileChooser();
		initComponents();
	}
	private void initComponents() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CodeFiles", "java", "cpp");	
		this.fileChooser.addChoosableFileFilter(filter);
		this.fileChooser.setFileFilter(filter);
		
		this.textArea.setCodeFoldingEnabled(true);      

		setContentPane(cp);
		GridBagLayout gbl_cp = new GridBagLayout();
		gbl_cp.columnWidths = new int[]{212, 0};
		gbl_cp.rowHeights = new int[]{58, 0, 0};
		gbl_cp.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_cp.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		cp.setLayout(gbl_cp);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		cp.add(tabbedPane, gbc_tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{278, 0};
		gbl_panel.rowHeights = new int[]{32, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.anchor = GridBagConstraints.NORTHWEST;
		gbc_menuBar.insets = new Insets(0, 0, 5, 0);
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		panel.add(menuBar, gbc_menuBar);
		this.btnLoad = new JButton("Load");
		menuBar.add(btnLoad);
		this.btnBuild = new JButton("Build");
		menuBar.add(btnBuild);
		this.btnSave = new JButton("Save");
		menuBar.add(btnSave);
		
		this.btnSave.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnSaveOnClick();
	      	}
		});
		
		this.btnBuild.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		btnBuildOnClick();
		  	}
		});
		
		this.btnLoad.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnLoadOnClick();
	      	}
		});
		this.sp = new RTextScrollPane(textArea);
		GridBagConstraints gbc_sp = new GridBagConstraints();
		gbc_sp.gridheight = 6;
		gbc_sp.anchor = GridBagConstraints.WEST;
		gbc_sp.gridx = 0;
		gbc_sp.gridy = 1;
		panel.add(sp, gbc_sp);
		this.sp.getTextArea().setWrapStyleWord(false);
		setTitle("Text Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		this.loadFile();
	}
		
	private void btnSaveOnClick() {
		 try{
			 String str = this.textArea.getText();
			 BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
			 writer.write(str);		 
			 writer.close();
		 }catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void btnBuildOnClick() {
		try {
			this.builder.build(this.filePath);
			JOptionPane.showMessageDialog(null, "Arquivo Compilado com Sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void btnLoadOnClick() {
		try {
			int response = this.fileChooser.showOpenDialog(this);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				
				String filePath = this.fileChooser.getSelectedFile().getPath();
				String extension = this.fileService.getExtension(filePath);
				
				fileService.validateExtension(filePath);
				
				if(extension.equals("java")) {
					ILangFactory factory = new JavaFactory();
					JFrame frame = factory.createTextArea(filePath);
					frame.setVisible(true);
				}
				if(extension.equals("cpp")) {
					ILangFactory factory = new CppFactory();
					JFrame frame = factory.createTextArea(filePath);
					frame.setVisible(true);
				}
			}else {
				throw new Exception("NO FILE CHOOSEN!");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void loadFile() {		
		try {		
			this.bufferedReader = new BufferedReader(new FileReader(this.filePath));
	
			String buffer;
			
			while ((buffer = bufferedReader.readLine()) != null) {
				this.textArea.append(buffer + '\n');
			}			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			 try {
				 this.bufferedReader.close(); 
			 } catch (Exception ex) {
				 ex.printStackTrace();
			 }
		}		
	}		
}

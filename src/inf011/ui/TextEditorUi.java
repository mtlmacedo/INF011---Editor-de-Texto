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
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;
import inf011.services.FileService;
import inf011.services.PluginService;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

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
	private JMenuBar menuBar;
	private PluginService pluginService;

	public TextEditorUi(RSyntaxTextArea textArea, String filePath, IBuilder builder,  PluginService pluginService) {	
		this.pluginService = pluginService;
		this.fileService = new FileService();
		this.filePath = filePath;
		this.builder = builder;
		this.textArea = new RSyntaxTextArea();
		this.textArea = textArea;
		this.sp = new RTextScrollPane(textArea);
		this.menuBar = new JMenuBar();
		this.cp = new JPanel();
		this.fileChooser = new JFileChooser();
		this.btnLoad = new JButton("Load");	  
		this.btnBuild = new JButton("Build");		
		this.btnSave = new JButton("Save");
		
		initComponents();
	}
	private void initComponents() {	
		
		this.sp.setColumnHeaderView(menuBar);		
		this.cp.setLayout(new GridLayout(1, 1, 2, 2));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CodeFiles", this.pluginService.getValidExtension());	
		this.fileChooser.addChoosableFileFilter(filter);
		this.fileChooser.setFileFilter(filter);
		
		this.textArea.setCodeFoldingEnabled(true);      
		this.sp.getTextArea().setWrapStyleWord(false);
		this.cp.add(sp, BorderLayout.CENTER);
		menuBar.add(btnSave);
		
		this.btnSave.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnSaveOnClick();
	      	}
		});
		
		this.btnLoad.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnLoadOnClick();
	      	}
		});
	    
		this.btnBuild.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnBuildOnClick();
	      	}
		});
		
		this.menuBar.add(btnLoad);
      
		this.menuBar.add(btnBuild);
		
		setContentPane(cp);
		setTitle(new File(filePath).getName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			 JOptionPane.showMessageDialog(null, "Arquivo Salvo com Sucesso!");
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
				ILangFactory factory = this.pluginService.getFactoryByExtension(extension); 
				RSyntaxTextArea textArea = (RSyntaxTextArea) factory.createTextArea(filePath);
				JFrame frame = new TextEditorUi(textArea, filePath, factory.createBuilder(), this.pluginService);
				frame.setVisible(true);
				
			}else {
				throw new Exception("Nenhum arquivo foi carregado!");
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
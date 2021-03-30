package inf011.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.FileNameMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import inf011.factorys.JavaFactory;
import inf011.interfaces.IBuilder;
import inf011.interfaces.ILangFactory;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextEditorUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private JMenuBar menuBar;
	private RTextScrollPane sp;
	private JPanel cp; 
	private RSyntaxTextArea textArea;
	private JButton btnBuild;
	private JButton btnLoad;
	
	private BufferedReader bufferedReader;
	
	private ILangFactory factory;
	
	private String filePath;
	private IBuilder builder;
	
	public TextEditorUi(RSyntaxTextArea textArea, String filePath, IBuilder builder) {
		
		this.filePath = filePath;
		this.builder = builder;
		this.textArea = textArea;
		this.sp = new RTextScrollPane(textArea);
		this.menuBar = new JMenuBar();
		this.cp = new JPanel();
		this.fileChooser = new JFileChooser();
		this.btnLoad = new JButton("Load");	  
		this.btnBuild = new JButton("Build");
		
		initComponents();
	}

	private void initComponents() {
				
		this.sp.setColumnHeaderView(menuBar);		
		this.cp.setLayout(new GridLayout(1, 1, 2, 2));

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CodeFiles", "java", "cpp");	
		this.fileChooser.addChoosableFileFilter(filter);
			
		this.textArea.setCodeFoldingEnabled(true);      
		this.sp.getTextArea().setWrapStyleWord(false);
		this.cp.add(sp, BorderLayout.CENTER);
        		    
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
		setTitle("Text Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		this.loadFile();
	}
		
	private void btnBuildOnClick() {
		try {
			this.builder.run(this.filePath);
			JOptionPane.showMessageDialog(null, "Arquivo Compilado com Sucesso!");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void btnLoadOnClick() {
		try {
			int response = this.fileChooser.showOpenDialog(this);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				
				ILangFactory factory = new JavaFactory();
				JFrame frame = factory.createTextArea(this.fileChooser.getSelectedFile().getPath());
				frame.setVisible(true);
				
			}else {
				throw new Exception("NO FILE CHOOSEN!");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}finally {
			 try {
				 this.bufferedReader.close(); 
			 } catch (Exception ex) {
				 ex.printStackTrace();
			 }
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

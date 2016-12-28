package widget;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import interfaces.IAction;

public class AddNewColorFrame extends JFrame {
	MJTextField attrFileInput;
	MJTextField styleFileInput;
	MJTextField colorFileInput;
	JButton attrFileSearchBtn;
	JButton styleFileSearchBtn;
	JButton colorFileSearchBtn;
	MJTextField newColorNameInput;
	MJTextField dayColorInput;
	MJTextField nightColorInput;
	JLabel dayColorSample;
	JLabel nightColorSample;
	JButton generateBtn;
	JLabel generateResult;

	File attrsFile, stylesFile, colorsFile, recentFile;
	private IAction actionDelegate;

	public AddNewColorFrame(IAction delegate) {
		super();
		setTitle("添加颜色");
		setLayout(null);
		addFilePathView();
		addEditColorView();
		addGenerateView();
		addSearchFileListener();
		addInputColorListener();
		addGenerateListener();
		actionDelegate = delegate;
	}

	private void addFilePathView() {
		int y1 = 20, y2 = 50, y3 = 80;
		JLabel attrFileLabel = new JLabel("attrs.xml文件路径:");
		JLabel styleFileLable = new JLabel("styles.xml文件路径:");
		JLabel colorFileLable = new JLabel("colors.xml文件路径:");
		attrFileLabel.setBounds(20, y1, 130, 25);
		styleFileLable.setBounds(20, y2, 130, 25);
		colorFileLable.setBounds(20, y3, 130, 25);
		add(attrFileLabel);
		add(styleFileLable);
		add(colorFileLable);

		attrFileInput = new MJTextField();
		styleFileInput = new MJTextField();
		colorFileInput = new MJTextField();
		attrFileInput.setBounds(160, y1, 500, 25);
		styleFileInput.setBounds(160, y2, 500, 25);
		colorFileInput.setBounds(160, y3, 500, 25);
		attrFileInput.setEditable(true);
		styleFileInput.setEditable(true);
		colorFileInput.setEditable(true);
		add(attrFileInput);
		add(styleFileInput);
		add(colorFileInput);

		attrFileSearchBtn = new JButton("...");
		styleFileSearchBtn = new JButton("...");
		colorFileSearchBtn = new JButton("...");
		attrFileSearchBtn.setBounds(660, y1, 40, 25);
		styleFileSearchBtn.setBounds(660, y2, 40, 25);
		colorFileSearchBtn.setBounds(660, y3, 40, 25);
		add(attrFileSearchBtn);
		add(styleFileSearchBtn);
		add(colorFileSearchBtn);
	}

	private void addEditColorView() {
		int y1 = 130, y2 = 160, y3 = 190;
		JLabel newColorNameLable = new JLabel("新加颜色变量名：");
		JLabel dayColorLable = new JLabel("常规模式颜色：");
		JLabel nightColorLable = new JLabel("黑夜模式颜色：");
		newColorNameLable.setBounds(20, y1, 110, 25);
		dayColorLable.setBounds(20, y2, 100, 25);
		nightColorLable.setBounds(20, y3, 100, 25);
		add(newColorNameLable);
		add(dayColorLable);
		add(nightColorLable);

		newColorNameInput = new MJTextField();
		dayColorInput = new MJTextField(6);
		nightColorInput = new MJTextField(6);
		newColorNameInput.setBounds(120, y1, 540, 25);
		dayColorInput.setBounds(120, y2, 80, 25);
		nightColorInput.setBounds(120, y3, 80, 25);
		add(newColorNameInput);
		add(dayColorInput);
		add(nightColorInput);

		dayColorSample = new JLabel();
		nightColorSample = new JLabel();
		dayColorSample.setBounds(210, y2 + 2, 20, 20);
		nightColorSample.setBounds(210, y3 + 2, 20, 20);
		dayColorSample.setBackground(new Color(255, 255, 255));
		nightColorSample.setBackground(new Color(0, 0, 0));
		dayColorSample.setOpaque(true);
		nightColorSample.setOpaque(true);
		add(dayColorSample);
		add(nightColorSample);
	}

	private void addGenerateView() {
		int y1 = 250, y2 = 290;
		generateBtn = new JButton("添  加");
		generateBtn.setBounds(20, y1, 680, 25);
		add(generateBtn);

		generateResult = new JLabel("这是处理结果");
		generateResult.setBounds(20, y2, 680, 100);
		add(generateResult);
	}

	private void addSearchFileListener() {
		attrFileSearchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				if (recentFile != null) {
					jfc.setCurrentDirectory(recentFile);
				}
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择attrs.xml文件");
				File file = jfc.getSelectedFile();
				recentFile = file;
				if (file == null) {
					return;
				}
				if (file.isDirectory() || !file.getName().endsWith("attrs.xml")) {
					generateResult.setText("请选择正确的文件!");
				} else {
					attrsFile = file;
					attrFileInput.setText(file.getAbsolutePath());
				}
			}
		});

		styleFileSearchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				if (recentFile != null) {
					jfc.setCurrentDirectory(recentFile);
				}
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择styles.xml文件");
				File file = jfc.getSelectedFile();
				recentFile = file;
				if (file.isDirectory() || !file.getName().endsWith("styles.xml")) {
					generateResult.setText("请选择正确的文件!");
				} else {
					stylesFile = file;
					styleFileInput.setText(file.getAbsolutePath());
				}
			}
		});

		colorFileSearchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				if (recentFile != null) {
					jfc.setCurrentDirectory(recentFile);
				}
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择colors.xml文件");
				File file = jfc.getSelectedFile();
				recentFile = file;
				if (file.isDirectory() || !file.getName().endsWith("colors.xml")) {
					generateResult.setText("请选择正确的文件!");
				} else {
					colorsFile = file;
					colorFileInput.setText(file.getAbsolutePath());
				}
			}
		});
	}

	private void addInputColorListener() {
		Document dayDoc = dayColorInput.getDocument();
		dayDoc.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeDayColor();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeDayColor();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeDayColor();
			}
		});

		Document nightDoc = nightColorInput.getDocument();
		nightDoc.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeNightColor();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeNightColor();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeNightColor();
			}
		});
	}

	private void changeDayColor() {
		try {
			String dayColorStr = dayColorInput.getText();
			if ("".equals(dayColorStr)) {
				dayColorSample.setBackground(new Color(255, 255, 255));
				generateResult.setText("请输入正确的常规模式颜色");
				return;
			}
			if (!dayColorStr.startsWith("#")) {
				dayColorStr = "#" + dayColorStr;
			}
			Color color = Color.decode(dayColorStr);
			dayColorSample.setBackground(color);
		} catch (Exception exception) {
			generateResult.setText("请输入正确的常规模式颜色");
		}
	}

	private void changeNightColor() {
		try {
			String nightColor = nightColorInput.getText();
			if ("".equals(nightColor)) {
//				dayColorSample.setBackground(new Color(255, 255, 255));
				nightColorSample.setBackground(new Color(0, 0, 0));
				generateResult.setText("请输入正确的黑夜模式颜色");
				return;
			}
			if (!nightColor.startsWith("#")) {
				nightColor = "#" + nightColor;
			}
			Color color = Color.decode(nightColor);
			nightColorSample.setBackground(color);
		} catch (Exception exception) {
			generateResult.setText("请输入正确的黑夜模式颜色");
		}
	}

	private void addGenerateListener() {
		generateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (actionDelegate != null) {
					actionDelegate.onAdded();
				}
			}
		});
	}
	
	private boolean checkColorName(){
		
		return false;
	}
	
	public String getDayColorName(){
		String dayColorName = newColorNameInput.getText();
		return dayColorName;
	}
	
	public String getNightColorValue(){
		String nightColorValue = nightColorInput.getText();
		return nightColorValue;
	}
	
	public String getDayColorValue(){
		String dayColorValue = dayColorInput.getText();
		return dayColorValue;
	}
	
	public String getColorFilePath(){
		String colorFilePath = colorFileInput.getText();
		return colorFilePath;
	}
	
	public String getAttrFilePath(){
		String attrFilePath = attrFileInput.getText();
		return attrFilePath;
	}
	
	public String getStyleFilePath(){
		String styleFilePath = styleFileInput.getText();
		return styleFilePath;
	}
}

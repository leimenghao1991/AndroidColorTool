package widget;
import java.awt.Button;
import java.awt.Color;
import javax.swing.*;

import Util.InsertHelper;
import interfaces.IAction;

public class MainPage implements IAction{
	private InsertHelper insertHelper;
	private AddNewColorFrame frame;
	
	public MainPage(){
		insertHelper = InsertHelper.getInstance();
	}
	
	public void createAndShowGUI(){
		//确保一个漂亮外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		frame = new AddNewColorFrame(MainPage.this);
//		//创建及设置窗口
//		JFrame frame = new JFrame("hello word");
		frame.setSize(720, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(null);
//		
//		addFilePathView(frame);
//		addEditColorView(frame);
//		addGenerateView(frame);
		frame.setTitle("添加颜色");
		frame.setSize(720, 1000);
		//显示窗口
//		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void onAdded() {
		// TODO Auto-generated method stub
		String colorFilePath = frame.getColorFilePath();
		String attrFilePath = frame.getAttrFilePath();
		String styleFilePath = frame.getStyleFilePath();
		String colorName = frame.getDayColorName();
		String dayColorValue = frame.getDayColorValue();
		String nightColorValue = frame.getNightColorValue();
		insertHelper.insert(colorFilePath, attrFilePath, styleFilePath, colorName, dayColorValue, nightColorValue);
	}
}

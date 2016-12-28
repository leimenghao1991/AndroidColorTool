import widget.MainPage;

public class Main {
	
	public static void main(String[] args)
	{
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainPage mainPage = new MainPage();
				mainPage.createAndShowGUI();
			}
		});
	}
}

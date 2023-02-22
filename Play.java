import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Play
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					try{
					Configuration.loadParameters(args[0]);
					}
					catch (Exception e){}
					JFrame frame = new JFrame();
					JLabel statusbar = new JLabel("select a cell");
					frame.add(statusbar, BorderLayout.SOUTH);
					frame.add(new Board(Configuration.ROWS, Configuration.COLS, Configuration.MINES, statusbar));
					frame.setResizable(false);
					frame.pack();
					frame.setTitle("Project2 is so hard");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
	}
}

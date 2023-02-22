import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Dimension;

public class Board extends JPanel
{
	private int rows;
	private int columns;
	private int mines;
	private Minefield m;
	private JLabel statusbar;
	private int flag;
	private int compare;
	private String gameStatus;
	public Board(int height, int width, int mines, JLabel statusbar)
	{
		this.rows= height;
		this.columns = width;
		this.mines = mines;
		this.m = new Minefield(height, width, mines);
		//m.mineLaying(mines);
		m.addInfoCells();
		this.statusbar = statusbar;
		this.flag = mines;
		this.compare = mines;
		//m.print();
		setPreferredSize(new Dimension(Configuration.BOARD_WIDTH, Configuration.BOARD_HEIGHT));
		addMouseListener(new MouseReader(this));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		m.draw(g);
	}

	public Minefield getMinefield(){
		return m;
	}

	public boolean isGameOver(){
		if(this.gameStatus == "L"){
			this.setStatusbar("Game over - You lost!");
			return true;
		}
		else if(this.gameStatus == "W"){
			this.setStatusbar("Game over - You won!");
			return true;
		}
		return false;


	}

	public void setStatusbar(String text){
		this.statusbar.setText(text);
	}

	public String getStatusbar(){
		return this.statusbar.getText();
	}

	public boolean removeMine(){
		if(flag >= 0){
			this.setStatusbar(Integer.toString(flag) + " mines remaining");
			return true;
		}
		else{
			this.setStatusbar("Invalid action");
			return false;
		}
	}

	public boolean addMine(){
		if(flag + 1 > compare){
			this.setStatusbar("Invalid action");
			return false;
		}
		else{
			flag += 1;
			this.setStatusbar(Integer.toString(flag) + " mines remaining");
			return true;
		}
	}


	public void mouseClickOnLocation(int x, int y, String button)
	{
		if(m.getCellByScreenCoordinates(x,y) instanceof MineCell){
			MineCell mine = (MineCell) m.getCellByScreenCoordinates(x,y);
			if(button.equals("left")){
				if(mine.getStatus().equals(Configuration.STATUS_COVERED)){
					mine.setStatus(Configuration.STATUS_OPENED);
				}
				this.gameStatus = "L";
				
			}
			else if(button.equals("right")){
				if(mine.getStatus().equals(Configuration.STATUS_COVERED)){
					mines -= 1;
					flag -= 1;
					if(this.removeMine() == true){
						mine.setStatus(Configuration.STATUS_MARKED);
					}
					else{
						flag += 1;
					}
					
				}
				else if(mine.getStatus().equals(Configuration.STATUS_MARKED)){
					mines += 1;
					if(this.addMine() == true){
						mine.setStatus(Configuration.STATUS_COVERED);
					}
					else{
						flag -= 1;
						mines += 1;
					}
					
				}
			}
		}
		else{
			InfoCell info = (InfoCell) m.getCellByScreenCoordinates(x,y);
			if(button.equals("left")){
				if(info.getStatus().equals(Configuration.STATUS_COVERED)){
					m.openCells(info);
					this.removeMine();
				}
			}
			else if(button.equals("right")){
				if(info.getStatus().equals(Configuration.STATUS_COVERED)){
					flag -= 1;
					if(this.removeMine() == true){
						info.setStatus(Configuration.STATUS_MARKED);
					}
					else{
						flag += 1;
					}
					
				}
				else if(info.getStatus().equals(Configuration.STATUS_MARKED)){
					if(this.addMine() == true){
						info.setStatus(Configuration.STATUS_COVERED);
					}
				}
			}
		}
		int countTotalInfoCell = this.rows * this.columns - this.compare;
		int countOpenedInfoCell = 0;
		for(int i = 0; i < this.rows; ++i){
			for(int j = 0; j < this.columns; ++j){
				if(m.getCellByRowCol(i, j) instanceof InfoCell){
					InfoCell infocell = (InfoCell) m.getCellByRowCol(i,j);
					if(infocell.getStatus() == Configuration.STATUS_OPENED){
						countOpenedInfoCell += 1;
					}
				}
			}
		}
		if((countTotalInfoCell == countOpenedInfoCell) && this.isGameOver() == false){
			this.gameStatus = "W";
		}
		System.out.println(countTotalInfoCell + "," + countOpenedInfoCell );
		this.isGameOver();
		if(this.isGameOver() == true){
			m.revealIncorrectMarkedCells();
		}
		this.getStatusbar();
		repaint();
	}
}

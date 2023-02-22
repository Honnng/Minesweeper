import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class MineCell{
    private int row;
    private int column;
    private String s = Configuration.STATUS_COVERED;
    private ImageIcon covered = new ImageIcon("img/covered_cell.png");
    private ImageIcon marked = new ImageIcon("img/marked_cell.png");
    private ImageIcon open = new ImageIcon("img/mine_cell.png");
    public MineCell(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void draw(Graphics g){
        g.drawImage(this.getImage(),this.getHorizontalPosition(), this.getVerticalPosition(), null);
    }

    public int getHorizontalPosition(){
        return column * Configuration.CELL_SIZE;
    }

    public int getVerticalPosition(){
        return row* Configuration.CELL_SIZE;
    }

    public String getStatus(){
        return this.s;
    }

    public void setStatus(String status){
        if (status.equals(Configuration.STATUS_COVERED)){
            this.s = Configuration.STATUS_COVERED;
        }
        else if(status.equals(Configuration.STATUS_OPENED)){
            this.s = Configuration.STATUS_OPENED;
        }
        else if(status.equals(Configuration.STATUS_MARKED)){
            this.s = Configuration.STATUS_MARKED;
        }
    }

    public Image getImage(){
        if(this.getStatus() == Configuration.STATUS_COVERED){
            return covered.getImage();
        }
        else if(this.getStatus() == Configuration.STATUS_OPENED){
            return open.getImage();
        }
        else{
            return marked.getImage();
        }
    }
}

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class InfoCell{
    private int row;
    private int column;
    private int numOfAdjacentMines;
    private String s = Configuration.STATUS_COVERED;
    private ImageIcon covered = new ImageIcon("img/covered_cell.png");
    private ImageIcon info_0 = new ImageIcon("img/info_0.png");
    private ImageIcon info_1 = new ImageIcon("img/info_1.png");
    private ImageIcon info_2 = new ImageIcon("img/info_2.png");
    private ImageIcon info_3 = new ImageIcon("img/info_3.png");
    private ImageIcon info_4 = new ImageIcon("img/info_4.png");
    private ImageIcon info_5 = new ImageIcon("img/info_5.png");
    private ImageIcon info_6 = new ImageIcon("img/info_6.png");
    private ImageIcon info_7 = new ImageIcon("img/info_7.png");
    private ImageIcon info_8 = new ImageIcon("img/info_8.png");
    private ImageIcon marked = new ImageIcon("img/marked_cell.png");
    private ImageIcon wrong_marked = new ImageIcon("img/wrong_mark.png");
    private Image img;
    

    public InfoCell(int row, int column, int numOfAdjacentMines){
        this.row = row;
        this.column = column;
        this.numOfAdjacentMines = numOfAdjacentMines;
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
        else if(status.equals(Configuration.STATUS_WRONGLY_MARKED)){
            this.s = Configuration.STATUS_WRONGLY_MARKED;
        }
    }

    public int getNumOfAdjacentMines(){
        return this.numOfAdjacentMines;
    }

    public Image getImage(){
        if(this.getStatus() == Configuration.STATUS_COVERED){
            this.img = covered.getImage();
        }
        else if(this.getStatus() == Configuration.STATUS_MARKED){
            this.img = marked.getImage();
        }
        else if(this.getStatus() == Configuration.STATUS_WRONGLY_MARKED){
            this.img = wrong_marked.getImage();
        }
        else{
            if(this.getNumOfAdjacentMines() == 0){
                this.img = info_0.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 1){
                this.img = info_1.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 2){
                this.img = info_2.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 3){
                this.img = info_3.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 4){
                this.img = info_4.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 5){
                this.img = info_5.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 6){
                this.img = info_6.getImage();
            }
            else if(this.getNumOfAdjacentMines() == 7){
                this.img = info_7.getImage();
            }
            else{
                this.img = info_8.getImage();
            }
        }
        return this.img;
    }
}

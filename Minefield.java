import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Minefield{
    private Object[][] field;
    private int numMines;
    private int numRows;
    private int numColumns;

    public Minefield(){
        new Minefield(10,10,10);
    }

    public Minefield(int numRows, int numColumns, int numMines){
        field = new Object[numRows][numColumns];
        this.numMines = numMines;
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.mineLaying(this.numMines);
        this.addInfoCells();
    }

    public void mineLaying(int numOfMines){
        Random r = new Random();
        int mines = numOfMines;
        /**for(int i = numOfMines; i > 0; --i){
            int mineRow = r.nextInt(this.numRows);
            int mineColumn = r.nextInt(this.numColumns);
            MineCell m = new MineCell(mineRow, mineColumn);
            //m.setStatus(Configuration.STATUS_COVERED);
            this.setMineCell(mineRow, mineColumn, m);
            //field[mineRow][mineColumn] = m;
        }**/
        while(mines != 0){
            int mineRow = r.nextInt(this.numRows);
            int mineColumn = r.nextInt(this.numColumns);
            if(field[mineRow][mineColumn] == null){
                MineCell m = new MineCell(mineRow, mineColumn);
                this.setMineCell(mineRow, mineColumn, m);
                mines -= 1;
            }
        }
    }

    public void addInfoCells(){
        for(int i = 0; i < this.numRows; ++i){
            for(int j = 0; j < this.numColumns; ++j){
                int a = 0;
                if(field[i][j] == null){
                    if((i - 1 >= 0) && (j - 1 >= 0)){
                        Object b = field[i-1][j-1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if(i - 1 >= 0){
                        Object b = field[i-1][j];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if((i - 1 >= 0) && (j + 1 < numColumns)){
                        Object b = field[i-1][j+1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if(j - 1 >= 0){
                        Object b = field[i][j-1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if(j + 1 < numColumns){
                        Object b = field[i][j+1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if((i + 1 < numRows) && (j - 1 >= 0)){
                        Object b = field[i+1][j-1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if(i + 1 < numRows){
                        Object b = field[i+1][j];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    if((i + 1 < numRows) && (j + 1 < numColumns)){
                        Object b = field[i+1][j+1];
                        if(b instanceof MineCell){
                            a += 1;
                        }
                    }
                    InfoCell info = new InfoCell(i, j, a);
                    //info.setStatus(Configuration.STATUS_COVERED);
                    this.setInfoCell(i, j, info);
                    //field[i][j] = info;
                }
            }
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < numRows; ++i){
            for(int j = 0; j < numColumns; ++j){
                if(field[i][j] instanceof MineCell){
                    MineCell m = (MineCell) field[i][j];
                    m.draw(g);
                }
                else if(field[i][j] instanceof InfoCell){
                    InfoCell info = (InfoCell) field[i][j];
                    info.draw(g);
                }
            }
        }
    }

    public Object getCellByScreenCoordinates(int x, int y){
        int a = x / Configuration.CELL_SIZE;
        int b = y / Configuration.CELL_SIZE;
        return field[b][a];
    }

    public Object getCellByRowCol(int row, int col){
        return field[row][col];
    }

    public void setMineCell(int row, int col, MineCell cell){
        field[row][col] = cell;
    }

    public void setInfoCell(int row, int col, InfoCell cell){
        field[row][col] = cell;
    }

    public int countCellsWithStatus(String status){
        int count = 0;
        for(int i = 0; i < numRows; ++i){
            for(int j = 0; j < numColumns; ++j){
                if(field[i][j] instanceof MineCell){
                    MineCell m = (MineCell) field[i][j];
                    if(m.getStatus().equals(status)){
                        count += 1;
                    }
                }
                else if(field[i][j] instanceof InfoCell){
                    InfoCell info = (InfoCell) field[i][j];
                    if(info.getStatus().equals(status)){
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    public void openCells(Object cell){
        InfoCell info = (InfoCell) cell;
        info.setStatus(Configuration.STATUS_OPENED);
        if(info.getNumOfAdjacentMines() == 0){
            int b = (info.getHorizontalPosition() / Configuration.CELL_SIZE);
			int a = (info.getVerticalPosition() / Configuration.CELL_SIZE);
            if(a - 1 >= 0 && b - 1 >= 0){
                InfoCell info1 = (InfoCell) field[a - 1][b - 1];
                if(info1.getStatus().equals(Configuration.STATUS_COVERED)){
                    info1.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(a - 1 >= 0){
                InfoCell info2 = (InfoCell) field[a - 1][b];
                if(info2.getStatus().equals(Configuration.STATUS_COVERED)){
                    info2.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(a - 1 >= 0 && b + 1 < this.numColumns){
                InfoCell info3 = (InfoCell) field[a - 1][b + 1];
                if(info3.getStatus().equals(Configuration.STATUS_COVERED)){
                info3.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(b - 1 >= 0){
                InfoCell info4 = (InfoCell) field[a][b - 1];
                if(info4.getStatus().equals(Configuration.STATUS_COVERED)){
                info4.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(b + 1 < this.numColumns){
                InfoCell info5 = (InfoCell) field[a][b + 1];
                if(info5.getStatus().equals(Configuration.STATUS_COVERED)){
                info5.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(a + 1 < this.numRows && b - 1 >= 0){
                InfoCell info6 = (InfoCell) field[a + 1][b - 1];
                if(info6.getStatus().equals(Configuration.STATUS_COVERED)){
                info6.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(a + 1 < this.numRows){
                InfoCell info7 = (InfoCell) field[a + 1][b];
                if(info7.getStatus().equals(Configuration.STATUS_COVERED)){
                info7.setStatus(Configuration.STATUS_OPENED);
                }
            }
            if(a + 1 < this.numRows && b + 1 < this.numColumns){
                InfoCell info8 = (InfoCell) field[a + 1][b + 1];
                if(info8.getStatus().equals(Configuration.STATUS_COVERED)){
                info8.setStatus(Configuration.STATUS_OPENED);
                }
            }
        }
        
    }

    public void revealIncorrectMarkedCells(){
        for(int i = 0; i < this.numRows; ++i){
            for(int j = 0; j < this.numColumns; ++j){
                if(field[i][j] instanceof InfoCell){
                    InfoCell info = (InfoCell) field[i][j];
                    //InfoCell info = (InfoCell) this.getCellByRowCol(i + 1, j + 1);
                    if(info.getStatus().equals(Configuration.STATUS_MARKED)){
                        info.setStatus(Configuration.STATUS_WRONGLY_MARKED);
                    }
                }
            }
        }
    }
 /** 
    public void print(){
        for(int i = 0; i < numRows; ++i){
            for(int j = 0; j < numColumns; ++j){
                if(field[i][j] instanceof MineCell){
                    System.out.print("b");
                }
                else{
                    InfoCell info = (InfoCell) field[i][j];
                    System.out.print(info.getNumOfAdjacentMines());
                }
            }
            System.out.println();
        }
    }*/
}

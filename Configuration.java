import java.io.File;
import java.util.Scanner;
import javax.swing.JLabel;

public class Configuration{
    public static int ROWS;
    public static int COLS;
    public static int CELL_SIZE;
    public static int MINES;
    public static int BOARD_WIDTH;
    public static int BOARD_HEIGHT;
    public static String STATUS_COVERED;
    public static String STATUS_OPENED;
    public static String STATUS_MARKED;
    public static String STATUS_WRONGLY_MARKED;

    public static void loadParameters(String filename){
        try{
        Scanner sc = new Scanner(new File(filename));
        while(sc.hasNext()){
            String a = sc.next();
            switch(a){
                case "ROWS":
                    ROWS = Integer.parseInt(sc.next());
                    break;
                case "COLS":
                    COLS = Integer.parseInt(sc.next());
                    break;
                case "CELL_SIZE":
                    CELL_SIZE = Integer.parseInt(sc.next());
                    break;
                case "MINES":
                    MINES = Integer.parseInt(sc.next());
                    break;
                case "STATUS_COVERED":
                    STATUS_COVERED = sc.next();
                    break;
                case "STATUS_OPENED":
                    STATUS_OPENED = sc.next();
                    break;
                case "STATUS_MARKED":
                    STATUS_MARKED = sc.next();
                    break;
                case "STATUS_WRONGLY_MARKED":
                    STATUS_WRONGLY_MARKED = sc.next();
                    break;
            }
        }
        BOARD_WIDTH = COLS * CELL_SIZE + 1;
        BOARD_HEIGHT = ROWS * CELL_SIZE + 1;
        }
        catch(Exception e){}
    }
/** 
    public static void main(String[] args) {
        try{
            Configuration.loadParameters("config1.test");
            }
            catch (Exception e){}
            JLabel statusbar = new JLabel("select a cell");
            //Board b = new Board(10, 10, 10, statusbar);
            Minefield m = new Minefield(10,10,10);
            /**System.out.println(Configuration.STATUS_WRONGLY_MARKED);
            System.out.println(Configuration.COLS );
            System.out.println(Configuration.CELL_SIZE);
            System.out.println(Configuration.ROWS);
            System.out.println(Configuration.STATUS_COVERED );
            System.out.println(Configuration.STATUS_OPENED);
            System.out.println(Configuration.MINES );
            System.out.println(Configuration.STATUS_MARKED);
            int infocount = 0;
            int minecount = 0;
            for(int i = 0; i < 10; ++i){
                for(int j = 0; j < 10; ++j){
                    if(m.getCellByRowCol(i, j) instanceof MineCell){
                        minecount += 1;
                    }
                    if(m.getCellByRowCol(i, j) instanceof InfoCell){
                        infocount += 1;
                    }
                }
            }
            System.out.println(minecount + "," + infocount);

    }*/
    

}

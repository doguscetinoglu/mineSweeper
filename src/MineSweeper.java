import java.util.*; // Random Number

public class MineSweeper {

    // Değerlendirme Formu #1  = > Değişkenler ve Fonksiyonlar
    // Evaluation Form #1 => Variables and Functions
    String[][] mineMap;
    String[][] playerMap;
    int row;
    int column;
    int area;
    int checkCounter = 0;

    public MineSweeper(int row, int column) {
        this.mineMap = new String[row][column];
        this.playerMap = new String[row][column];
        this.row = row;
        this.column = column;
        this.area = row * column;

    }
    // Harita Alanı
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();


    // Harita oluşturulan Alan
    // Map created Area

    public void map(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ("*".equals(map[i][j])) {
                    System.out.print(map[i][j] + " ");
                } else {
                    map[i][j] = "-";
                    System.out.print(map[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    // Değerlendirme Formu # 6 / 11  Oyunu Başlatan Method Alanı ve Oyun Alanı Güncellemesi
    // The Evaluation Form # 6/11  Game Initiation Method Area and Game Field Update
    public void run(){
        int count = 0;
        mineAdd();
        map(mineMap);
        System.out.println("--------------");
        map(playerMap);

        // Değerlendirme Formu # 9 Oyuncu Konumu Seçim Alanı (Satır)
        // The Evaluation Form #9: Player Position Selection Area (Row)

        while (true){
            Scanner scanRow = new Scanner(System.in);
            System.out.println("Please Enter row");
            int userRow = scanRow.nextInt();

            if (userRow < 0 || userRow > row) {
                System.out.println("Invalid row. Enter a row between 1 and " + row);
                continue;
            }

            // Değerlendirme Formu # 9 Oyuncu Konumu Seçim Alanı (Sütun)
           // The Evaluation Form #9: Player Position Selection Area (Colmun)
            Scanner scanCol = new Scanner(System.in);
            System.out.println("Please Enter Column");
            int userCol = scanCol.nextInt();



            if (userCol < 0 || userCol > column) {
                System.out.println("Invalid column. Enter a column between 1 and " + column);
                continue;
            }

            // Değerlendirme Formu # 10 Oyuncu Seçimleri Oyun Alanı İçerisindemi Kontrol Alanı
            // The Evaluation Form # 10 Player Selections Verification Area within the Game Field

            if (!playerMap[userRow][userCol].equals("-")){
                System.out.println("Invalid coordinate, please try again");
                continue;
            }
            checkMine(userRow,userCol);

            // Değerlendirme Formu #6,12,13,14,15 Oyuncunun Oyun Durumunu Konrol Etme Alanı
            // The Evaluation Form #6,12,13,14,15 Player's Game State Checking Area

            if (mineMap[userRow][userCol].equals("*")){
                System.out.println("Game over!");
                break;


            } else {
                System.out.println("Good choice");
                mineInfo(userRow,userCol);
                this.checkCounter = 0;
                count++;
                if (count == area - (area * 25 / 100)){
                    System.out.println("You win!");
                    break;
                }
            }




        }


    }

    // Değerlendirme Formu # 8  Mayın Oluşturma & Kontrol Alanı
    // The Evaluation Form #8: Mine Generation & Control Area

    public void mineAdd() {
        int rowMine;
        int columnMine;
        int counter = 0;
        while (counter != area / 4) {
            rowMine = rand.nextInt(row);
            columnMine = rand.nextInt(column);

            if (!"*".equals(mineMap[rowMine][columnMine])) {
                mineMap[rowMine][columnMine] = "*";
                counter++;
            }
        }
    }

    public void checkMine(int checkRow, int checkCol) {

        if (mineMap[checkRow][checkCol].equals("-")){


            // Top right
            if ((checkRow > 0) && (checkCol < (column - 1)) && (mineMap[checkRow - 1][checkCol + 1]).equals("*")) {
                checkCounter++;
            }

            // Top left
            if ((checkRow > 0) && (checkCol > 0) && (mineMap[checkRow - 1][checkCol - 1]).equals("*")) {
                checkCounter++;
            }

            // Bottom right
            if ((checkRow < (row - 1)) && (checkCol < (column - 1)) && (mineMap[checkRow + 1][checkCol + 1]).equals("*")) {
                checkCounter++;
            }

            // Bottom left
            if ((checkRow < (row - 1)) && (checkCol > 0) && (mineMap[checkRow + 1][checkCol - 1]).equals("*")) {
                checkCounter++;
            }

            // Top
            if ((checkRow > 0) && (mineMap[checkRow - 1][checkCol]).equals("*")) {
                checkCounter++;
            }

            // Bottom
            if ((checkRow < (row - 1)) && (mineMap[checkRow + 1][checkCol]).equals("*")) {
                checkCounter++;
            }

            // Left
            if ((checkCol > 0) && (mineMap[checkRow][checkCol - 1]).equals("*")) {
                checkCounter++;
            }

            // Right
            if ((checkCol < (column - 1)) && (mineMap[checkRow][checkCol + 1]).equals("*")) {
                checkCounter++;
            }

            playerMap[checkRow][checkCol] = String.valueOf(checkCounter);
        }

    }

      // Oyuncuya Mayınların Bilgisini Söyleyen Alan
     // Field Telling the Player the Information of Mines

    public void mineInfo(int row, int col) {
        for (int i = 0; i < playerMap.length; i++) {
            for (int j = 0; j < playerMap[i].length; j++) {
                if (playerMap[i][j].equals(playerMap[row][col])) {
                    playerMap[row][col] = String.valueOf(checkCounter);
                    System.out.print(playerMap[row][col] + " ");
                    continue;
                }
                System.out.print(playerMap[i][j] + " ");
            }
            System.out.println();
        }
    }

}

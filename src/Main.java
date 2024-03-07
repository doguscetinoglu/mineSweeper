import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int row, col;
        Scanner scan = new Scanner(System.in);
    // Değerlendirme Formu # 7 Oyuncudan Dizi Boyutu (Matris) Alma Alanı
    // The Evaluation Form #7: Player's Array Size (Matrix) Acquisition Area

        System.out.println("Welcome to the Minesweeper Game!");
        System.out.println("--------------------------------");
        while (true){
            System.out.print("Please Enter the Number of Rows: ");
            row = scan.nextInt();
            System.out.print("Please Enter the Number of Columns: ");
            col = scan.nextInt();
            if (row < 2 || col < 2){
                System.out.println("Please enter a value greater than 2!");
            } else {
                break;
            }
        }

        MineSweeper play = new MineSweeper(row,col);
        play.run();
    }
}

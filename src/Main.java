import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe2D game = new TicTacToe2D();
        game.displayGrid();
        while(!game.getGameOver()){
            System.out.println("Player turn :" + game.getCurrentPlayer());
            System.out.print("Enter Row:");
            int xVal = scanner.nextInt();
            System.out.print("Enter Column:");
            int yVal = scanner.nextInt();
            game.play(xVal,yVal);
            game.displayGrid();
            System.out.println("-----");


        }


    }

}

import java.util.Arrays;

public class TicTacToe2D {

    final int COLUMNS = 3;
    final int ROWS = 3;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public enum Value {
        X,
        O,
        E,
    }

    public enum Player {
        PLAYER_1,
        PLAYER_2,
    }

    private Value[][] grid;


    private boolean gameOver = false;

    private Player winner;
    private Player currentPlayer;


    public TicTacToe2D() {
        this.grid = createEmptyGrid();
        this.setGameOver(false);
        this.setCurrentPlayer(Player.PLAYER_1);
    }

    public Player getWinner() {
        if(this.winner == null){
            System.out.println("Winner has not been defined.");
            return this.winner;
        }
        return this.winner;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private Value[][] createEmptyGrid() {
        Value[][] grid = new Value[this.ROWS][this.COLUMNS];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Value.E;
            }
        }

        return grid;
    }


    // Method to display the grid
    public void displayGrid() {
        for (Value[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void play(int x, int y) {
        boolean isValidated = validateMove(x, y);
        if (!isValidated) {
            System.out.println("not validated");
            return;
        }

        Player player = getCurrentPlayer();

        if (player == Player.PLAYER_1) {
            this.grid[x - 1][y - 1] = Value.O;
        } else if (player == Player.PLAYER_2) {
            this.grid[x - 1][y - 1] = Value.X;
        }
        System.out.println("move to "+ x + " and " + y + " successfully");

        if(hasWin()){
        //    this.gameOver = true;
            this.setGameOver(true);
            this.setWinner(player);
        //    this.winner = player;
            System.out.println("Winner winner chicken dinner." + this.getWinner()+ "won");
        }

        if (isDraw()){
            this.setGameOver(true);
            System.out.println("Game ended draw.");
        }

        if (!this.gameOver){
            Player nextPlayer = getNextPlayer();
            this.setCurrentPlayer(nextPlayer);
        }


    }

    public Player getNextPlayer()  {
        Player nextPlayer;
        if (this.currentPlayer == Player.PLAYER_1){
            nextPlayer = Player.PLAYER_2;
        }
        else {
            nextPlayer = Player.PLAYER_1;
        }
        return nextPlayer;
    }

    public boolean validateMove(int x, int y) {

        // only 3-2-1 allowed
        if (!((this.ROWS >= x && x >= 1) && (this.COLUMNS >= y && y >= 1))) {
            System.out.println("range not validated " + x + ", " + y );
            return false;
        }

        // if the place we want to put is already taken.
        if (this.grid[x - 1][y - 1] != Value.E) {
            System.out.println("Place (" + x + ", " + y + ") already taken.");
            return false;
        }

        return true;
    }

    public boolean isDraw(){
        int numEmpties = 0;
        for (int i = 0; i <this.ROWS; i++) {
            for (int j = 0; j <this.COLUMNS; j++) {
                if(this.grid[i][j] == Value.E){
                    numEmpties++;
                }
            }
        }
        if (numEmpties == 0){
            return true;
        }
         return false;
    }
    public boolean hasWin(){
        //checking row
        for (int row = 0; row < this.ROWS; row++) {
            if (this.grid[row][0] == this.grid[row][1] && this.grid[row][1] == this.grid[row][2] && this.grid[row][0] != Value.E) {
                return true;
            }
        }

        //checking columns
        for (int col = 0; col < this.COLUMNS; col++) {
            if (this.grid[col][0] == this.grid[col][1] && this.grid[col][1] == this.grid[col][2] && this.grid[col][0] != Value.E) {
                return true;
            }
        }

        //checking diagonal
        boolean diagonalWin = checkDiagonals();
        if (diagonalWin) {
            return true;
        }
        return false;
    }
    private boolean checkDiagonals() {
        // Check main diagonal (top-left to bottom-right)
        boolean mainDiagonalWin = true;
        for (int i = 0; i < this.ROWS; i++) {
            if (this.grid[i][i] != this.grid[0][0] || this.grid[i][i] == Value.E) {
                mainDiagonalWin = false;
                break;
            }
        }

        // Check anti-diagonal (top-right to bottom-left)
        boolean antiDiagonalWin = true;
        for (int i = 0; i < this.ROWS; i++) {
            if (this.grid[i][this.ROWS - 1 - i] != this.grid[0][this.ROWS - 1] || this.grid[i][this.ROWS - 1 - i] == Value.E) {
                antiDiagonalWin = false;
                break;
            }
        }

        return mainDiagonalWin || antiDiagonalWin;
    }




}

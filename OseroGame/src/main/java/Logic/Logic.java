package Logic;


public class Logic {
	/**
	 * 石を置くメソッド
	 */
	public void placeStone(int[][] board, int row, int column, int player) {
		if(board[row][column] == 0) {
			board[row][column] = player;
			
		}
	}
}

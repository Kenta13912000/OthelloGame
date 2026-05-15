package Logic;

import othelloGame.Player;

public class Logic {
	//フィールド
	private final int MIN_SIZE = 0;
	private final int MAX_SIZE = 8;

	/**
	 * 石を置くメソッド
	 * 
	 * @param board 盤面情報
	 * @param row 配置する行
	 * @param column 配置する列
	 * @param player プレイヤー情報
	 */
	public boolean placeStone(int[][] board, int row, int column, Player player) {
		if (board[row][column] == 0) {
			board[row][column] = player.getValue();
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * プレイヤー交代メソッド
	 * 
	 * @param player プレイヤー
	 * @return プレイヤー(Black or White)を返す
	 */
	public Player switchPlayer(Player player) {
		if (player == Player.Black) {
			return Player.White;
		} else {
			return Player.Black;
		}
	}


	/**
	 * ひっくり返しメソッド（改良版1）
	 * 
	 * @param board 盤面情報
	 * @param baseRow 始点となる行
	 * @param baseColumn 始点となる列
	 * @param player プレイヤー情報
	 * @param rowDirection 行の方向(+1, -1, 0)
	 * @param colDirection 列の方向(+1, -1, 0)
	 */
	public void turnOverStone(int[][] board, int baseRow, int baseColumn, Player player, int rowDirection, int colDirection) {
		int tmpColumn = baseColumn + colDirection;
		int tmpRow = baseRow + rowDirection;
		int count = 0;
		boolean check = false;
		
		//引数のバリデーション
		if(rowDirection > 1 || -1 > rowDirection || colDirection > 1 || -1 > colDirection) {
			return;
		}
		
		if(rowDirection == 0 && colDirection == 0) {
			return;
		}
		
		//何個ひっくり返せるか数える
		while (MIN_SIZE <= tmpRow && tmpRow < MAX_SIZE && MIN_SIZE <= tmpColumn && tmpColumn < MAX_SIZE) {
			if (board[tmpRow][tmpColumn] == 0) {
				count = 0;
				break;
			}else if(board[tmpRow][tmpColumn] == player.getValue()) {
				break;
			}else {
				tmpColumn += colDirection;
				tmpRow += rowDirection;
				count++;
			}
		}
		
		//盤面外・最後が自分の石かチェック
		if(MIN_SIZE > tmpRow || tmpRow >= MAX_SIZE || MIN_SIZE > tmpColumn || tmpColumn >= MAX_SIZE ) {
			check = false;
		}else if(board[tmpRow][tmpColumn] == player.getValue()){	//最後が自分の石かチェック
			check = true;
		}else {
			check = false;
		}
		
		//ひっくり返す処理
		if (count >= 1 && check) {
			int targetRow = baseRow;
			int targetColumn = baseColumn;
			
			for (int i = 0; i < count; i++) {
				targetRow += rowDirection;
				targetColumn += colDirection;

				board[targetRow][targetColumn] = player.getValue();
			}
		}
	}
	
}

package othelloGame;

/**
 * プレイヤーを表すenum型
 */
public enum Player {
	//各プレイヤーを定義
	Black(1),
	White(2);
	
	//フィールド
	private final int value;
	
	//コンストラクタ
	Player(int value){
		this.value= value;
	}
	
	/**
	 * 表示用の石のゲッターメソッド
	 * 
	 * @return 石（黒or白）を返す
	 */
	public int getValue() {
		return value;
	}
}

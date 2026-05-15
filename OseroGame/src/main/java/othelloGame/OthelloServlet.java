package othelloGame;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Logic.Logic;

/**
 * Servlet implementation class OseroServlet
 */
@WebServlet("/Servlet")
public class OthelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OthelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		int[][] board = (int[][]) session.getAttribute("board");
		Player player = (Player) session.getAttribute("player");

		//初回盤面設定
		if (board == null) {
			//盤面初期化	
			board = new int[8][8];

			//初期配置
			board[3][3] = 2;
			board[3][4] = 1;
			board[4][3] = 1;
			board[4][4] = 2;

			//セッション保存
			session.setAttribute("board", board);
		}

		//初回プレイヤー設定
		if (player == null) {
			//プレイヤー初期化
			player = Player.White;

			//セッション保存
			session.setAttribute("player", player);
		}

		//jpsへ転送
		RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
		rd.forward(request, response);
	}

	// TODO ブラウザバック時の画面制御対応
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		int[][] board = (int[][]) session.getAttribute("board");
		Player player = (Player) session.getAttribute("player");

		//リセット
		if (request.getParameter("reset") != null) {
			session.removeAttribute("board");
			session.removeAttribute("player");
			
			doGet(request, response);
			return;
		}

		//リクエストから行と列を取得
		int row = Integer.parseInt(request.getParameter("row"));
		int column = Integer.parseInt(request.getParameter("column"));

		//配置メソッド呼び出し
		Logic logic = new Logic();
		boolean placeFlag = logic.placeStone(board, row, column, player);

		if (placeFlag) {
			logic.turnOverStone(board, row, column, player, 0, 1); //右方向
			logic.turnOverStone(board, row, column, player, 0, -1); //左方向
			logic.turnOverStone(board, row, column, player, -1, 0); //上方向
			logic.turnOverStone(board, row, column, player, 1, 0); //下方向
			logic.turnOverStone(board, row, column, player, -1, -1); //斜め・左上
			logic.turnOverStone(board, row, column, player, -1, 1); //斜め・右上
			logic.turnOverStone(board, row, column, player, 1, -1); //斜め・左下
			logic.turnOverStone(board, row, column, player, 1, 1); //斜め・右下

			//プレイヤー交代		
			player = logic.switchPlayer(player);
		}

		//セッション保存
		session.setAttribute("board", board);
		session.setAttribute("player", player);

		//jspへ転送
		RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
		rd.forward(request, response);
	}

}

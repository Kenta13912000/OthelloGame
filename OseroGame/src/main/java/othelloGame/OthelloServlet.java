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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//盤面初期化	
		int[][] board = new int[8][8];
		
		//初期配置
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
		
		//セッション保存
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		
		//jpsへ転送
		RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//リクエストから行と列を取得
		int row = Integer.parseInt(request.getParameter("row"));
		int column = Integer.parseInt(request.getParameter("column"));
		
		//セッション取得
		HttpSession session = request.getSession();
		int[][] board = (int[][])session.getAttribute("board");
		
		//プレイヤー設定
		Player player = Player.White;
		
		//配置メソッド呼び出し
		Logic logic = new Logic();
		logic.placeStone(board, row, column, player.getValue());
		
		//セッション保存
		session.setAttribute("board", board);
		
		//jspへ転送
		RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
		rd.forward(request, response);
	}

}

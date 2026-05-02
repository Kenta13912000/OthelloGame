<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int [][] board = (int[][])session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>オセロ盤面</title>

<link rel="stylesheet" href="css/style.css?v=2">
</head>
<body>
	<h2>オセロゲーム</h2>
	<table>
		<% for(int i = 0;i < 8; i++ ){ %>
		<tr>
			<% for(int j = 0; j< 8 ;j++) {%>
			<td>
				<form action="Servlet" method="post" >
					<input type = "hidden" name = "row" value = "<%= i %>">
					<input type = "hidden" name = "column" value = "<%= j %>">
					<button type="submit">
						<%if(board[i][j] == 1){ %>
							●
						<%}else if(board[i][j] == 2) {%>
							○
						<%}else { %>
						<% } %>
					</button>
				</form>
			</td>	
			<%} %>
		</tr>	
		<%} %>	
	</table>
	
</body>
</html>
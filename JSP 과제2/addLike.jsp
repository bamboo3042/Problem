<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%	
	request.setCharacterEncoding("UTF-8");
	String b_idx = request.getParameter("b_idx");
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = "";
	ResultSet rs = null;
	int like;
	
	try{
		conn = Dbconn.getConnection();
		if(conn != null){
			sql = "UPDATE tb_board SET b_like= b_like + 1 WHERE b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			pstmt.executeUpdate();
			
			sql = "SELECT b_like FROM tb_board WHERE b_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				like = rs.getInt("b_like");
				out.println(like);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
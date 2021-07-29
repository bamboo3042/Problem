package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.db.Dbconn;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardDTO> boardList = new ArrayList();
	String sql = "";
	
	public int editBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{			
			conn = Dbconn.getConnection();
			
			if(conn != null){
				if(board.getB_file() != null && board.getB_file() != "") {
				sql = "update tb_board set b_title=?, b_content=?, b_file=? where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getB_title());
				pstmt.setString(2, board.getB_content());
				pstmt.setString(3, board.getB_file());
				pstmt.setLong(4, board.getIdx());
				pstmt.executeUpdate();
				}
				else {
					sql = "update tb_board set b_title=?, b_content=? where b_idx=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getB_title());
					pstmt.setString(2, board.getB_content());
					pstmt.setLong(3, board.getIdx());
					pstmt.executeUpdate();
					
				}
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return 0;
	}
	
	public BoardDTO updateLike(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int b_like = 0;
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "update tb_board set b_like = b_like + 1 where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				pstmt.executeUpdate();
				
				sql = "select b_like from tb_board where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				rs = pstmt.executeQuery();
			
				if(rs.next()){
					board.setLike(rs.getInt("b_like"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return board;
	}
	
	public BoardDTO viewBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				pstmt.executeUpdate();
				
				sql = "select b_idx, b_userid, b_title, b_content, b_regdate, b_like, b_hit, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					board.setUserid(rs.getString("b_userid"));
					board.setB_title(rs.getString("b_title"));
					board.setB_content(rs.getString("b_content"));
					board.setRegdate(rs.getString("b_regdate"));
					board.setLike(rs.getInt("b_like"));
					board.setHit(rs.getInt("b_hit"));
					board.setB_file(rs.getString("b_file"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			board = null;
		}
		return board;
	}
	
	public int deleteBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "delete from tb_board where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				pstmt.executeUpdate();
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int createBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "insert into tb_board(b_userid, b_title, b_content, b_file) values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getUserid());
				pstmt.setString(2, board.getB_title());
				pstmt.setString(3, board.getB_content());
				pstmt.setString(4, board.getB_file());
				pstmt.executeUpdate();
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int countBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null) {
				sql = "select count(b_idx) as total from tb_board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt("total");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<BoardDTO> boardList(int start, int pagePerCount){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = Dbconn.getConnection();
			sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pagePerCount);
			rs = pstmt.executeQuery();
//			sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setIdx(rs.getLong("b_idx"));
				board.setUserid(rs.getString("b_userid"));
				board.setB_title(rs.getString("b_title"));
				board.setHit(rs.getInt("b_hit"));
				board.setLike(rs.getInt("b_like"));
				board.setRegdate(rs.getString("b_regdate"));
				board.setB_file(rs.getString("b_file"));
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
}
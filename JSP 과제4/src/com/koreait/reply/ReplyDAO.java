package com.koreait.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board.BoardDTO;
import com.koreait.db.Dbconn;

public class ReplyDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<ReplyDTO> replyList = new ArrayList();
	
	public String countReply(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;		
		try {
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "select count(re_idx) as replycnt from tb_reply where re_boardidx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				int replycnt = 0;
				String replycnt_str = "";
				if(rs.next()){
					replycnt = rs.getInt("replycnt");
					if(replycnt > 0){
						 replycnt_str = "["+replycnt+"]";
					}
				}
				return replycnt_str;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public int addReply(ReplyDTO reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "insert into tb_reply (re_userid, re_content, re_boardidx) values (?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reply.getUserid());
				pstmt.setString(2, reply.getContent());
				pstmt.setLong(3, reply.getBoardidx());
				pstmt.executeUpdate();
				
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<ReplyDTO> viewReply(BoardDTO board){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx=? order by re_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					ReplyDTO reply = new ReplyDTO();
					reply.setIdx(rs.getLong("re_idx"));
					reply.setUserid(rs.getString("re_userid"));
					reply.setContent(rs.getString("re_content"));
					reply.setRegdate(rs.getString("re_regdate"));
					replyList.add(reply);
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return replyList;
	}
}

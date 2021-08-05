package com.koreait.board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.db.Dbconn;
import com.koreait.db.SqlMapConfig;

public class ReplyDAO {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   ArrayList<ReplyDTO> list = new ArrayList();
	   
	   String sql = "";
	   
	   SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	   SqlSession sqlsession;
	   
	
	public int reply(ReplyDTO reply) {
		try {
			
			conn = Dbconn.getConnection();
			if(conn!= null) {
				 sql = "insert into tb_reply (re_userid, re_content, re_boardidx) values (?,?,?)";
				 pstmt = conn.prepareStatement(sql);
	             pstmt.setString(1, reply.getUserid());
	             pstmt.setString(2, reply.getContent());
	             pstmt.setInt(3, reply.getBoardidx());

				
	             if(pstmt.executeUpdate() > 0) {
	  			   return 1;
	  				}
				}
	  		}catch(Exception e) {
	  			e.printStackTrace();
	  		}
	  		return 0;
	  	}
	
	public ArrayList<ReplyDTO> viewReply(int idx) {
		System.out.println(idx);
			
		//	System.out.println(reply);
		List<HashMap<String, String>> dataMapList = new ArrayList();
		dataMapList = sqlsession.selectList("Member.viewreply",idx);
        for (HashMap<String, String> dataMap : dataMapList) {
        	ReplyDTO reply = new ReplyDTO();
			reply.setReidx(Integer.parseInt(dataMap.get("re_idx")));
			reply.setUserid(dataMap.get("re_userid"));
			reply.setContent(dataMap.get("re_content"));
			reply.setRegdate(dataMap.get("re_regdate"));
			list.add(reply);
		}	
        return list;						
	}
	
	public int reply_del(ReplyDTO reply) {
		try {
			conn = Dbconn.getConnection();
			sql = "delete from tb_reply where re_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getReidx());
			if(pstmt.executeUpdate() > 0) {
			   return 1;
			}
		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return 0;
	}
	
	public int replycnt(int idx) {
		try {
			conn = Dbconn.getConnection();
			if(conn!= null) {
				sql = "select count(re_idx) as replycnt from tb_reply where re_boardidx="+idx;
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				int replycnt=0;
				if(rs.next()) {
					replycnt= rs.getInt("replycnt");
				}
				return replycnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
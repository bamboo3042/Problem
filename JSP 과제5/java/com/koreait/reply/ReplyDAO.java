package com.koreait.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.board.BoardDTO;
import com.koreait.db.Dbconn;
import com.koreait.db.SqlMapConfig;

public class ReplyDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<ReplyDTO> replyList = new ArrayList();
	
	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public ReplyDAO() {
		sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
		System.out.println("리플 마이바티스 설정 성공!");
	}
	
	public String countReply(BoardDTO board) {
		Long idx = board.getIdx();
		int replycnt = sqlsession.selectOne("Reply.count", idx);
		String replycnt_str = "";
		replycnt_str = "["+replycnt+"]";

		return replycnt_str;
	}
	
	public int addReply(ReplyDTO reply) {
		HashMap<String, String> dataMap = new HashMap();
		
		dataMap.put("re_userid", reply.getUserid());
		dataMap.put("re_userpassword", reply.getUserpassword());
		dataMap.put("re_content", reply.getContent());
		dataMap.put("re_boardidx", String.valueOf(reply.getBoardidx()));

		return sqlsession.insert("Reply.add", dataMap);
	}
	
	public List<ReplyDTO> viewReply(BoardDTO board){
		Long idx = board.getIdx();
		
		List<HashMap<String, String>> dataMapList = new ArrayList<HashMap<String,String>>();
		
		dataMapList = sqlsession.selectList("Reply.list", idx);
		
		for (HashMap<String, String> hashMap : dataMapList) {
			ReplyDTO reply = new ReplyDTO();
			reply.setIdx(Integer.parseInt(String.valueOf(hashMap.get("re_idx"))));
			reply.setUserid(hashMap.get("re_userid"));
			reply.setContent(hashMap.get("re_content"));
			replyList.add(reply);
		}
		
		return replyList;
	}
}

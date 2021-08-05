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
import com.oreilly.servlet.MultipartRequest;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardDTO> boardList = new ArrayList();
	String sql = "";
	
	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BoardDAO() {
		sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
		System.out.println("보드 마이바티스 설정 성공!");
	}
		
	public int editBoard(MultipartRequest multi) {
		HashMap<String, String> dataMap = new HashMap();
		
		String title = multi.getParameter("b_title");
		String content = multi.getParameter("b_content");
		String file = multi.getFilesystemName("b_file");
		Long idx = Long.parseLong(multi.getParameter("b_idx"));

		if(title.isEmpty() || content.isEmpty()) return 0;
		
		dataMap.put("b_title", title);
		dataMap.put("b_content", content);
		dataMap.put("b_file", file);
		dataMap.put("b_idx", String.valueOf(idx));
		
		return sqlsession.update("Board.edit", dataMap);
	}
	
	public BoardDTO editviewBoard(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		
		dataMap.put("b_userid", board.getUserid());
		dataMap.put("b_userpassword", board.getUserpassword());
		dataMap = sqlsession.selectOne("Board.editview", dataMap);
		
		if(dataMap != null) {
			board.setIdx(Long.parseLong(String.valueOf(dataMap.get("b_idx"))));
			board.setUserid(dataMap.get("b_userid"));
			board.setB_title(dataMap.get("b_title"));
			board.setB_content(dataMap.get("b_content"));
			board.setHit(Integer.parseInt(String.valueOf(dataMap.get("b_hit"))));
			board.setB_file(dataMap.get("b_file"));
			return board;
		}
		
		return null;
	}
		
	public BoardDTO viewBoard(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		
		long idx = board.getIdx();
		
		if(sqlsession.update("Board.addhit", idx) == 1) {
			dataMap = sqlsession.selectOne("Board.view", idx);
			if(dataMap != null) {
				board.setUserid(dataMap.get("b_userid"));
				board.setUsername(dataMap.get("b_username"));
				board.setB_title(dataMap.get("b_title"));
				board.setB_content(dataMap.get("b_content"));
				board.setHit(Integer.parseInt(String.valueOf(dataMap.get("b_hit"))));
				board.setB_file(dataMap.get("b_file"));
				
				return board;
			}
		}
		return null;
	}
	
	public int deleteBoard(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		dataMap.put("b_userpassword", board.getUserpassword());
		
		return sqlsession.delete("Board.delete", dataMap);
	}
	
	public int createBoard(MultipartRequest multi) {
		HashMap<String, String> dataMap = new HashMap();
		
		dataMap.put("b_userid", multi.getParameter("userid"));
		dataMap.put("b_userpassword", multi.getParameter("userpassword"));
		dataMap.put("b_username", multi.getParameter("username"));
		dataMap.put("b_title", multi.getParameter("b_title"));
		dataMap.put("b_content", multi.getParameter("b_content"));
		dataMap.put("b_file", multi.getFilesystemName("b_file"));
		
		return sqlsession.insert("Board.create", dataMap);
	}
	
	public int countBoard() {
		return sqlsession.selectOne("Board.count");
	}
	
	public List<BoardDTO> boardList(int start, int pagePerCount){
		HashMap<String, Integer> dataMap = new HashMap();
		List<HashMap<String, String>> dataMapList = new ArrayList();
		
		dataMap.put("start", start);
		dataMap.put("pagePerCount", pagePerCount);
		
		dataMapList = sqlsession.selectList("Board.list", dataMap);
		
		for (HashMap<String, String> hashMap : dataMapList) {
			BoardDTO board = new BoardDTO();
			board.setIdx(Long.parseLong(String.valueOf(hashMap.get("b_idx"))));
			board.setUserid(hashMap.get("b_userid"));
			board.setUsername(hashMap.get("b_username"));
			board.setB_title(hashMap.get("b_title"));
			board.setHit(Integer.parseInt(String.valueOf(hashMap.get("b_hit"))));
			board.setB_file(hashMap.get("b_file"));
			boardList.add(board);
		}
		
		return boardList;
	}
}
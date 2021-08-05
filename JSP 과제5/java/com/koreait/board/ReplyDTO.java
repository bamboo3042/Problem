package com.koreait.board;

import java.util.ArrayList;

public class ReplyDTO {
	private int reidx;
	private String userid;
	private String content;
	private String regdate;
	private int boardidx;
	private ArrayList<ReplyDTO> dtolist;


	public ArrayList<ReplyDTO> getDtolist() {
		return dtolist;
	}
	public void setDtolist(ArrayList<ReplyDTO> dtolist) {
		this.dtolist = dtolist;
	}
	public int getReidx() {
		return reidx;
	}
	public void setReidx(int reidx) {
		this.reidx = reidx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getBoardidx() {
		return boardidx;
	}
	public void setBoardidx(int boardidx) {
		this.boardidx = boardidx;
	}
	@Override
	public String toString() {
		return "ReplyDTO [re_idx=" + reidx + ", user_id=" + userid + ", re_content=" + content + ", re_regdate=" + regdate
				+ ", re_boardidx=" + boardidx + "]";
	}

	
}

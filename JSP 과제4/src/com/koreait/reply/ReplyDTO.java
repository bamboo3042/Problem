package com.koreait.reply;

public class ReplyDTO {
	long idx;
	String userid;
	String content;
	String regdate;
	long boardidx;
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
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
	public long getBoardidx() {
		return boardidx;
	}
	public void setBoardidx(long boardidx) {
		this.boardidx = boardidx;
	}
	@Override
	public String toString() {
		return "ReplyDTO [idx=" + idx + ", userid=" + userid + ", content=" + content + ", regdate=" + regdate
				+ ", boardidx=" + boardidx + "]";
	}
}

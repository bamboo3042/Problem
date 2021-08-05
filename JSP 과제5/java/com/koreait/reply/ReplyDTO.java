package com.koreait.reply;

public class ReplyDTO {
	long idx;
	String userid;
	String userpassword;
	String content;
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
	public long getBoardidx() {
		return boardidx;
	}
	public void setBoardidx(long boardidx) {
		this.boardidx = boardidx;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	@Override
	public String toString() {
		return "ReplyDTO [idx=" + idx + ", userid=" + userid + ", userpassword=" + userpassword + ", content=" + content
				+ ", boardidx=" + boardidx + "]";
	}
}


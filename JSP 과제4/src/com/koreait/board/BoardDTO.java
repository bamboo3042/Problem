package com.koreait.board;

public class BoardDTO {
	private Long idx;
	private String userid;
	private String b_title;
	private String b_content;
	private int hit;
	private int like;
	private String regdate;
	private String b_file;
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getB_file() {
		return b_file;
	}
	public void setB_file(String b_file) {
		this.b_file = b_file;
	}
	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", userid=" + userid + ", b_title=" + b_title + ", b_content=" + b_content
				+ ", hit=" + hit + ", like=" + like + ", regdate=" + regdate + ", b_file=" + b_file + "]";
	}

	
}
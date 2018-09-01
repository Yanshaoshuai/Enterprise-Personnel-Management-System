package com.domain;

import javax.swing.tree.DefaultMutableTreeNode;

public class Partment extends DefaultMutableTreeNode{
	private String pid;
	private String pname;
	private String ppid;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
		super.setUserObject(pname);
	}
	public String getPpid() {
		return ppid;
	}
	public void setPpid(String ppid) {
		this.ppid = ppid;
	}
	@Override
	public String toString() {
		return pname;
	}
	
}

package com.mmju.jsp.ep7;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class HelloTag2 implements Tag {

	private PageContext ctx = null;
	private Tag parrent = null;
	private String name = null;

	@Override
	public int doEndTag() throws JspException {

		try {
			ctx.getOut().print("Hello from Custom Tag! " + getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public Tag getParent() {
		return this.parrent;
	}

	@Override
	public void release() {
	}

	@Override
	public void setPageContext(PageContext arg) {
		this.ctx = arg;
	}

	@Override
	public void setParent(Tag arg) {
		this.parrent = arg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

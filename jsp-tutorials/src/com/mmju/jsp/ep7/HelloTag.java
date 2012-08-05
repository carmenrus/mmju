package com.mmju.jsp.ep7;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class HelloTag implements Tag {

	private PageContext ctx = null;
	private Tag parrent = null;

	@Override
	public int doEndTag() throws JspException {

		try {
			ctx.getOut().print("Hello Custom Tag!");
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

}

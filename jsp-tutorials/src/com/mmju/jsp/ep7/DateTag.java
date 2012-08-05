package com.mmju.jsp.ep7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class DateTag implements Tag {

	private Tag parent = null;
	private PageContext ctx = null;

	@Override
	public int doEndTag() throws JspException {
		try {
			ctx.getOut().print(
					" Now is "
							+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
									.format(new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			ctx.getOut().print("Hi!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Tag getParent() {
		return this.parent;
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
		this.parent = arg;
	}

}

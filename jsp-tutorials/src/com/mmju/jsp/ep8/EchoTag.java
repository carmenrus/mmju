package com.mmju.jsp.ep8;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

public class EchoTag implements IterationTag {

	private PageContext ctx = null;
	private Tag parent = null;
	private String message = null;
	private Integer times = null;
	private Integer var = 0;

	@Override
	public int doEndTag() throws JspException {
		this.out("</table>");
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		this.out("<table><tr><td>");
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
	public void setPageContext(PageContext arg0) {
		this.ctx = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		this.parent = arg0;
	}

	@Override
	public int doAfterBody() throws JspException {
		var++;
		out("</td>");
		out("<td>");
		out(message);
		out("</td>");
		out("</tr>");
		if (var < times) {
			out("<tr>");
			out("<td>");
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	private void out(String str) {
		try {
			this.ctx.getOut().println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.mmju.jsp.ep8;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class EchoTagSupport extends TagSupport {

	private static final long serialVersionUID = -8556986664817835060L;

	private String message = null;
	private Integer times = null;
	private Integer var = 0;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimes(Integer times) {
		this.times = times;
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

	private void out(String str) {
		try {
			super.pageContext.getOut().println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.mmju.jsp.ep8;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class EchoBodyTagSupport extends BodyTagSupport {

    private static final long serialVersionUID = -7851512871222689936L;

	private String message = null;
	private String title = null;
	private Integer times = null;
	private Integer var = 0;

	@Override
    public int doAfterBody() throws JspException {

		BufferedReader br = new BufferedReader(bodyContent.getReader());
		try {
			if(null == title)
				title = br.readLine();
        } catch (IOException e) {
	        e.printStackTrace();
        }

		this.outBody(this.getRow(title, message));
		if( ++var < times) {
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
    }

	@Override
    public int doEndTag() throws JspException {
		out("</table>");
	    return EVAL_PAGE;
    }

	@Override
    public void doInitBody() throws JspException {
    }

	@Override
    public int doStartTag() throws JspException {
		out("<table>");
	    return EVAL_BODY_BUFFERED;
    }

	private void out(String str) {
		try {
			super.pageContext.getOut().println(str);
        } catch (IOException e) {
	        e.printStackTrace();
        }
	}
	
	private void outBody(String str) {
		try {
			super.bodyContent.getEnclosingWriter().println(str);
        } catch (IOException e) {
	        e.printStackTrace();
        }
	}
	
	private String getRow(String title, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr><td>");
		sb.append(title);
		sb.append("</td><td>");
		sb.append(message);
		sb.append("</td></tr>");
		return sb.toString();
	}

	public void setMessage(String message) {
    	this.message = message;
    }

	public void setTimes(Integer times) {
    	this.times = times;
    }
}

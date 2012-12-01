package org.apache.jsp.jsp.episode2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ep2_002d14_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<div id=\"right\">\r\n");

try {
	Object param = request.getParameter("p");
	if(null != param) {
		String message = "";
		int p = Integer.parseInt((String)param);
		switch(p) {
		case 10:
			message = "<p>ဤစာမျက်နှာသည် ကားစာရင်းကို ဖော်ပြသော စာမျက်နှာဖြစ်ပြီး၊ application အချက်အလက်ထဲရှိ " + 
		"ကားစာရင်းကို ဖော်ပြနေပါသည်။</p>"
		+ "<p>အကယ်၍ session ထဲတွင် ကားအသစ်တစ်စီးရှိပါက ကားစာရင်းထဲတွင် ကားအသစ်ကို ဖြည့်စွက်ပြီး၊ ကားစာရင်းကို " +
		"application အချက်အလက်အတွင်းတွင် ပြန်လည် သိမ်းဆည်းပါသည်။</p>" +
		"<p>အကယ်၍ ကားစာရင်း အတွင်းတွင် ဖော်ပြရန် ကားမရှိပါက ကားအသစ်စာရင်းသွင်းရန် စာမျက်နှာ ဆီသို့ jsp:forwardကို အသုံးပြု၍ ဖော်ပြစေပါသည်။</p>";
			break;
		case 11:
			message = "ကား အသစ်စာရင်းသွင်းရာတွင် အသုံးပြုသော စာမျက်နှာဖြစ်ပါသည်။ ဤစာမျက်နှာသည် HTML Form Tag ကို အသုံးပြုထားပြီး၊ Form Action အား"
			+"ကားစာရင်းသွင်းရန် စာမှတ်နှာကို ပို့ပေးနေပါသည်။ method ကို ရေးမထားပါသဖြင့် GET ကို အသုံးပြုမည် ဖြစ်သည်။";
			break;
		case 12:
			message = "ဤစာမျက်နှာသည် ကား အသစ်ဖောင်မှ အချက်အလက်များကို ရယူကာ jsp:useBean ဖြင့် car object ကို အသစ် ပြုလုပ်ပြီး session ထဲတွင် သိမ်းဆည်းပါသည်"
			+ "တဖန် စာရင်းသွင်းမည်ကို ခလုပ်နှိပ်ပါက ကားစာရင်း စာမျက်နှာကို post လုပ်မည်ဖြစ်သည်။ ကားစာရင်းစာမျက်နှာတွင် session ထဲမှကားကို ကားစာရင်းထဲသို့သွင်းပြီး ကားစာရင်းကို ဖော်ပြပေးမည်ဖြစ်ပါသည်။"
			+ "အကယ်၍ ပြန်ပြင်မည် ခလုပ်ကိုနှိပ်ပါက history.back()ဖြင့် ယခင်စာမျက်နှာကို ပြန်မည်ဖြစ်ပါသည်။";
			break;
		default:
			break;
		}
		out.println(message);
	}
} catch (Exception e) {
	out.println("ပါရာမီတာ မှားနေပါသည်။ စာမျက်နှာ အစမှပြန်၍ အလုပ်လုပ်ပါ။");
}

      out.write("\r\n");
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

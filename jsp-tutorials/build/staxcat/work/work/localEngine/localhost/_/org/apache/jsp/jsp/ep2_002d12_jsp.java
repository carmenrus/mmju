package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.mmju.jsp.ep2.Car;

public final class ep2_002d12_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/theme/episode.css\" type=\"text/css\" />\r\n");
      out.write("<title>Script Sample</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div style=\"width: 100%; text-align: right\">\r\n");
      out.write("\t\t<a href=\"javascript:history.back();\">ယခင် စာမျက်နှာသို့</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<h3>စာရင်းသွင်းရန် ကား</h3>\r\n");
      out.write("\t\r\n");
      out.write("\t");
      com.mmju.jsp.ep2.Car car = null;
      synchronized (session) {
        car = (com.mmju.jsp.ep2.Car) _jspx_page_context.getAttribute("car", PageContext.SESSION_SCOPE);
        if (car == null){
          car = new com.mmju.jsp.ep2.Car();
          _jspx_page_context.setAttribute("car", car, PageContext.SESSION_SCOPE);
          out.write("\r\n");
          out.write("\t\t");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("car"), request);
          out.write('\r');
          out.write('\n');
          out.write('	');
        }
      }
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"left\">\r\n");
      out.write("\t<form action=\"./ep2-10.jsp\" method=\"post\">\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td width=\"180px\">ကားအမည်</td>\r\n");
      out.write("\t\t\t<td>");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((com.mmju.jsp.ep2.Car)_jspx_page_context.findAttribute("car")).getModel())));
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>ကားကုမ္ပဏီ</td>\r\n");
      out.write("\t\t\t<td>");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((com.mmju.jsp.ep2.Car)_jspx_page_context.findAttribute("car")).getBrand())));
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>ထုတ်လုပ်သည့်နှစ်</td>\r\n");
      out.write("\t\t\t<td>");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((com.mmju.jsp.ep2.Car)_jspx_page_context.findAttribute("car")).getYear())));
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr><td colspan=\"2\">\r\n");
      out.write("\t\t<input onclick=\"javascript:history.back();\" type=\"button\" value=\"ပြန်ပြင်မည်\" />\r\n");
      out.write("\t\t<input type=\"submit\" value=\"စာရင်းသွင်းမည်\" />\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t</table></form></div>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "./ep2-14.jsp?p=12", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

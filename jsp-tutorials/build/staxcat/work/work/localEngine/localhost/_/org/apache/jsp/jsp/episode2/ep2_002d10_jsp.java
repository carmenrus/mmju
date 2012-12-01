package org.apache.jsp.jsp.episode2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.mmju.jsp.ep2.Car;
import java.util.List;

public final class ep2_002d10_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/theme/episode.css\" type=\"text/css\" />\r\n");
      out.write("<title>Show List of Car</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div style=\"width: 100%; text-align: right\">\r\n");
      out.write("\t\t<a href=\"javascript:history.back();\">ယခင် စာမျက်နှာသို့</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<h3>ကားစာရင်း</h3>\r\n");
      out.write("\t");

		List<Car> carList = (List<Car>) application
				.getAttribute("car-list");
		Car car = (Car)session.getAttribute("car");
		
		if(null == carList)
			carList = new ArrayList<Car>();
		
		if(null != car) {
			carList.add(car);
			application.setAttribute("car-list", carList);
			session.removeAttribute("car");
		}
		
		if (null == carList || carList.size() == 0) {
	
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (true) {
        _jspx_page_context.forward("./ep2-11.jsp" + (("./ep2-11.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("msg", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("ဖော်ပြစရာကား စရင်းမရှိပါ သဖြင့် ကားအသစ်များကို စာရင်းသွင်းပါ။", request.getCharacterEncoding()));
        return;
      }
      out.write('\r');
      out.write('\n');
      out.write('	');

		} else {
	
      out.write("\r\n");
      out.write("\t<div id=\"left\">\r\n");
      out.write("\t<table class=\"lineTbl\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>ကားကုမ္ပဏီ</td>\r\n");
      out.write("\t\t\t\t<td>ကားအမျိုးအစား</td>\r\n");
      out.write("\t\t\t\t<td>ထုတ်လုပ်သည့်နှစ်</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody>\r\n");
      out.write("\t\t\t");

				for (Car c : carList) {
			
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(c.getBrand());
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(c.getModel());
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(c.getYear());
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");

		}
	
      out.write('\r');
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "./ep2-14.jsp" + (("./ep2-14.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("p", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("10", request.getCharacterEncoding()), out, false);
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

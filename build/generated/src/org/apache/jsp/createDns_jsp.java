package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createDns_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>New DNS</title>\n");
      out.write("        <link href=\"webs/bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <link href=\"webs/bootstrap/css/bootstrap-responsive.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"webs/jquery.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"webs/webserv.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"webs/bootstrap/js/bootstrap.js\"></script>\n");
      out.write("        ");

            if(session.getAttribute("user")==null)
                response.sendRedirect("index.jsp");
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"container\" class=\"container\">\n");
      out.write("            <form id=\"frm\" action=\"CreateDns\" method=\"POST\">\n");
      out.write("                   <fieldset>\n");
      out.write("                       <legend><em>Novo DNS do Cliente ");
      out.print(request.getParameter("name") );
      out.write("</em></legend>\n");
      out.write("                       <input type=\"hidden\" name=\"client\" value=\"");
      out.print(request.getParameter("name") );
      out.write("\"/>\n");
      out.write("                       <input type=\"hidden\" id=\"qtdePos\" name=\"qtdePos\" />\n");
      out.write("                    <h4 style=\"float: left\">Descrição</h4>\n");
      out.write("                    <h4 class=\"offset3\">&nbsp;&nbsp;&nbsp;&nbsp;Servidor</h4>\n");
      out.write("                    <div id=\"dnss\">\n");
      out.write("                        <div id=\"dns1\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"clearfix\"></div>\n");
      out.write("                    \n");
      out.write("                   <input type=\"button\" id=\"go\" value=\"Salvar\" class=\"btn btn-info\" />\n");
      out.write("                    <a type=\"button\" href=\"home.jsp\" class=\"btn btn-info\">Voltar</a>\n");
      out.write("                   </fieldset>\n");
      out.write("                </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("    <script>\n");
      out.write("        var pos=1;\n");
      out.write("        function addDesc(){\n");
      out.write("            pos++;\n");
      out.write("            $('#dnss').append('<div id=\\\"dns'+pos+'\\\"></div>');\n");
      out.write("            loadInternal('WebServ?servers=options&pos='+pos,'dns'+pos+'');\n");
      out.write("        }\n");
      out.write("        function delDesc(id){\n");
      out.write("            if(id!=='dns1')\n");
      out.write("                $('#'+id).remove();\n");
      out.write("        }\n");
      out.write("        $(function(){\n");
      out.write("            loadInternal('WebServ?servers=options&pos='+pos,'dns1');\n");
      out.write("        });\n");
      out.write("        $('#go').click(function(){\n");
      out.write("            $('#qtdePos').val(pos);\n");
      out.write("            alert($('#frm').html());\n");
      out.write("            $('#frm').submit();\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

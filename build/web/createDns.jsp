<%-- 
    Document   : createDns
    Created on : 02/07/2014, 15:48:21
    Author     : jefaokpta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New DNS</title>
        <link href="webs/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="webs/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="webs/jquery.js"></script>
        <script type="text/javascript" src="webs/webserv.js"></script>
        <script type="text/javascript" src="webs/bootstrap/js/bootstrap.js"></script>
        <%
            if(session.getAttribute("user")==null)
                response.sendRedirect("index.jsp");
        %>
    </head>
    <body>
        <div id="container" class="container">
            <form id="frm" action="CreateDns" method="POST">
                   <fieldset>
                       <legend><em>Novo DNS do Cliente <%=request.getParameter("name") %></em></legend>
                       <input type="hidden" name="client" value="<%=request.getParameter("name") %>"/>
                       <input type="hidden" id="qtdePos" name="qtdePos" />
                    <h4 style="float: left">Descrição</h4>
                    <h4 class="offset3">&nbsp;&nbsp;&nbsp;&nbsp;Servidor</h4>
                    <div id="dnss">
                        <div id="dns1"></div>
                    </div>
                    <div class="clearfix"></div>
                    
                   <input type="button" id="go" value="Salvar" class="btn btn-info" />
                    <a type="button" href="home.jsp" class="btn btn-info">Voltar</a>
                   </fieldset>
                </form>
        </div>
    </body>
    <script>
        var pos=1;
        function addDesc(){
            pos++;
            $('#dnss').append('<div id=\"dns'+pos+'\"></div>');
            loadInternal('WebServ?servers=options&pos='+pos,'dns'+pos+'');
        }
        function delDesc(id){
            if(id!=='dns1')
                $('#'+id).remove();
        }
        $(function(){
            loadInternal('WebServ?servers=options&pos='+pos,'dns1');
        });
        $('#go').click(function(){
            $('#qtdePos').val(pos);
            $('#frm').submit();
        });
    </script>
</html>

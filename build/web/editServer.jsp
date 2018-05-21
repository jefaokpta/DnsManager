<%-- 
    Document   : editServer
    Created on : 11/08/2014, 11:34:45
    Author     : jefaokpta
--%>

<%@page import="com.dns.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Server</title>
        <link href="webs/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="webs/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="webs/functions.js"></script>
        <script type="text/javascript" src="webs/jquery.js"></script>
        <script type="text/javascript" src="webs/bootstrap/js/bootstrap.js"></script>
        <%
            if(session.getAttribute("user")==null)
                response.sendRedirect("index.jsp");
            DB db=new DB();
            String data[]=db.editServer(request.getParameter("server"));
        %>
    </head>
    <body>
        <div id="container" class="container">
            <form id="frm" action="EditServer" method="POST">
                   <fieldset>
                    <legend><em>Novo Servidor</em></legend>
                    <h4>Nome do Servidor</h4>
                    <input type="hidden" name="id" value="<%=data[0] %>"/>
                    <input type="text" id="name" name="name" value="<%=data[1] %>"/>
                    <h4>IP do Servidor</h4>
                    <input id="ip" type="text" name="ip" value="<%=data[2] %>" onkeypress ="return ( maskIP(event,this) );" 
                           onblur="if (!validateIP(this.value)) this.style.backgroundColor='#FF9999'; else this.style.backgroundColor='#FFFFFF';"/></br>
                    <input type="button" id="go" value="Salvar" class="btn btn-info" />
                    <a type="button" href="home.jsp" class="btn btn-info">Voltar</a>
                   </fieldset>
                </form>
        </div>
    </body>
    <script>
        $('#go').click(function(){
            if($('#name').val()===''||$('#ip').val()==='')
                alert('Preencha todos os campos!');
            else
                $('#frm').submit();
        });
    </script>
</html>

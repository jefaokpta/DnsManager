<%-- 
    Document   : home
    Created on : 01/07/2014, 17:35:38
    Author     : jefaokpta
--%>

<%@page import="com.dns.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vcom DNS</title>
        <link href="webs/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="webs/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="webs/jquery.js"></script>
        <script type="text/javascript" src="webs/functions.js"></script>
        <script type="text/javascript" src="webs/bootstrap/js/bootstrap.js"></script>
        <%
            if(session.getAttribute("user")==null)
                response.sendRedirect("index.jsp");
            if(session.getAttribute("error")!=null){
                out.print("<script>"
                        + "alert('"+session.getAttribute("error").toString().replaceAll("'", "")+"');"
                        + "</script>");
                session.removeAttribute("error");
            }
        %>
    </head>
    <body>
        <div id="container" class="container">
            <h2>Vcom DNS</h2>
            <div>
                <a href="createServer.jsp"><h5>NOVO SERVIDOR</a>
        
                <a class="offset3" href="#" onclick="reloadBind();">REINICIA BIND</a>
                <a href="Logout"><img class="icon-search icon-off offset2"/></a></h5>
            </div>
            <div class="clearfix"></div>
            <h4>Lista de Servidores</h4>
            <%
                DB db=new DB();
                out.print(db.listServers());
            %>
            <div class="clearfix"></div>
            <a href="#" onclick="newClient();"><h5>NOVO CLIENTE</h5></a>
            <h4>Lista de Clientes</h4>
            <%
                out.print(db.listClients());
            %>
        </div>
        <div id="alert"></div>
    </body>
    <script>
        function deleteDns(name,id){
            if(confirm('Deseja realmente apagar o DNS '+name)===true)
                window.location='DelDns?dns='+id;
        }
        function deleteServer(name,id){
            if(confirm('Deseja realmente apagar o Server '+name)===true)
                window.location='DelServer?server='+id;
        }
        function deleteClient(name,id){
            if(confirm('Deseja realmente apagar o Cliente '+name)===true)
                window.location='DelDns?client='+id;
        }
        $(function(){
            $('a').tooltip('hide');
        });
    </script>
</html>

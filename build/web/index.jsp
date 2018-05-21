<%-- 
    Document   : index
    Created on : 01/07/2014, 15:44:41
    Author     : jefaokpta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vcom DNS</title>
        <link href="webs/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="webs/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="webs/jquery.js"></script>
        <script type="text/javascript" src="webs/bootstrap/js/bootstrap.js"></script>
    </head>
    <body>
        <%
            if(session.getAttribute("error")!=null)
                out.print("<script>"
                        + "alert('"+session.getAttribute("error")+"');"
                        + "</script>");
        %>
        <div id="container" class="container span5 offset4">
               <div class="span3">
               <form id="frm" action="Controller" method="POST">
                   <fieldset>
                    <legend><em>Autenticação Vcom DNS</em></legend>                   
                    <input type="text" id="name" name="user" placeholder="Usuário" />
                    <input id="pass" type="password" name="pass" placeholder="Senha" /></br>
                    <input type="button" id="go" value="Entrar" class="btn btn-info" />
                   </fieldset>
                </form>
               </div>
        </div>
    </body>
    <script>
        $('#go').click(function(){
            if($('#name').val()===''||$('#pass').val()==='')
                alert('Preencha todos os campos!');
            else
                $('#frm').submit();
        });
    </script>
</html>

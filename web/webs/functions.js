/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function maskIP(e,obj){
        if (document.all){var evt=event.keyCode;} // caso seja IE
        else{var evt = e.charCode;}    // do contrário...
        if (evt <20) return true;    // liberando teclas de controle
        if ( (/^(\d{1,3}\.){3}\d{3}$/).test(obj.value) ) return false;        
        var chr= String.fromCharCode(evt);    // pegando a tecla digitada
        if (! (/[\d\.]/).test(chr)) return false; // testando se é uma tecla válida (um digito ou um ponto)
        if (chr=='.')
            return (!(/\.$|^(\d{1,3}\.){3}/).test(obj.value) );
        else 
            if( (/\d{3}$/).test(obj.value) )
                obj.value+='.';            
        return true;
    }
    // /Mascara IP    //

    // Valida IP    //    
    function validateIP(ip,showErrMsg){
        a = (/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/).test(ip);
        if (!a && showErrMsg){
            alert(ip+' não é um ip válido!');
        }
        return a;
    }
    function newClient(){
        $('#alert').html('<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\n\
                    <div class="modal-header">\n\
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>\n\
                    <h3 id="myModalLabel">Criação de novo Cliente</h3> </div>\n\
                    <div class="modal-body">\n\
                    <form id="frm" action="CreateClient" method="POST">\n\
                    <p class="text-info">Nome do Cliente</p>\n\
                    <input id="desc" type="text" name="name" /></form></div>\n\
                    <div class="modal-footer">\n\
                    <button class="btn disabled" data-dismiss="modal" aria-hidden="true">Cancelar</button>\n\
                    <a href="#" onclick="goFrm();" class="btn btn-info">Criar</a>\n\
                    </div></div>\n\
                        <script>\n\
                            function goFrm(){$("#frm").submit();}\n\
                         </script>');
        $('#myModal').modal('show');
    } 
    function reloadBind(){
        $('#alert').html('<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\n\
                    <div class="modal-header">\n\
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>\n\
                    <h3 id="myModalLabel">Senha Requerida</h3> </div>\n\
                    <div class="modal-body">\n\
                    <form id="frm" action="ReloadBind" method="POST">\n\
                    <p class="text-info">Senha de Root</p>\n\
                    <input id="desc" type="password" name="password" /></form></div>\n\
                    <div class="modal-footer">\n\
                    <button class="btn disabled" data-dismiss="modal" aria-hidden="true">Cancelar</button>\n\
                    <a href="#" onclick="goFrm();" class="btn btn-info">Criar</a>\n\
                    </div></div>\n\
                        <script>\n\
                            function goFrm(){$("#frm").submit();}\n\
                         </script>');
        $('#myModal').modal('show');
    } 


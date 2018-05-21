function loadInternal(url,local)
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        // code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
    }
    else
    {
		// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function()
    {
		if(xmlhttp.readyState==0)
		{
            document.getElementById(local).innerHTML='<div style="width:100%;font-family:verdana;font-weight:bold;font-size:12px;color:#666666;text-align:center;" align="center" valign="middle">Conectando na Internet&nbsp;&nbsp;<img src="img/ajax-loader.gif" /></div>';
		}
		if(xmlhttp.readyState==1)
		{
            document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Carregando p&aacute;gina&nbsp;&nbsp;<img src="img/ajax-loader.gif" /></div>';
		}
		if(xmlhttp.readyState==4)
		{
            document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Finalizando&nbsp;&nbsp;<img src="img/ajax-loader.gif" /></div>';
			
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                //document.location.href=url;
				exeJS(document.getElementById(local).innerHTML=xmlhttp.responseText);
                
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==400)
            {
                document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Solicita&ccedil;&atilde;o inv&aacute;lida. Contate o administrador do sistema e informe o erro.</div>';
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==401)
            {
				document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Acesso n&atilde;o autorizado. Contate o administrador do sistema e informe o erro.</div>';
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==403)
            {
				document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">N&atilde;o podemos dar continuidade. Contate o administrador do sistema e informe o erro.</div>';
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==404)
            {
                document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">P&aacute;gina n&atilde;o encontrada. Contate o administrador do sistema e informe o erro.</div>';
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==500)
            {
				document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Erro interno do servidor. Contate o administrador do sistema e informe o erro.</div>';
            }
            else if (xmlhttp.readyState==4 && xmlhttp.status==503)
            {
				document.getElementById(local).innerHTML='<div style="font-family:verdana;font-weight:bold;font-size:12px;color:#666666;" align="center" valign="middle">Servi&ccedil;o indispon&iacute;vel, verifique sua conex&atilde;o. Contate o administrador do sistema e informe o erro.</div>';
            }
		}
    }
    
    xmlhttp.open("POST",url,true);
    xmlhttp.send();
}

function exeJS(txt)
{
	var ini=0;
	while(ini!=-1)
	{
		ini=txt.indexOf('<script',ini);
		if(ini>=0)
		{
			ini=txt.indexOf('>',ini)+1;
			var fim = txt.indexOf('</script>',ini);
			codigo=txt.substring(ini,fim);
			//eval(codigo);
			var head	= document.getElementsByTagName('head').item(0);
			var eScript	= document.createElement('script');
			eScript.setAttribute('type','text/javascript'); 
			eScript.setAttribute('charset','utf-8');
			eScript.text=codigo;
			head.appendChild(eScript);
		}
	}
}

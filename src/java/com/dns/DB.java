/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dns;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jefaokpta
 */
public class DB {
    
    public String login(String user,String pass){
        String res = null;
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select count(name) from users "
                    + "where name='"+user+"' and password='"+pass+"'");
            if(db.resultado.next())
                if(db.resultado.getString(1).equals("1"))
                    res="ok";
                else
                    res="Usuário ou senha incorretos!";
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String listDnss(){
        DBConnect db=new DBConnect();
        String qtde,res="<table class=\"span11 table-striped table-condensed table-hover table-bordered\">"
                + "<tr><th>Código</th><th>Cliente</th><th>IP</th><th>Host</th><th>Ações</th><th></th></tr>";
        try {
            db.resultado=db.Comando.executeQuery("select iddnss,name,ip,host from dnss");
            while(db.resultado.next()){
                res+="<tr><td>"+db.resultado.getString(1)+"</td>"
                        + "<td>"+db.resultado.getString(2)+"</td>"
                        + "<td>"+db.resultado.getString(3)+"</td>"
                        + "<td>"+db.resultado.getString(4)+"</td>"
  
                        + "<td><a rel=\"tooltip\" data-placement=\"left\" title=\"Editar DNS\" href=\"editDns.jsp?dns="+db.resultado.getString(1)+"\" >Editar</a></td> "
                        + "<td><a rel=\"tooltip\" data-placement=\"right\" title=\"Apagar DNS\" href=\"#\" onclick=\"deleteDns('"+db.resultado.getString(2)+"',"+db.resultado.getString(1)+");\" >Apagar</a></td>"
                        + "</tr>";
            }
            res+="</table>";//"+getPickupPeers(db.resultado.getString(1))+"

        } catch (SQLException ex) {
            res="Falha na lista: "+ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String listServers() throws IOException{
        DBConnect db=new DBConnect();
        String qtde,res="<table class=\"span11 table-striped table-condensed table-hover table-bordered\">"
                + "<tr><th>Código</th><th>Servidor</th><th>IP</th><th>Status</th><th>Ações</th><th></th></tr>";
        try {
            db.resultado=db.Comando.executeQuery("select idservers,name,ip from servers order by name");
            while(db.resultado.next()){
                String status="<img src=\"webs/bootstrap/img/red-signals.png\">",ls_str;
                Process ls_proc=Runtime.getRuntime().exec("ping -w1 -c1 "+db.resultado.getString(3));
                DataInputStream ls_in = new DataInputStream(ls_proc.getInputStream());
		while ((ls_str = ls_in.readLine()) != null) {
                   if(ls_str.contains("icmp")){
                       status="<img src=\"webs/bootstrap/img/green-signals.png\">";
                       break;
                   }
		}
                res+="<tr><td>"+db.resultado.getString(1)+"</td>"
                        + "<td>"+db.resultado.getString(2)+"</td>"
                        + "<td>"+db.resultado.getString(3)+"</td>"
                        + "<td>"+status+"</td>"
  
                        + "<td><a rel=\"tooltip\" data-placement=\"left\" title=\"Editar Server\" href=\"editServer.jsp?server="+db.resultado.getString(1)+"\" >Editar</a></td> "
                        + "<td><a rel=\"tooltip\" data-placement=\"right\" title=\"Apagar Server\" href=\"#\" onclick=\"deleteServer('"+db.resultado.getString(2)+"',"+db.resultado.getString(1)+");\" >Apagar</a></td>"
                        + "</tr>";
            }
            res+="</table>";//"+getPickupPeers(db.resultado.getString(1))+"

        } catch (SQLException ex) {
            res="Falha na lista: "+ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String createServer(String name,String ip){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("insert into servers (name,ip) values ('"+name+"','"+ip+"')");
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String writeServers(){
        String res="ok";
        File makefile=new File("webapps/dns/arquivos/vcomsolucoes.wan.servidores");
        DBConnect db=new DBConnect();
        try {   
            FileWriter fwrite=new FileWriter(makefile);
            db.resultado=db.Comando.executeQuery("select name,ip from servers");
            while(db.resultado.next()){
                fwrite.write(db.resultado.getString("name")+"\tIN\tA\t"+db.resultado.getString("ip")+"\n");
                fwrite.write("\n");
            }
            fwrite.flush();
            fwrite.close();
            db.disconnectDB();
        }catch(Exception ex){
            res=ex.getMessage();
        }
        return res;
    }
    public String writeDns(){
        String res="ok";
        File makefile=new File("webapps/dns/arquivos/vcomsolucoes.wan.dns");
        DBConnect db=new DBConnect();
        try {   
            FileWriter fwrite=new FileWriter(makefile);
            db.resultado=db.Comando.executeQuery("select description,serverdns from dnss");
            while(db.resultado.next()){
                fwrite.write(db.resultado.getString("description")+"\tIN\tCNAME\t"+db.resultado.getString("serverdns")+"\n");
                fwrite.write("\n");
            }
            fwrite.flush();
            fwrite.close();
            db.disconnectDB();
        }catch(Exception ex){
            res=ex.getMessage();
        }
        return res;
    }
    String deleteServer(String id){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("delete from servers where idservers="+id);
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        writeServers();
        return res;
    }
    public String[] editServer(String id){
        String res[]=new String[3];
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select idservers,name,ip from servers where idservers="+id);
            if(db.resultado.next()){
                res[0]=db.resultado.getString(1);
                res[1]=db.resultado.getString(2);
                res[2]=db.resultado.getString(3);
            }
        } catch (SQLException ex) {
            res[0]=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String alterServer(String id,String name,String ip){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("update servers set name='"+name+"',ip='"+ip+"' "
                    + "where idservers="+id);
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        writeServers();
        return res;
    }
    String createClient(String name){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("insert into clients (name) values ('"+name+"')");
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String listClients(){
        DBConnect db=new DBConnect();
        String qtde,res="<table class=\"span11 table-striped table-condensed table-hover table-bordered\">"
                + "<tr><th>Código</th><th>Cliente</th><th>DNS</th><th>Ações</th><th></th></tr>";
        try {
            db.resultado=db.Comando.executeQuery("select idclients,name from clients order by name");
            while(db.resultado.next()){
                res+="<tr><td>"+db.resultado.getString(1)+"</td>"
                        + "<td>"+db.resultado.getString(2)+"</td>"
                        + "<td>"+viewDns(db.resultado.getString(1))+"</td>" 
                        + "<td><a rel=\"tooltip\" data-placement=\"left\" title=\"Editar Cliente\" href=\"editClient.jsp?dns="+db.resultado.getString(1)+"&name="+db.resultado.getString(2)+"\" >Editar</a></td> "
                        + "<td><a rel=\"tooltip\" data-placement=\"right\" title=\"Apagar Cliente\" href=\"#\" onclick=\"deleteClient('"+db.resultado.getString(2)+"',"+db.resultado.getString(1)+");\" >Apagar</a></td>"
                        + "</tr>";
            }
            res+="</table>";//"+getPickupPeers(db.resultado.getString(1))+"

        } catch (SQLException ex) {
            res="Falha na lista: "+ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String optionServer(){
        String res="";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select name,ip from servers");
            while(db.resultado.next()){
                res+="<option value=\""+db.resultado.getString(1)+"\">"+db.resultado.getString(1)+"</option>";
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String optionServerSelected(String select){
        String res="";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select name,ip from servers");
            while(db.resultado.next()){
                res+="<option "+(db.resultado.getString(1).equals(select)?"selected=\"\"":"")+" "
                        + "value=\""+db.resultado.getString(1)+"\">"+db.resultado.getString(1)+"</option>";
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String getIdClient(String name){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select idclients from clients where name='"+name+"'");
            while(db.resultado.next()){
                res=db.resultado.getString(1);
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String createDns(String idclient,String desc,String server){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("insert into dnss (idclient,description,serverdns) values "
                    + "("+idclient+",'"+desc+"','"+server+"')");
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String deleteDns(String idclient){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("delete from dnss where idclient="+idclient);
            db.Comando.executeUpdate("delete from clients where idclients="+idclient);
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String viewDns(String id){
        String res="";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select description,serverdns from dnss "
                    + "where idclient="+id);
            while(db.resultado.next()){
                res+=""+db.resultado.getString(1)+" -> "+db.resultado.getString(2)+"<br>";
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String editDns(String client){
        String res="";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select description,serverdns from dnss "
                    + "where idclient="+client);
            int pos=0;
            while(db.resultado.next()){
                pos++;
                res+="<div id=\"dns"+pos+"\">"
                        + "<input style=\"float: left\" type=\"text\" value=\""+db.resultado.getString(1)+"\" name=\"desc"+pos+"\" id=\"desc"+pos+"\"></input>"
                        + "<select class=\"offset1\" id=\"serv"+pos+"\" name=\"serv"+pos+"\">"
                        + ""+optionServerSelected(db.resultado.getString(2))+""
                        + "</select>"
                        + "<a href=\"#\" onclick=\"addDesc();\"><img class=\"icon-plus-sign\" /></a>"
                        + "<a href=\"#\" onclick=\"delDesc('dns"+pos+"');\"><img class=\"icon-minus-sign\" /></a>"
                        + "</div>";
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    public String countDns(String client){
        String res="";
        DBConnect db=new DBConnect();
        try {
            db.resultado=db.Comando.executeQuery("select count(description) from dnss "
                    + "where idclient="+client);
            while(db.resultado.next()){
                res=db.resultado.getString(1);
            }
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    String deleteDnsToEdit(String idclient){
        String res="ok";
        DBConnect db=new DBConnect();
        try {
            db.Comando.executeUpdate("delete from dnss where idclient="+idclient);
        } catch (SQLException ex) {
            res=ex.getMessage();
        }
        db.disconnectDB();
        return res;
    }
    int reloadBind(String pass){
        File makeFile=new File("/tmp/reload.sh");
            FileWriter fwrite;
        try {
            fwrite = new FileWriter(makeFile);
            fwrite.write("#!/bin/bash\n");
            fwrite.write("echo '"+pass+"' | sudo -S -u root /etc/init.d/bind9 restart\n");
            fwrite.write("echo $?");
            fwrite.flush();
            fwrite.close();
            setPermissions();
            
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }    
        return execScript();
    }
    void setPermissions(){
        String res;
        try {   
            Runtime.getRuntime().exec("chmod a+x /tmp/reload.sh");
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
                
    }
    int execScript(){
        int res = 1;
        Process ls_proc;
        DataInputStream ls_in;
        String ls_str;
        try {   
            ls_proc = Runtime.getRuntime().exec("/tmp/reload.sh");
            ls_in = new DataInputStream(ls_proc.getInputStream());
            while ((ls_str=ls_in.readLine()) != null) {
                if(ls_str.length()==1)
                    res=Integer.parseInt(ls_str);
            }
           // removeSh();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        return res;
    }
    void removeSh(){
        int res = 1;
        try {   
            Runtime.getRuntime().exec("rm /tmp/reload.sh");          
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dns;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jefaokpta
 */
public class DBConnect {
    private String enderecoDB;
    private java.sql.Connection Conexao;
    java.sql.Statement Comando;
    java.sql.ResultSet resultado;
    
    public DBConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            enderecoDB="jdbc:mysql://localhost/dns";
            Conexao=DriverManager.getConnection(
                    enderecoDB,"vcom","vcomadmin");
            Comando=Conexao.createStatement();
           // java.sql.ResultSet resultado=Comando.executeQuery();
                        
         //   Comando.close();
         //   Conexao.close();

        }
        catch (Exception Excecao){
            System.out.println(Excecao);
        }
    }
    public void disconnectDB(){
        try {
            Comando.close();
            Conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
}

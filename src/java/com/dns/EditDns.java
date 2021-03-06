/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dns;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefaokpta
 */
public class EditDns extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DB db=new DB();
        String ret = "ok";
        ret=db.deleteDnsToEdit(request.getParameter("clientId"));
        String idClient=request.getParameter("clientId");
        try (PrintWriter out = response.getWriter()) {
            //out.print("Cliente "+request.getParameter("client"));
           // out.print("Cliente ID "+idClient);
            for(int i=1;i<=Integer.parseInt(request.getParameter("qtdePos"));i++){
                if(request.getParameter("desc"+i)!=null&&!request.getParameter("desc"+i).equals("")){
                    ret=db.createDns(idClient, request.getParameter("desc"+i), request.getParameter("serv"+i));
                    if(!ret.equals("ok")){
                        request.getSession().setAttribute("error", ret);
                        break;
                    }
                    //out.print("<br>dns "+request.getParameter("desc"+i)+" server "+request.getParameter("serv"+i));
                }
            }
            //out.print("<br>"+request.getParameter("qtdePos"));
            if(ret.equals("ok"))
                ret=db.writeDns();
            if(!ret.equals("ok"))
                request.getSession().setAttribute("error", ret);
            response.sendRedirect("home.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

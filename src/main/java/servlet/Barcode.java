/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cedric
 */
@WebServlet(urlPatterns={"/servlet/Barcode"})
public class Barcode extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Barcode(){
    super();
    }
    
    @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config); // indispensable !
        ServletContext sc = config.getServletContext(); 
        sc.log( "Demarrage servlet BarcodeServlet" );// Ecrit les informations fournies en paramètre dans le fichier log du serveur
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
        
            DonnerReponse(request,response);
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String msg = request.getParameter("code");
            String codeType = request.getParameter("codeType");
            String code = request.getParameter("msg");
            String fmt = request.getParameter("fmt");
            String qz = request.getParameter("qz");
            String height = request.getParameter("height");
            String type =request.getParameter("type");
            String width =request.getParameter("mw");
            String wf=request.getParameter("wf");
            String dpi =request.getParameter("dpi");
            String hrp =request.getParameter("human-readable-pos");
            String hrf =request.getParameter("human-readable-font");
            String hrs =request.getParameter("human-readable-size");
            String hrpat =request.getParameter("human-readable-pattern");
            String hist =request.getParameter("history");
            
            request.setAttribute("code", code);
            request.setAttribute("label", msg);
            request.setAttribute("fmt",fmt );
            request.setAttribute("qz",qz);
            request.setAttribute("height",height );
            request.setAttribute("type",type);
            request.setAttribute("mw",width);
            request.setAttribute("wf", wf);
            request.setAttribute("dpi",dpi);
            request.setAttribute("codeType",codeType);
            request.setAttribute("history",hist);
            
        // Validation EAN-13
        if ("ean-13".equals(type)) {

            if (code == null || !code.matches("\\d{12}")) {
                response.sendError(500,"EAN‑13 doit contenir exactement 12 chiffres. Exemple : 123456789012");
            } else {
                // Calcul automatique du checksum
                code = code + calculateEAN13Checksum(code);
            }
        }
        request.setAttribute("code", code);
        request.setAttribute("human-readable-pattern",hrpat);
        request.setAttribute("human-readable-font",hrf);
        request.setAttribute("human-readable-size",hrs);
        request.setAttribute("human-readable-pos",hrp);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
		dispatcher.forward(request,response);
    }

    public int calculateEAN13Checksum(String code12) {
        int sumOdd = 0;
        int sumEven = 0;

        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(code12.charAt(i));

            if ((i % 2) == 0) {
                sumOdd += digit;
            } else {
                sumEven += digit;
            }
        }

        int total = sumOdd + (sumEven * 3);
        return (10 - (total % 10)) % 10;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            this.doGet(request,response);
}
  
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
}
 
   protected void DonnerReponse(HttpServletRequest request, HttpServletResponse reponse) 
        throws IOException {
 
      reponse.setContentType("text/html");
      PrintWriter out =reponse.getWriter();
      out.println("<html>");
      out.println("<body>");
      out.println("<head>");
      out.println("<title>Informations a disposition de la servlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<p>Type mime de la requête :"
        +request.getContentType()+"</p>");
      out.println("<p>Protocole de la requête :"
        +request.getProtocol()+"</p>");
      out.println("<p>Adresse IP du client :"
        +request.getRemoteAddr()+"</p>");
      out.println("<p>Nom du client : "
        +request.getRemoteHost()+"</p>");
      out.println("<p>Nom du serveur qui a reçu la requête :"
        +request.getServerName()+"</p>");
      out.println("<p>Port du serveur qui a reçu la requête :"
        +request.getServerPort()+"</p>");
      out.println("<p>scheme: "+request.getScheme()+"</p>");
      out.println("<p>liste des paramètres </p>");
      out.println("</body>");
      out.println("</html>");
   }
           
            }
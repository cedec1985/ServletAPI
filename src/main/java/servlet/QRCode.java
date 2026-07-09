/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns={"/servlet/QRCode"})
public class QRCode extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public QRCode(){
    super();
    }
    
    @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config); // indispensable !
        ServletContext sc = config.getServletContext(); 
        sc.log( "Demarrage servlet QRCodeServlet" );// Ecrit les informations fournies en paramètre dans le fichier log du serveur
   }
    
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
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QRCode</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QRCode at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       
        String msg=request.getParameter("msg");
        if(msg == null || msg.isEmpty()) msg = "EMPTY";
        int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size"):"250");
        request.setAttribute("size",size);
        request.setAttribute("msg",msg);
        response.setContentType("image/png");

        
        try{
            BitMatrix matrix = new MultiFormatWriter().encode(
            msg,
            BarcodeFormat.QR_CODE,
            size,
            size
            );
        
            MatrixToImageWriter.writeToStream(matrix, msg, response.getOutputStream());
        
        } catch (WriterException | IOException e){
            response.sendError(500, "erreur avec la génération du QR Code");
        }
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
        this.doGet(request, response);
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

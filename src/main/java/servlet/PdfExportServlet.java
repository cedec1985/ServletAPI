/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.net.URL;
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
@WebServlet(urlPatterns={"/servlet/PdfExportServlet"})
public class PdfExportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public PdfExportServlet(){
        super();
    }
      @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config); // indispensable !
        ServletContext sc = config.getServletContext(); 
        sc.log( "Demarrage servlet PdfExportServlet" );// Ecrit les informations fournies en paramètre dans le fichier log du serveur
   }

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
        
        String qrUrl = request.getParameter("QRCode");
        String eanUrl = request.getParameter("Barcode");
        request.setAttribute("qrUrl",qrUrl);
        request.setAttribute("eanUrl",eanUrl);
        String param = request.getQueryString();
        response.setHeader("Content-Disposition", "attachment; filename=codes.pdf");

        try {
            try (com.lowagie.text.Document pdf = new com.lowagie.text.Document()) {
                com.lowagie.text.pdf.PdfWriter.getInstance(pdf, response.getOutputStream());
                pdf.open();
                
                pdf.add(new com.lowagie.text.Paragraph("QR Code & EAN‑13"));
                pdf.add(new com.lowagie.text.Paragraph(" "));
                
                // Charger les images
                com.lowagie.text.Image qrImg = com.lowagie.text.Image.getInstance(new URL(param));
                com.lowagie.text.Image eanImg = com.lowagie.text.Image.getInstance(new URL(param));
                
                qrImg.scaleToFit(250, 250);
                eanImg.scaleToFit(250, 250);
                
                pdf.add(qrImg);
                pdf.add(new com.lowagie.text.Paragraph(" "));
                pdf.add(eanImg);
            }

        } catch (DocumentException | IOException e) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la génération du PDF.");
        }
         request.getRequestDispatcher("/result.jsp").forward(request, response);
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


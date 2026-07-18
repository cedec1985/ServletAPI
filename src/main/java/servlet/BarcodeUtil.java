/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author cedric
 */
@WebServlet(urlPatterns={"/servlet/BarcodeUtil"})
public class BarcodeUtil extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String barcodePath = "C:\\hello\\src\\main\\java\\images\\excel.png"; 
    @SuppressWarnings("unused")
    String barcodePath2 ="/images/out2.png";
   
 

    public BarcodeUtil(){
        super();
    }
      @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config); // indispensable !
        ServletContext sc = config.getServletContext(); 
        sc.log( "Demarrage servlet BarcodeUtil" );// Ecrit les informations fournies en paramètre dans le fichier log du serveur
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
            out.println("<title>Servlet BarcodeUtil</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BarcodeUtil at " + request.getContextPath() + "</h1>");
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
        
        String msg = request.getParameter("msg");
        
         try {
      // 1. Récupérer le message à encoder
       
      Code128Bean bean = new Code128Bean();
      final int dpi = 160;
      bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
      bean.doQuietZone(true);
      bean.setBarHeight(30);
      bean.setQuietZone(0.8);
    
      //Open output file
      File outputFile = new File(barcodePath);

      FileOutputStream out = new FileOutputStream(outputFile);
    
      BitmapCanvasProvider canvas = new BitmapCanvasProvider(
          out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

      //Generate the barcode
      bean.generateBarcode(canvas, msg);
   
      //Signal end of generation
      canvas.finish();
    }
    catch (IOException ex){
        
    }
        
        Code128Bean bean = new Code128Bean();
        int dpi = 150;

        // largeur du module (barre la plus fine)
        bean.setModuleWidth(0.2); // en mm
        bean.doQuietZone(true);

        // 3. Préparer la réponse HTTP
        response.setContentType("image/png");

        OutputStream out = response.getOutputStream();
        try {
            // 4. Canvas pour image bitmap
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/png", dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 5. Générer le code‑barres
            bean.generateBarcode(canvas, msg);

            // 6. Finaliser
            canvas.finish();
            request.setAttribute("msg",msg);
            
        }
        catch (IOException ex){
        
    }
      
          Code39Bean bean39 = new Code39Bean();
          dpi = 160;

          //Configure the barcode generator
          bean39.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

          bean39.doQuietZone(true);

          OutputStream out2 = response.getOutputStream();
          //Set up the canvas provider for monochrome PNG output
          BitmapCanvasProvider canvas = new BitmapCanvasProvider(
              out2, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

          //Generate the barcode
          bean39.generateBarcode(canvas, msg);
          //Signal end of generation
          canvas.finish();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        BarCodeUtil barCodeUtil = new BarCodeUtil();
    
        // This will generate Bar-Code 3 of 9 format
        barCodeUtil.createBarCode39("naeemgik - 12345");
    
      // This will generate Bar-Code 128 format
        barCodeUtil.createBarCode128("0123456789");
        
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


public class BarCodeUtil {

  String barCodePath = "images/out.png"; 
 
  
  public void createBarCode128(String fileName) {
    try {
      Code128Bean bean = new Code128Bean();
      final int dpi = 160;

      //Configure the barcode generator
      bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

      bean.doQuietZone(false);

      //Open output file
      File outputFile = new File(barCodePath);

      FileOutputStream out = new FileOutputStream(outputFile);
    
      BitmapCanvasProvider canvas = new BitmapCanvasProvider(
          out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

      //Generate the barcode
      bean.generateBarcode(canvas, fileName);
   
      //Signal end of generation
      canvas.finish();
    
      System.out.println("Bar Code is generated successfully…");
    }
    catch (IOException ex) {
    }
  
  }
  public void createBarCode39(String fileName) {

        try {
          Code39Bean bean39 = new Code39Bean();
          final int dpi = 160;

          //Configure the barcode generator
          bean39.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

          bean39.doQuietZone(false);

          //Open output file
          File outputFile = new File(barCodePath + fileName + ".JPG");
        
          FileOutputStream out = new FileOutputStream(outputFile);
        

          //Set up the canvas provider for monochrome PNG output
          BitmapCanvasProvider canvas = new BitmapCanvasProvider(
              out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

          //Generate the barcode
          bean39.generateBarcode(canvas, fileName);
       
          //Signal end of generation
          canvas.finish();
        
          System.out.println("Bar Code is generated successfully…");
        }
        catch (IOException ex) {
           
        }
  }
}
}



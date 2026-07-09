/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;


/**
 *
 * @author cedric
 */
@WebServlet(urlPatterns={"/servlet/Barcode"})
public class Barcode extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String barCodePath = "images/out.png";

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
        
            
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String[] codes = request.getParameterValues("codeTypes");
            String message = request.getParameter("msg");
           
            List<String> urls = new ArrayList<>();

            if (codes != null) {
            for (String c : codes) {
            switch (c) {
                case "ean":
                    // Validation EAN-13
            if ("ean-13".equals(codes[0])) {

            if (message == null || !message.matches("\\d{12}")) {
                request.setAttribute("error", 
                    "EAN‑13 doit contenir exactement 12 chiffres. Exemple : 123456789012");
            } else {
                // Calcul automatique du checksum
                message = message + calculateEAN13Checksum(message);
            }
            }
                    urls.add(request.getContextPath() + "/Barcode?msg="+ message +  "&type=ean-13");
                break;
                case "qr":
                    urls.add(request.getContextPath() + "/QRCode?msg="+ message + "&size=250"   + "&type=qrcode");
                break;
                case "code128":
                    urls.add(request.getContextPath() + "/Barcode?msg="+ message + "&type=code128");
                break;
                case "gs1-128":
                    urls.add(request.getContextPath() + "/Barcode?msg=" + URLEncoder.encode(message, "UTF-8")
                                + "&type=gs1-128");
                break;
                case "itf-14":
                    if ("itf-14".equals(codes[4])) {

            if (message == null || !message.matches("\\d{13}")) {
                request.setAttribute("error", 
                    "Erreur lors de la sélection du type de code barres.");
            } else {
                // Calcul automatique du checksum
                message = message + calculateITF14Checksum(message);
            }
            }
                    request.setAttribute("message", message);
                    urls.add(request.getContextPath() + "/Barcode?msg=" + message + "&type=itf-14");
                    break;
                    default:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la sélection du type de code barres.");

            }}}
            
                request.setAttribute("msg", message);      
                request.setAttribute("urls", urls);
                request.setAttribute("codes",codes);
                request.getRequestDispatcher("/result.jsp").forward(request, response);
  
          
            String msg = request.getParameter("code");
            String codeType = request.getParameter("codeType");
            String code = request.getParameter("msg");
            String fmt = request.getParameter("fmt");
            String qz = request.getParameter("qz");
            String height = request.getParameter("height");
            String type =request.getParameter("type");
            String width =request.getParameter("mw");
            String wf=request.getParameter("wf");
            String hrp =request.getParameter("human-readable-pos");
            String hrf =request.getParameter("human-readable-font");
            String hrs =request.getParameter("human-readable-size");
            String hrpat =request.getParameter("human-readable-pattern");
            String hist =request.getParameter("history");
            
 
            Code128Bean bean = new Code128Bean();
            int dpi = 180;
            bean.setModuleWidth(0.3);
            bean.setHeight(15);
            bean.setQuietZone(2);
            bean.doQuietZone(true);

// GS1-128 automatique si ton message contient des AI
            String gs1 = "(00)" + msg;  // ou "(01)12345678901234(17)260731"
            
//Open output file
      File outputFile = new File(barCodePath);

      FileOutputStream out = new FileOutputStream(outputFile);
    
      BitmapCanvasProvider canvas = new BitmapCanvasProvider(
          out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            bean.generateBarcode(canvas, gs1);
            canvas.finish();
           
      System.out.println("Bar Code is generated successfully…");


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
            request.setAttribute("human-readable-pattern",hrpat);
            request.setAttribute("human-readable-font",hrf);
            request.setAttribute("human-readable-size",hrs);
            request.setAttribute("human-readable-pos",hrp);
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
    
    public int calculateITF14Checksum(String code13) {
    int sumOdd = 0;
    int sumEven = 0;

    for (int i = 0; i < 13; i++) {
        int digit = Character.getNumericValue(code13.charAt(i));

        if ((i % 2) == 0) {
            sumOdd += digit;
        } else {
            sumEven += digit;
        }
    }

    int total = sumOdd + (sumEven * 3);
    return (10 - (total % 10)) % 10;
}
    
public static String generateSSCC(String base17) {
    if (base17 == null || base17.length() != 17 || !base17.matches("\\d+")) {
        throw new IllegalArgumentException("SSCC must be 17 digits");
    }

    int sum = 0;
    boolean weightThree = true; // start from right with weight 3

    for (int i = base17.length() - 1; i >= 0; i--) {
        int digit = Character.getNumericValue(base17.charAt(i));
        sum += digit * (weightThree ? 3 : 1);
        weightThree = !weightThree;
    }

    int checkDigit = (10 - (sum % 10)) % 10;

    return base17 + checkDigit;
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
 
   
           
            }

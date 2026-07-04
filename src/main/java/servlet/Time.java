package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Time
 */
@WebServlet(urlPatterns={"/servlet/Time"})
public class Time extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Time() {
        super();
    }
	/**
     * @throws javax.servlet.ServletException
	 * @see Servlet#init(ServletConfig)
	 */
        @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config); // indispensable !
        ServletContext sc = config.getServletContext(); 
        sc.log( "Demarrage servlet TimeServlet" );// Ecrit les informations fournies en paramètre dans le fichier log du serveur
   }
   
    // ton code ici
	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
                DonnerReponse(request,response); // appel à la méthode
		response.setCharacterEncoding("utf-8");
                response.setContentType("text/html");
                DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.LONG);
                Cookie monCookie = new Cookie("nom","valeur");
                response.addCookie(monCookie);
                request.setAttribute("monCookie",monCookie);
                request.setAttribute("dateInstance",dateInstance);
		// Forward vers la JSP
		// Données envoyées à la JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/time.jsp");
		dispatcher.forward(request,response);
}
        
	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                  
                this.doGet(request, response);
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


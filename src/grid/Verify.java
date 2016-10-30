package grid;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verify
 */
@WebServlet(description = "verifies message", urlPatterns = { "/Verify" })
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		HttpSession session = request.getSession();
		Service serviceConn = null;	

		if(session.getAttribute("username")==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
		
		String cont = (String)session.getAttribute("cont");
	    
		serviceConn = Service.getInstance();
		ArrayList<Item> trans = new ArrayList<Item>();
		
		String trans_res = serviceConn.sendTrans(session.getAttribute("username").toString(),cont, request.getRemoteHost(),request.getRemoteAddr());     
        trans = serviceConn.getTrans(session.getAttribute("username").toString());
        
        request.setAttribute("message", trans_res);        
        
		request.setAttribute("button_text", "Logout");
		request.setAttribute("button_type", "submit");	
		request.setAttribute("list", trans);
		request.getRequestDispatcher("/list.jsp").forward(request, response);	
		
		}
	}

}

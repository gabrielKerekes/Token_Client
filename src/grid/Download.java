package grid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class Download
 */
@WebServlet(description = "stiahnutie suboru s vygenerovanym grid suborom", urlPatterns = { "/Download" })
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		super.init();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean is_token = (boolean)session.getAttribute("token");
		
		Service serviceConn = null;		
		
		String uname = (String)session.getAttribute("username");
	    
		serviceConn = Service.getInstance();
			
		boolean reg_res = serviceConn.isRegFinished(uname);  
		
		if(!is_token){
        	if(reg_res)request.setAttribute("message", "Registration complete");
        	else request.setAttribute("message", "Registration failed");
		}else{			
			if(reg_res)request.setAttribute("message", "Recovery complete");
        	else request.setAttribute("message", "Recovery failed");
		}
		
		session.removeAttribute("username");
	    session.invalidate();
 
		request.getRequestDispatcher("/result.jsp").forward(request, response);	
		
	}

}

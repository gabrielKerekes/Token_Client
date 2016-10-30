package grid;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
        
	 	HttpSession session = request.getSession();
	 	String un = session.getAttribute("username").toString();
	 	String s_code = session.getAttribute("s_code").toString();
	 	boolean is_token = (boolean)session.getAttribute("token");
	 	
	 	Service serviceConn = Service.getInstance();
	 	
	 	ArrayList<String> messages = new ArrayList<String>();
	       
	    if(request.getParameter("button").equals("Next")){
	    	if( code.trim().isEmpty() ){
				messages.add("Insert code!");
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("/code.jsp").forward(request, response);
			}else{
				if(code.equals(s_code)){
					if(!is_token){
						session.setAttribute("username", un);   	
						request.setAttribute("username", un);   
						session.setAttribute("recover", true);
						request.getRequestDispatcher("/account.jsp").forward(request, response);
					}
					else{
						String pin = session.getAttribute("pin").toString();
						
						serviceConn.writePin(un+"="+pin);
						serviceConn.request_mess(un);
						request.getRequestDispatcher("/download_link.jsp").forward(request, response);
					}
				}else{
					messages.add("Wrong code!");
					request.setAttribute("messages", messages);
					request.getRequestDispatcher("/code.jsp").forward(request, response);
				}				
			}
	    }else{
	    	request.getRequestDispatcher("/recovery.jsp").forward(request, response);
	    }
	}

}

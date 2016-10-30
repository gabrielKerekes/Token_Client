package grid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class List
 */
@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
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
		HttpSession session = request.getSession();
		Service serviceConn = null;				

		if(session.getAttribute("username")==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
	    
		serviceConn = Service.getInstance();
	     		
		if(request.getParameter("button").equals("Message")){
	        
	        String cont = request.getParameter("push_message"); //obsah transakcie
	        
	        session.setAttribute("cont", cont);
	        request.getRequestDispatcher("/verify.jsp").forward(request, response);
	        
		}
		else if(request.getParameter("button").equals("Account Settings")){
			session.setAttribute("recover", false);
			request.getRequestDispatcher("/account.jsp").forward(request, response);
		}
		else{
			
			//presun na login page a nastavenie cisla gridu, ktory bude treba zadat
			String code = get_code(); 

			session.setAttribute("grid_num", code);
		    session.setAttribute("serviceConn", serviceConn);
		    
		    session.removeAttribute("username");
		    session.removeAttribute("logged_username");
		    session.invalidate();
			
			request.setAttribute("which_grid", code);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}  
		}
	}
	
private String get_code(){
		
		String num=" ";
		int c=0,i=10;
	               
		while(i==10){
			i = (int)(Math.random() * 10);         
		}
		i = (i%5)+1;
	               
		c = (Math.random() <= 0.5) ? 0 : 1;
		c += 65;
	               
		num = Character.toString((char) c) + Integer.toString(i);
	               
		return num;
	} 

}

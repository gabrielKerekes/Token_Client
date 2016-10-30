package grid;


import grid.Item;
import grid.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Recovery
 */
@WebServlet("/Recovery")
public class Recovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recovery() {
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
		String un = request.getParameter("username");
               
	 	HttpSession session = request.getSession();
	 	ArrayList<String> messages = new ArrayList<String>();
	 	
	 	Service service = Service.getInstance();
	 	boolean is_token = (boolean)session.getAttribute("token");
	 	
	    if(is_token){
	    	String pin = request.getParameter("pin");
	    	
		    if(request.getParameter("button").equals("Next")){
		    	if( un.trim().isEmpty() ){
					messages.add("User Name is required!");
					request.setAttribute("messages", messages);
					request.getRequestDispatcher("/recovery.jsp").forward(request, response);
				}else if(pin.trim().isEmpty()){
					messages.add("PIN is required!");
					request.setAttribute("messages", messages);
					request.getRequestDispatcher("/recovery.jsp").forward(request, response);
				}else{
					String code = service.recovery(un);
					if(code.equals("Error")){
						messages.add("Wrong Code!");
						request.setAttribute("messages", messages);
						request.getRequestDispatcher("/recovery.jsp").forward(request, response);
					}
					else{		
						session.setAttribute("pin", pin);
						session.setAttribute("username", un);   
						session.setAttribute("s_code", code);   
						request.setAttribute("username", un);   
						request.getRequestDispatcher("/code.jsp").forward(request, response);
					}
				}
		    }else{
		    	String code = get_code(); 

				session.setAttribute("grid_num", code);
					
				request.setAttribute("which_grid", code);
		    	request.getRequestDispatcher("/login.jsp").forward(request, response);
		    }
	    }
	    else{
		    if(request.getParameter("button").equals("Next")){
		    	if( un.trim().isEmpty() ){
					messages.add("User Name is required!");
					request.setAttribute("messages", messages);
					request.getRequestDispatcher("/recovery.jsp").forward(request, response);
				}else{
					String code = service.recovery(un);
					if(code.equals("Error")){
						messages.add("Wrong COde!");
						request.setAttribute("messages", messages);
						request.getRequestDispatcher("/recovery.jsp").forward(request, response);
					}
					else{				
						session.setAttribute("username", un);   
						session.setAttribute("s_code", code);   
						request.setAttribute("username", un);   
						request.getRequestDispatcher("/code.jsp").forward(request, response);
					}
				}
		    }else{
		    	String code = get_code(); 

				session.setAttribute("grid_num", code);
					
				request.setAttribute("which_grid", code);
		    	request.getRequestDispatcher("/login.jsp").forward(request, response);
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

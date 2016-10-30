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
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
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
		
        String pw = request.getParameter("password");
        String new_pw = request.getParameter("new_password");
        String new_rep_pw = request.getParameter("repeat_password");
        String mail = request.getParameter("mail");
        String repeat_mail = request.getParameter("repeat_mail");
        String pin = request.getParameter("pin");
        
        String msg = " ";
        
        HttpSession session = request.getSession();        

    	if(session.getAttribute("username")==null){
    		request.getRequestDispatcher("/index.jsp").forward(request, response);
    	}else{
        
        String username = (String)session.getAttribute("username");
        boolean recover = (boolean)session.getAttribute("recover");
        Service serviceConn = null;		
	    
		serviceConn = Service.getInstance();
        
        
        if(request.getParameter("button").equals("Back")){
        	
        	if(recover){
        		session.removeAttribute("username");
    		    session.invalidate();
    			
    			request.getRequestDispatcher("/index.jsp").forward(request, response);
        	}else{
	        	ArrayList<Item> trans = new ArrayList<Item>();
		        
		        trans = serviceConn.getTrans(session.getAttribute("username").toString());
		        
		        request.setAttribute("message", "Send Transaction");
				request.setAttribute("button_text", "Logout");
				request.setAttribute("button_type", "submit");	
				request.setAttribute("list", trans);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
        	}
        
        }else if(request.getParameter("button").equals("Change password")){
        	
        	if(new_pw.equals(new_rep_pw)){
        		serviceConn.changeAccountSett(username,"password", new_pw, pw);
        	}
        	else{
        		request.setAttribute("messages_a", "Passwords do not match!");
        	}
        	
        	request.getRequestDispatcher("/account.jsp").forward(request, response);
        }
        else if(request.getParameter("button").equals("Change mail")){
        	
        	if(mail.equals(repeat_mail)){
        		
        		if(mail.contains("@") && mail.contains(".")){
        			serviceConn.changeAccountSett(username,"mail", mail," ");
        		}
        		else request.setAttribute("messages_a", "Wrong Mail Format!");
        		
        	}else{        		
        		request.setAttribute("messages_a", "Mails do not match!");
        	}
        	
        	request.getRequestDispatcher("/account.jsp").forward(request, response);
        }
        else if(request.getParameter("button").equals("Change PIN")){
        	
        	serviceConn.changeAccountSett(username,"pin", pin, " ");

        	request.getRequestDispatcher("/account.jsp").forward(request, response);
        }
		
	}
	}

}

package grid;

import java.io.IOException;
 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(description = "Login Servlet", urlPatterns = { "/login" })
public class Login extends HttpServlet {
       
    private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }
 
	public void init(ServletConfig config) throws ServletException {
               
	}
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	
		String un = request.getParameter("username");
        String pw = request.getParameter("password");
        String grid_code = request.getParameter("grid_code");
        String otp = request.getParameter("otp_code");
               
        //ziskanie hodnoty grid_num zo session
	 	HttpSession session = request.getSession();
	    String grid_num = (String)session.getAttribute("grid_num");
	    Service serviceConn = Service.getInstance();
	    
	    ArrayList<Item> trans = new ArrayList<Item>();
	    
	    session.removeAttribute("username");
	    
	    if(session.getAttribute("logged_username")!=null){
	    	request.setAttribute("message", "There is user logged in!"); 
	    	request.getRequestDispatcher("/result.jsp").forward(request, response);	
		}else{			
		    if(request.getParameter("button").equals("Login")){
		    	
		    	String res = serviceConn.getCreaTs(un);
		    	
		    	if(res.equals("out")){
					request.getRequestDispatcher("/outofdate.jsp").forward(request, response);	
				}else{
			                           
				String msg = " ";
				String button_text = " ";
				String button_type = " ";
				
				String ret = serviceConn.getLoginStat(un, pw, grid_code, grid_num, otp);
			               
				//nastavenie msg podla uspesneho/neuspesneho prihlasenia
				if(ret.equals("succes")){     
					msg="Welcome " + un;
					button_text = "Logout";
			     	button_type = "submit";
					session.setAttribute("logged_username", un);
					session.setAttribute("username", un);
					trans = serviceConn.getTrans(un);

					request.setAttribute("message", msg);
					request.setAttribute("button_text", button_text);
					request.setAttribute("button_type", button_type);	
					request.setAttribute("list", trans);
					request.getRequestDispatcher("/list.jsp").forward(request, response);	
				}
			 	else{
			 		if(!ret.equals("failed")){
	        			//error messages
	    		        request.setAttribute("messages", new ArrayList<String>(Arrays.asList(ret.split("-"))));
				 		String code = get_code(); 

						session.setAttribute("grid_num", code);
						session.setAttribute("serviceConn", serviceConn);
							
						request.setAttribute("which_grid", code);
						request.setAttribute("message", msg);
	    				request.getRequestDispatcher("/login.jsp").forward(request, response);
	        		}
	        		else{
				 		msg = "Login failed!";

				 		request.setAttribute("message", msg);
						request.getRequestDispatcher("/logout.jsp").forward(request, response);	
	        		}
				}
			                
				}
			}
		    else if(request.getParameter("button").equals("Restore")){
		    	session.setAttribute("token", false);
		    	request.getRequestDispatcher("/recovery.jsp").forward(request, response);	
		    	
			}
		    else if(request.getParameter("button").equals("Token")){
		    	session.setAttribute("token", true);
		    	request.getRequestDispatcher("/token_recovery.jsp").forward(request, response);	
		    	
			}else{
				request.setAttribute("message"," ");
		    	request.getRequestDispatcher("/reg_form.jsp").forward(request, response);
		    }
		}
	}
	
	//metoda na nahodne vygenerovanie pozicie na GRID karte
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

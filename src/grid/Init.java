package grid;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(description = "Initi required grid number", urlPatterns = { "/init" })
public class Init extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    public Init() {
        super();
    }
 
    public void init(ServletConfig config) throws ServletException {
	}
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Service serviceConn = null;		
	    
		serviceConn = Service.getInstance();
			
		//presun na login page a nastavenie cisla gridu, ktory bude treba zadat
		String code = get_code(); 

		session.setAttribute("grid_num", code);
		session.setAttribute("serviceConn", serviceConn);
			
		request.setAttribute("which_grid", code);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
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

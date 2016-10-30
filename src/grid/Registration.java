package grid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

/**
 * Servlet implementation class Registration
 */
@WebServlet(description = "registracia noveho uzivatela", urlPatterns = { "/Registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private String message;
    private List<String> messages;
    private String[] grid_card;
	
    public Registration() {
        super();
        message = " ";
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
		
		//nacitanie zadaych dat
		String un = request.getParameter("username");
        String pw = request.getParameter("password");
        String rep_pw = request.getParameter("repeat_password");
        String mail = request.getParameter("mail");
        String repeat_mail = request.getParameter("repeat_mail");
        String pin = request.getParameter("pin");
        
        String msg = " ";
        
        HttpSession session = request.getSession();
        Service serviceConn = (Service)session.getAttribute("serviceConn");
        
        if(request.getParameter("button").equals("OK")){
        	
        	String ret = serviceConn.register(un, pw, rep_pw, mail, repeat_mail, pin);
        	String[] data = ret.split("-");

        	if(data[0].equals("succes")){
        		//uspesna registracia
        		session.setAttribute("token", false);
        		session.setAttribute("username", un);
	    		request.getRequestDispatcher("/download_link.jsp").forward(request, response);
        	}
        	else{
        		//zle vyplnene polia
        		if(!data[0].equals("failed")){
        			//error messages
    		        request.setAttribute("messages", new ArrayList<String>(Arrays.asList(data)));
    		        
    		        //vypln parametre
    		        request.setAttribute("username", un);
    		        request.setAttribute("password", pw);
    		        request.setAttribute("repeat_password", rep_pw);
    		        request.setAttribute("mail", mail);
    		        request.setAttribute("repeat_mail", repeat_mail);
    		        request.setAttribute("pin",pin);
    		        
    				request.getRequestDispatcher("/reg_form.jsp").forward(request, response);
        		}
        		else{
        			//neuspesna registracia - TODO: moze byt aj chyba pripojenia nie len ze user existuje
        			messages = new ArrayList<String>();
        			messages.add("User already exists!");
			        request.setAttribute("messages", messages);
					request.getRequestDispatcher("/reg_form.jsp").forward(request, response);
        		}
        	}
        }
        else{
        	request.setAttribute("which_grid", get_code());
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
         
	}
	
	//vytvorenie grid kodu
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


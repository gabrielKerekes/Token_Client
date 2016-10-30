package grid;

import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class Service {
	
	private static Service service = null;
	
	private static String ADDRESS = "147.175.98.16";
	
	private static ClientConfig config = new DefaultClientConfig();

    private static Client client;     
    private static AtomicLong mess_counter = new AtomicLong();  
    private static WebResource target;
    
    public Service(){
    	
    	HostnameVerifier hv = getHostnameVerifier();    	
        SSLContext ctx =  this.getSSLContext();
        
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                new HTTPSProperties(hv, ctx));
    	 
    	client = Client.create(config);
    	target =  client.resource("https://"+ADDRESS+":8443/testRest/rs/service");
    }
    
    public static Service getInstance(){
    	if(service == null){
    		mess_counter.set(1);
    		service = new Service();
    		return service;
    	}
    //	return null; Povodne !!!!!
    	return service;
    }
    
    private HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hv = new HostnameVerifier() {
 
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
 
        return hv;
    }
 
    private SSLContext getSSLContext(){
        SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        // set up a TrustManager that trusts everything
        try {
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
      
			} }, new SecureRandom());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return sslContext;
    }
    
    public ArrayList<Item> getTrans(String user){
    	
    	ArrayList<Item> items = new ArrayList<Item>();
    	
    	String list = target.path("/getTrans").queryParam("user", user).accept("text/plain").get(String.class);  
    	
    	if(!list.equals("err")){
    		String[] trans = list.split("=");
    	
    		for (String string : trans) {
				items.add(new Item(string));
			}
    	}
    	
    	return items;
    }
    
    public boolean isRegFinished(String uname){
    	boolean result = false;
    	String answer;
    	
    	answer = target.path("/getRegStatus").queryParam("user", uname).type("text/plain").get(String.class);
    	
    	if(answer.equals("suc")) result = true;
    	else{
    		ClientResponse resp = target.path("/deleteReg").type("text/plain").post(ClientResponse.class,uname);
    		result = false;
    	}
    	
    	return result;
    }
    
    public String sendTrans(String uname, String cont, String host, String address){
    	
    	String result = null;
    	String data="";
    	String datetime="";
    	String from ="";
    	String res = "";
    	String answer = "";
    	
    	DateFormat dateformat = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss");
    	Date date = new Date();
    	datetime = dateformat.format(date);
    	
    	try {
			if (host.equals(address)) {
				InetAddress addr = InetAddress.getByName(host);
				from = addr.getHostName();
			}
			
			if (InetAddress.getLocalHost().getHostAddress().equals(address)) {
				from = "Local Host";				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	String counter = Long.toString(mess_counter.incrementAndGet());    	
    	
    	res = target.path("/sendNot").queryParam("user", uname).queryParam("cont", cont).queryParam("counter", counter).type("text/plain").get(String.class); 
    	
    	if(res.equals("suc")){
    	
    		answer = target.path("/getAnswer").queryParam("user", uname).queryParam("counter", counter).type("text/plain").get(String.class);
    		if(!answer.equals("err")){
    	
    			data = uname+"="+cont+"#"+datetime+"#"+from+"#"+answer;
    	
    			ClientResponse resp = target.path("/writeTransDev").type("text/plain").post(ClientResponse.class,data);
    			if(resp.getStatus()==201){
    				if(answer.equals("1")) result = "Transaction Confirmed";
    				else result = "Transaction Rejected";
    			}
    			else result = "Transaction not sent due error";
    		}
    		else result = "Transaction not sent due error";
    	
    	}else result = "Transaction not sent due error";
    	
    	return result;
    }
    
    public String getLoginStat(String un, String pw, String grid_code, String grid_num, String otp) {
    	
    	ArrayList<String> messages = new ArrayList<String>();
    	boolean check = true;
    	String ret = null;
    	
    	if( un.trim().isEmpty() ){
			messages.add("User name is required!");
			check = false;
		}
		
		if (pw.trim().isEmpty()){
			messages.add("Password is required!");
			check = false;
		}
		
		if (grid_code.trim().isEmpty()){
			messages.add("GRID is required!");
			check = false;
		}
		
		if (otp.trim().isEmpty()){
			messages.add("One Time Password is required!");
			check = false;
		}
    	
		if(!check){
	    	for(int i=0;i<messages.size();i++){
				if(i == 0) ret=messages.get(i);
				else ret+="-"+messages.get(i);
			}
		}else{
	    	
	    	JSONObject json = new JSONObject();
	    	json.put("uname", un);
	    	json.put("pwd", pw);
	    	json.put("grid", grid_code);
	    	json.put("grid_num", grid_num);
	    	json.put("otp", otp);
	    	
	    	ClientResponse resp = target.path("/postLogin").type("application/json").post(ClientResponse.class,json.toJSONString());
	    	
	    	if(resp.getStatus()==201){
	    		ret = "succes";
	    	}else ret = "failed";
		}
    	
    	return ret;
	}
    
    public String register(String un, String pw, String rep_pw, String mail, String mail_repeat, String pin) {
    	
    	ArrayList<String> messages = new ArrayList<String>();
    	boolean check = true;
    	String ret = null;
    	
    	String data = " ";
    	
    	if( pw.trim().isEmpty() ){
			messages.add("Password is required!");
			check = false;
		}
		else if(pw.length()<6){
			messages.add("Password length<6!");
			check = false;
		}
		else if(pw.matches(".*[^\\s\\w].*")){
			messages.add("Password contains special character!");
			check = false;
		}
		else if(!(pw.matches(".*\\d.*") && pw.matches(".*[A-Z].*"))){
			messages.add("Password does not contain capital or number!");
			check = false;
		}
		else if(!pw.equals(rep_pw)){
			messages.add("Passwords do not match!");
			check = false;
		}
		
		if (un.trim().isEmpty()){
			messages.add("User name is required!");
			check = false;
		}
		
		if (mail.trim().isEmpty()){
			messages.add("Mail is required!");
			check = false;
		}	
		else if (!mail.contains("@")){
			messages.add("Wrong mail format");
			check = false;
		}
		else if (!mail.contains(".")){
			messages.add("Wrong mail format");
			check = false;
		}
		else if (!mail.equals(mail_repeat)){
			messages.add("Mails do not match!");
			check = false;
		}
		
		if (pin.trim().isEmpty()){
			messages.add("PIN is required!");
			check = false;
		}
		
		if(!check){
	    	for(int i=0;i<messages.size();i++){
				if(i == 0) ret=messages.get(i);
				else ret+="-"+messages.get(i);
			}
		}else{
		
	    	data = un+"-"+pw+"-"+rep_pw+"-"+mail+"-"+mail_repeat+"-"+pin;
	    	
	    	JSONObject json = new JSONObject();
	    	json.put("uname", un);
	    	json.put("pwd", pw);
	    	json.put("mail", mail);
	    	json.put("pin", pin);
	    	
	    	ClientResponse resp = target.path("/postRegister").type("application/json").post(ClientResponse.class,json.toJSONString());
	    	
	    	if(resp.getStatus()==201){
	    		ret = "succes-";
	    	}else ret = "failed-";
	    	
		}
		
    	return ret;
	}
    
    public void changeAccountSett(String user, String field, String value, String pass){
    	
    	String data = "";
    	
    	if(field.equals("password")){
    		 data = user+"="+field+"="+Integer.toString(value.hashCode())+"="+Integer.toString(pass.hashCode());
    	} else  data = user+"="+field+"="+value+"="+pass;
    	
    	String response = target.path("/changeAtt").type("text/plain").post(String.class,data);
    }    
    
    public String recovery(String user){
    	String code = target.path("/getRecovery").queryParam("user", user).accept("text/plain").get(String.class); 
    	
    	return code;
    }
    
    public boolean writePin(String data){
    	boolean result = false;
    	
    	ClientResponse resp = target.path("/writePIN").type("text/plain").post(ClientResponse.class,data);
    	
    	if(resp.getStatus()==201){
    		result = true;
    	}else result = false;
    	
    	return result;
    }

    public String getCreaTs(String user){
    	long crea_ts=0;
    	boolean is_ok = false;    	

		Date today = new Date();
		Long curr_ts = today.getTime();
    	
    	String res = target.path("/getTs").queryParam("user", user).queryParam("ts", Long.toString(curr_ts)).accept("text/plain").get(String.class); 
    	
    	if(res.equals("ok")){
    		is_ok = true;
    	}

    	
    	return res;
    }

	public void request_mess(String un) {
		ClientResponse resp = target.path("/reqMess").type("text/plain").post(ClientResponse.class,un);		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
}

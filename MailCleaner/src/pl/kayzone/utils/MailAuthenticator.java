package pl.kayzone.utils;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Stateless
//@EJB(name="java:global/MailAuthenticator", beanInterface=MailAuthenticatorInterface.class)
public class MailAuthenticator extends Authenticator implements Serializable, MailAuthenticatorInterface {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1678701365027318290L;
	private String usr;
	private String pass;
	public MailAuthenticator() {
		super();
	}
	
	public MailAuthenticator(String username, String pass) {
		super();
		this.usr = username;
		this.pass = pass;
	}

	public PasswordAuthentication getPassAuth() {
		 if ((this.usr != null ) && (this.usr.length()>0) && (this.pass !=null ) && (this.pass.length()>0) ) {
			 return new PasswordAuthentication(this.usr,this.pass);
		 }
		return null;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

}

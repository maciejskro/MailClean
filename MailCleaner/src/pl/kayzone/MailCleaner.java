package pl.kayzone;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.MessagingException;

import pl.kayzone.utils.MailReader;

@Named
@SessionScoped
public class MailCleaner  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7458833629820851878L;
	
	private MailReader mr = null;
	
	public MailCleaner() throws MessagingException {
		super();
		MailReader mr = new MailReader();
			
		System.out.println("inside construktor logcelaner" + " " + mr.getPropert("mail.port"));
	}
	
	public String getMailList() {
		
		return "index.xhtml";
	}

	public MailReader getMr() {
		return mr;
	}

	public void setMr(MailReader mr) {
		this.mr = mr;
	}

}

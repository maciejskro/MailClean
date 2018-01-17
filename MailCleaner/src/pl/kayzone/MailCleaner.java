package pl.kayzone;

import java.io.IOException;
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
	 * 
	 * @author smaciej
	 * 
	 */
	private static final long serialVersionUID = 7458833629820851878L;
	
	private MailReader mr = null;
	
	public MailCleaner() throws MessagingException, IOException {
		super();
		MailReader mr = new MailReader("/mailserver.properties");
		System.out.println("inside construktor logcelaner" + " " + mr.getPropert("mail.port"));
	}
	
	public String getMailList() {
		this.mr.getMailList(100);
		return "index.xhtml";
	}

	public MailReader getMr() {
		return mr;
	}

	public void setMr(MailReader mr) {
		this.mr = mr;
	}

}

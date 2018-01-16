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
	
	public MailCleaner() {
		super();
		//this.mr = new MailReader("kntnews1.kontri.pl" , "news@news.kontri.pl", "*yNPAoahT\\BA21", "imap", false);
		//this.mr = new MailReader("imap.mint.net.pl", "maciej.skrobiszewski@mint.net.pl" ,"pioRMZ25x", "imap", true );
		
		System.out.println("inside construktor logcelaner" + " " + mr.getPropert("mail.port"));
		try {
			//mr.getConnection();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

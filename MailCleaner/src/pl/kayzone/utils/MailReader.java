package pl.kayzone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/************************************
 * 
 * Read mail from given mailbox
 * @author smaciej
 *
 ***********************************/

@Path("/api")

public class MailReader {

	
	
	private Properties prop;
	private Folder emailFolder;
	public MailReader() {
		Properties p = new Properties();
		this.prop = p;
	}
	public MailReader(String path) throws IOException {
		this();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream in = cl.getResourceAsStream(path);
		System.out.println(in);
		this.prop.load(in);
		getConnectedFolder();
	}
	
	public String getPropert( String key ) {
		return this.prop.getProperty(key)	;	
	}
	
	public Folder getConnectedFolder() {
		Folder emailFolderObj = null;
		Store storeObj = null;
		
		
		MailAuthenticatorInterface mauth = new MailAuthenticator(prop.getProperty("mail.imap.user"), prop.getProperty("mail.pass"));
		//PasswordAuthentication pa = mauth.getPassAuth();
		System.out.println("port = " + this.getPropert("mail.imap.port") );
		Session emailSession = Session.getDefaultInstance(this.prop,null) ;
			                                    
		emailSession.setDebug(true);
		try {
			storeObj = emailSession.getStore("imap");
			//if ( getPropert("mail.imap.port") == "143") { System.out.println("port jest na 143") ; }
			storeObj.connect(getPropert("mail.imap.host"), getPropert("mail.imap.user"), mauth.getPassAuth().getPassword().toString());
			// Create folder object and open it in read-only mode
			
			if (getPropert("mail.store.protocol") == "pop3") {
				emailFolderObj = storeObj.getFolder("INBOX");
				emailFolderObj.open(Folder.READ_WRITE);
			} else if (getPropert("mail.store.protocol") == "imap"|| getPropert("mail.store.protocol") =="imaps" ) {
				System.out.println(storeObj);
				
				Folder [] f = storeObj.getDefaultFolder().list();
					for (Folder fd: f) {
						System.out.println(">>" + fd.getName());
					}
				emailFolderObj = storeObj.getFolder("INBOX");
				emailFolderObj.open(Folder.READ_WRITE);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		this.setEmailFolder(emailFolderObj);
		return emailFolderObj;
	}
	
	public Folder getEmailFolder() {
		return emailFolder;
	}
	public void setEmailFolder(Folder emailFolder) {
		this.emailFolder = emailFolder;
	}

	
	public void getMailList(int howmanymails ) {
		
		try {
			
			// Fetch messages from the folder and print in a loop
			Message[] messageobjs = this.getEmailFolder().getMessages();
			System.out.println ("ile message " + messageobjs.length);
			for (int i = 0, n = howmanymails; i < n; i++) {
				@SuppressWarnings("unused")
				Message indvidualmsg = messageobjs[i];
				
				//System.out.println("Printing individual messages from " + indvidualmsg.getReceivedDate().toString());
				//System.out.println("No# " + (i + 1));
				//System.out.println("Email Subject: " + indvidualmsg.getSubject());
				//System.out.println("Sender: " + indvidualmsg.getFrom()[i]);
				//System.out.println("Content: " + indvidualmsg.getContent().toString());
				//Flags deleted = new Flags("DELETED");
				
				//MailEntity me = new MailManager (indvidualmsg) ;
				
				//if (me != null ) {
					//this.mds = new MailStorager<MailEntityHeader>();
				//	this.mds.storeDocumnet();
				//}
				
				//if ( Arrays.asList(me.getMailjson().get("Recipients")).size() >0 ) {
				//	System.out.println(me.getMailjson().toString());
				//}
				
				

			}
			
			// Now close all the objects
			this.getEmailFolder().close(false);
			getConnectedFolder().getStore().close();
			
		} catch (NoSuchProviderException exp) {
			exp.printStackTrace();
		} catch (MessagingException exp) {
			exp.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String sayPlainTestHello() {
		 return "hello test";
	 }
}

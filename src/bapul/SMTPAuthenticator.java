package bapul;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	
//	private final String smtpId = "kang";
	private final String smtpId = "bapulmanagement@gmail.com";
//	private final String smtpPw = "1234";
	private final String smtpPw = "1q2w3e4r!@";
	
	public SMTPAuthenticator() {
		getPasswordAuthentication();
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(smtpId, smtpPw);
	}

}

package joshuaacademy.TestComponents;

import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void sendTestReport() {
		// Sender's and receiver's email

		final String fromEmail = "joshua.mendoza0824@gmail.com"; // your gmail
		final String appPassword = "sylz qszq dzlt vryl"; // app password
		String toEmail = "sogerokudo@gmail.com";

		// Gmail SMTP properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, appPassword);
			}
		});
		
		try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Automation Test Report");

            // HTML Email body with clickable link
            String htmlContent = "<h2>Test Automation Report</h2>" +
                    "<p>View the latest test report here:</p>" +
                    "<a href=\"" + "https://Sogero.github.io/AutomationCICD/index.html" + "\">Open Report in Browser</a>";

            msg.setContent(htmlContent, "text/html");
            Transport.send(msg);

            System.out.println("Email with report link sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }


	}

}

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
                    "<a href=\"" + "https://3dad-136-158-8-121.ngrok-free.app/job/SeleniumAutomationTest/ws/Reports/index.html" + "\">Open Report in Browser</a>";

            msg.setContent(htmlContent, "text/html");
            Transport.send(msg);

            System.out.println("Email with report link sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

//		try {
//			 Message message = new MimeMessage(session);
//		        message.setFrom(new InternetAddress(fromEmail));
//		        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//		        message.setSubject("Extent Test Report");
//
//		        // Text message
//		        MimeBodyPart textPart = new MimeBodyPart();
//		        textPart.setText("Hi team,\\n\\nClick the link below to view the Test Report in your browser \\n\\nhttps://Sogero.github.io/AutomationCICD/Reports/index.html");
//
//		        // ✅ HTML report attachment
//		        MimeBodyPart attachmentPart = new MimeBodyPart();
//		        String reportPath = System.getProperty("user.dir")+"\\Reports\\index.html";
//		        FileDataSource source = new FileDataSource(reportPath);
//		        attachmentPart.setDataHandler(new DataHandler(source));
//		        attachmentPart.setHeader("Content-Type", "text/html; charset=UTF-8");
//		        attachmentPart.setFileName("ExtentReport.html");
//
//		        // Combine parts
//		        MimeMultipart multipart = new MimeMultipart();
//		        multipart.addBodyPart(textPart);
//		        multipart.addBodyPart(attachmentPart);
//
//		        message.setContent(multipart);
//		        Transport.send(message);
//
//		        System.out.println("✅ Email with ExtentReport sent successfully!");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

	}

}

package net.javapractical.springboot.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.javapractical.springboot.model.Employee;
import net.javapractical.springboot.repository.EmployeeRepository;


 
@Service
public class EmployeeResignationService {
	
	private final String DOMAIN_NAME = "@dish.com";
	private static Session mailSession;
     
    @Autowired
    EmployeeRepository repository;
     
    public Employee getEmployeeById(Long employeeId, String employeeEmailId) throws Exception
    {
        Optional<Employee> employee = repository.findById(employeeId);
        String windowUser = System.getenv("USERNAME")+DOMAIN_NAME;
        System.out.println(" windows user :::::: "+windowUser);
         
        if(employee.isPresent()) {
        	Employee emp = employee.get();
        	if(employeeEmailId.equalsIgnoreCase(emp.getEmployeeEmailId()) && windowUser.equalsIgnoreCase("rohit@dish.com")) {
        		return emp;
        	}else {
        		throw new Exception("employee id and employee email id doesn't match");
        	}
            
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }
     
    public Employee updateResignationDetails(Employee entity) throws Exception
    {
        Optional<Employee> employee = repository.findById(entity.getEmployeeId());
         
        if(employee.isPresent())
        {
        	Employee newEntity = employee.get();
        	if(StringUtils.isEmpty(newEntity.getDateOfResignation())) {
        		Date currDate = new Date();
        	    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        	    
        	    Calendar c = Calendar.getInstance();
        	    c.add(Calendar.DATE, 60);
        	    
        	    newEntity.setDateOfResignation(dateFormat.format(currDate));
                newEntity.setLastWorkingDay(dateFormat.format(c.getTime()));
                newEntity.setResReason(entity.getResReason());
        	}else {
        		newEntity.setDateOfResignation("");
                newEntity.setLastWorkingDay("");
                newEntity.setResReason("");
        	}
            
            newEntity.setEmployeeId(entity.getEmployeeId());
            newEntity.setEmployeeEmailId(entity.getEmployeeEmailId());
            newEntity.setLevel(entity.getLevel());
            newEntity.setDesignation(entity.getDesignation());
            newEntity.setDateOfJoining(entity.getDateOfJoining());
 
            newEntity = repository.save(newEntity);
            
            String[] toEmails = {newEntity.getManagerEmailId()};
            // sendmail(toEmails, entity.getEmployeeEmailId(), "password");
            // or
            // sendmail(entity.getEmployeeEmailId(), newEntity.getManagerEmailId());
            
             System.out.println(" employee details updated :::::::: "+newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
    
    /*
	 * Following type of code used when anyone develop an application and want to check how email functionality works, lets say 
	 * he/she wants to use gmail for this then sender has to authenticate himself/herself first before sending any mail
	 */
    
    private static void sendmail(String[] toEmails, String fromEmails, String fromUserEmailPassword) throws MessagingException {
    	setMailServerProperties();
        sendEmailToSpecifiedPerson(toEmails, fromEmails, fromUserEmailPassword);
    }
    
    private static void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "586");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }
 
    private static MimeMessage draftEmailMessage(String[] toEmails, String fromEmail) throws MessagingException
    {
        String emailSubject = fromEmail+" has resigned today";
        String emailBody = "This is to inform you that employee"+fromEmail+ "has resigned from this organization, "
        		+ "please reach out to him/her for further details.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }
 
    private static void sendEmailToSpecifiedPerson(String[] toEmails, String fromUser, String fromUserEmailPassword) throws MessagingException
    {
        /**
         * Sender's credentials
         * */
 
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessage(toEmails, fromUser);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
    
	/*
	 * Following type of code used when anyone develop an application within their
	 * organization and implements email sending functionality
	 */
    
    private static void sendmail(String from, String to) throws MessagingException, IOException
    {
        String host = "your-company-host-server";
        String port ="host-port-number";

        // Getting system properties
        Properties properties = new Properties();

        // Setting up mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true"); //enable authentication
       

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("svc_compoffd", "Blue56Tested");
            }
        };

        Session session = Session.getDefaultInstance(properties,auth);

        MimeMessage msg = new MimeMessage(session);
            //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(from, "employee"));
        
        String emailSubject = from+" has resigned today";
        String emailBody = "This is to inform you that employee"+from+ "has resigned from this organization, "
        		+ "please reach out to him/her for further details.";

        msg.setSubject(emailSubject, "UTF-8");


        StringBuilder mailBody = new StringBuilder(emailBody);
       

        msg.setText(mailBody.toString(), "UTF-8", "html");
        

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        System.out.println("Message is ready");
        Transport.send(msg);

        System.out.println("EMail Sent Successfully!!");
    }
}
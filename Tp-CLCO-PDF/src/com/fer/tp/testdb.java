package com.fer.tp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

public class testdb {

	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
			Connection conn = null;
			// connects to the database
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/appdb", "root", "");

			// constructs SQL statement
		
			String sql = "select Email from  files_upload WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			 statement.setString(1, "14");
		
			// sends the statement to the database server
			ResultSet result1 = statement.executeQuery();
			// iterate through the java resultset
			while (result1.next()) {
				String EmailSend = result1.getString("Email");
				System.out.println("email:  " + EmailSend);
			}
			EmailAttachmentSender attachmentSender = new EmailAttachmentSender() ;
	        String[] attachFiles = new String[1];
	        attachFiles[0] = "D:\\my_fileClco.pdf";
	    
			try {
				attachmentSender.sendEmailWithAttachments("smtp.gmail.com","587", "dia.fergani@univ-constantine2.dz", 
						" ferganiramy1992", "moumenmadrid65@gmail.com", "convert", "New email with attachments", attachFiles);
				 System.out.println("Email sent.");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Could not send email.");
			}

	
	}

}

package com.fer.tp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.convertapi.Config;
import com.convertapi.ConversionResult;
import com.convertapi.ConvertApi;
import com.convertapi.Param;

@WebServlet("/Convert")
public class Convert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String dbURL = "jdbc:mysql://localhost:3306/appdb";
	private String dbUser = "root";
	private String dbPass = "";
	Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Convert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ccc cwoWord = new ccc();
		// System.out.println("Start2");
		// cwoWord.ConvertToPDF("D:\\SFFL.docx", "D:\\T1csxx.pdf");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Config.setDefaultSecret("yE6Tin1JVVnH023I");// Get your secret at https://www.convertapi.com/a
		File aDirectory = new File("D:\\uploaded_files\\");
		// get a listing of all files in the directory
		String[] filesInDir = aDirectory.list();
		// sort the list of files (optional)
		// Arrays.sort(filesInDir);
		// have everything i need, just print it now
		for (int i = 0; i < filesInDir.length; i++) {

			// System.out.println( "file: " + filesInDir[i] );
			System.out.println("D:\\uploaded_files\\" + filesInDir[i]);
			String phat = ("D:\\uploaded_files\\" + filesInDir[i]).toString();
			System.out.println(phat);

			// "D:\\uploaded_files\\ReportTP1 CLCO.docx"
			// "D:\\Text.docx"
			CompletableFuture<ConversionResult> result = ConvertApi.convert("docx", "pdf",
					new Param("file", Paths.get(phat)));
			try {
				result.get().saveFile(Paths.get("D:\\my_fileClco.pdf")).get();
				// new EmailAttachmentSender().sendEmailWithAttachments(host, port, userName,
				// password, toAddress, subject, message, attachFiles);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				// connects to the database
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
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
			} catch (SQLException ex) {
				System.out.println("Databaes not insert ");
				ex.printStackTrace();
			}
		}

	}

}

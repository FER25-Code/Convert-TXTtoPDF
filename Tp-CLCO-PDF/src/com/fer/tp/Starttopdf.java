package com.fer.tp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Starttopdf {

	public void Callpath() throws IOException, InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		   // create a file that is really a directory
		
		
	    File aDirectory = new File("D:\\uploaded_files\\");

	    // get a listing of all files in the directory
	    String[] filesInDir = aDirectory.list();

	    // sort the list of files (optional)
	    // Arrays.sort(filesInDir);

	    // have everything i need, just print it now
	    for ( int i=0; i<filesInDir.length; i++ )
	    {
	    	
	    //  System.out.println( "file: " + filesInDir[i] );
	      System.out.println( "D:\\uploaded_files\\"+ filesInDir[i]);
	      String phat = ("D:\\uploaded_files\\"+filesInDir[i]).toString();
	      System.out.println(phat);
	     
	      //"D:\\uploaded_files\\ReportTP1 CLCO.docx"
	  	//"D:\\Text.docx"
	 
	    
	    }

	}
}


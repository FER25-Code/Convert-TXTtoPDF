package com.fer.tp;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ccc {

    public static void main(String[] args) {
    	
    }
	    public  void ConvertToPDF(String docPath, String pdfPath) {
	        try {
	            InputStream doc = new FileInputStream(new File(docPath));
	            XWPFDocument document = new XWPFDocument(doc);
	            PdfOptions options = PdfOptions.create();
	            OutputStream out = new FileOutputStream(new File(pdfPath));
	         PdfConverter.getInstance().convert(document, out, options);
	           
	            System.out.println("Done");
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	    
	    }
	    }


}

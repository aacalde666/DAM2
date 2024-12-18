package com.dam2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//@WebServlet(name = "/upload")
@MultipartConfig
public class ejmUploadDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ejmUploadDownload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part filePart = request.getPart("file");
		InputStream input = filePart.getInputStream();
		String fileName = getSubmittedFileName(filePart);
		if (fileName.contains(File.separator)) {
			int index = fileName.lastIndexOf(File.separator);
			fileName = fileName.substring(index+1);
		}
		File f = new File("C:\\Users\\aacal\\Desktop\\ARCHIVOS SUBIDOS\\"+fileName);
		FileOutputStream fos = new FileOutputStream(f);
		int n;
		while ((n = input.read()) != -1) {
			fos.write(n);
		}
		input.close();
		fos.close();
		response.getWriter().append("<html><head><meta charset=\"UTF-8\"><body><h1>");
		response.getWriter().append("archivo subido con exito");
		response.getWriter().append("</h1></body></html>");
	}

	private String getSubmittedFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

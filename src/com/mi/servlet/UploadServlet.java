package com.mi.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.*;

public class UploadServlet extends HttpServlet {
	private String filePath;
	private String tempFilePath;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		filePath = config.getInitParameter("filePath");
		tempFilePath = config.getInitParameter("tempFilePath");
		//读取初始化参数tempFilePath
		filePath = getServletContext().getRealPath(filePath);
		tempFilePath = getServletContext().getRealPath(tempFilePath);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		//定义向客户端发送正文响应的outNet
		PrintWriter outNet = response.getWriter();
		try {
			//创建一个基于硬盘的FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置向硬盘写数据时所用的缓冲区大小，此处为4k
			factory.setSizeThreshold(4*1024);
			//设置临时目录
			factory.setRepository(new File(tempFilePath));
			
			//创建一个文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置允许上传问价的最大尺寸，此处为4M
			upload.setSizeMax(4*1024*1024);
			
			List items = upload.parseRequest(request);
			
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					processFormField(item,outNet);
				} else {
					processUploadedFile(item,outNet);
				}
			}
			outNet.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void processFormField(FileItem item,PrintWriter outNet) {
		String name = item.getFieldName();		//获得表单域的名字
		String value = item.getString();		//获取表单域的值
		outNet.println(name + " : " + value + "\r\n");
	}
	
	private void processUploadedFile(FileItem item, PrintWriter outNet) throws Exception {
		String filename = item.getName();			//获得包括路径在内的文件名
		int index = filename.lastIndexOf("\\");		
		filename = filename.substring(index+1,filename.length()); 		//获得不包括路径的文件名
		long fileSize = item.getSize();				//获得文件的大小
		
		if (filename.equals("") && fileSize == 0) return;
		
		File uploadFile = new File(filePath + "/" + filename);
		item.write(uploadFile);
		outNet.println(filename + " is saved.");
		outNet.println("The size of " + filename + " is " + fileSize + "\r\n");
	}
}

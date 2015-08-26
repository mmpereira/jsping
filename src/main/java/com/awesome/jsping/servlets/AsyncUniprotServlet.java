package com.awesome.jsping.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.jsping.UniprotService;

@WebServlet(urlPatterns = "/uniprot")
public class AsyncUniprotServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UniprotService service;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
//		AsyncContext ctx = request.startAsync(request, response);
//		UniprotTask task = new UniprotTask(ctx);
//		ctx.start(task);
		
		service = new UniprotService();
		try {
			
			response.getWriter().println(service.callService());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

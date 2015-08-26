package com.awesome.jsping.servlets;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.jsping.tasks.NextprotTask;

@WebServlet(urlPatterns="/shakeit", asyncSupported=true)
public class AsyncNextprotServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		
		String uniprotParam = request.getParameter("uniprot");
		
		
		AsyncContext ctx = request.startAsync(request, response);
		
		NextprotTask task = new NextprotTask(ctx);
		
		if(uniprotParam != null && Boolean.parseBoolean(uniprotParam)) {
			ctx.dispatch("/uniprot");
		} else {
			ctx.start(task);	
		}
		
	}
}

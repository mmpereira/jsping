package com.awesome.jsping.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 287766011062884926L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		getServletContext().getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
	}
}

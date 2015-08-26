package com.awesome.jsping.tasks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awesome.jsping.UniprotService;

public class UniprotTask implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(UniprotTask.class);
	
	
	private AsyncContext ctx;
	private UniprotService service;
	
	public UniprotTask(AsyncContext ctx) {
		this.ctx = ctx;
		this.service = new UniprotService();
	}

	@Override
	public void run() {

		PrintWriter writer;
		try {
			writer = ctx.getResponse().getWriter();
			writer.println(callService());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			ctx.complete();
		}
	}
	
	public String callService() throws ClientProtocolException, IOException {
		return service.callService();
	}

}

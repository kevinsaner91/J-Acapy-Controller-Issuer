package simplewebapp.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


@WebServlet(urlPatterns = { "/delete" })
public class DeleteConnectionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DeleteConnectionServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String connection_id = (String) request.getParameter("connection_id");

		System.out.println(connection_id);
		String URL = "https://alice-api.educa.ch/connections/" + connection_id + "/remove";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(URL);

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		System.out.println("Executing request " + httpPost.getRequestLine());

		HttpResponse response1 = httpclient.execute(httpPost);
		
		String errorString = null;

		
		// If has an error, redirecte to the error page.
		if (errorString != null) {
			// Store the information in the request attribute, before forward to views.
			request.setAttribute("errorString", errorString);
			// 
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the product listing page.		
		else {
			response.sendRedirect(request.getContextPath() + "/active");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

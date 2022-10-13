package simplewebapp.servlet;

import java.io.IOException;
import java.net.URI;

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

import simplewebapp.properties.PropertiesUtil;

@WebServlet(urlPatterns = { "/accept" })
public class AcceptConnectionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AcceptConnectionServlet() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/acceptView.jsp");

		dispatcher.forward(request, response);

	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String connectionString = request.getParameter("connectionString");

		System.out.println(connectionString);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(new PropertiesUtil().getScheme() +"://" +  new PropertiesUtil().getFaberAgentURL()+  "/connections/receive-invitation");

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		StringEntity stringEntity = new StringEntity(connectionString);

		httpPost.setEntity(stringEntity);

		System.out.println("Executing request " + httpPost.getRequestLine());

		HttpResponse response1 = httpclient.execute(httpPost);

		response.sendRedirect(request.getContextPath() + "/active");

	}

}

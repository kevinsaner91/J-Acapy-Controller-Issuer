package simplewebapp.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.owlike.genson.Genson;

import simplewebapp.beans.Connection;
import simplewebapp.beans.UserAccount;
import simplewebapp.utils.DBUtils;
import simplewebapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/newConnection" })
public class newConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public newConnectionServlet() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/newConnectionView.jsp");

		dispatcher.forward(request, response);

	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Something happened" );
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String connectionString = null;
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("https://alice-api.educa.ch/connections/create-invitation"))
					/*
					 * .addParameter("foo1", "bar1") .addParameter("foo2", "bar2")
					 */
                    .build();
 
            CloseableHttpResponse response1 = httpclient.execute(httppost);
            try {
            	
            	connectionString = EntityUtils.toString(response1.getEntity());
            	System.out.println(connectionString);
            	
            	Connection connection = new Genson().deserialize(connectionString, Connection.class);
            	
            	if(connection != null) {
            		request.setAttribute("connectionString", connection.getInvitation_url());
            	}

            } finally {
                response1.close();
            }
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            httpclient.close();
        }
        
        if (connectionString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/newConnectionView.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorString", "Error when receiving the connection details");
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/newConnectionView.jsp");

			dispatcher.forward(request, response);
		}
	}

}

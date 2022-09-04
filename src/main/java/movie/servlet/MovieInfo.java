package movie.servlet;

import movie.dal.*;
import movie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/movieInfo")
public class MovieInfo extends HttpServlet {
	
	protected MoviesDao moviesDao;
	
	@Override
	public void init() throws ServletException {
		moviesDao = MoviesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
//        String userName = req.getParameter("username");
//        if (userName == null || userName.trim().isEmpty()) {
//            messages.put("title", "Invalid username.");
//        } else {
//        	messages.put("title", "BlogPosts for " + userName);
//        }
        
        // Retrieve BlogUsers, and store in the request.
        Movies m1 = null;
		try {
			m1 = moviesDao.getMovieByMovieId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        List<BlogPosts> blogPosts = new ArrayList<BlogPosts>();
//        try {
//        	BlogUsers blogUser = new BlogUsers(userName);
//        	blogPosts = blogPostsDao.getBlogPostsForUser(blogUser);
//        } catch (SQLException e) {
//			e.printStackTrace();
//			throw new IOException(e);
//        }
//        req.setAttribute("blogPosts", blogPosts);
        req.setAttribute("blogPosts", m1);
        req.getRequestDispatcher("/MovieInfo.jsp").forward(req, resp);
	}
}

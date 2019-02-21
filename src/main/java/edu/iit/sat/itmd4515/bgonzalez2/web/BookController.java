/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Borja
 */
@WebServlet(name = "BookController", urlPatterns = {"/book"})
public class BookController extends HttpServlet {

    @Resource
    Validator validator;

    @Resource(lookup = "jdbc/itmd4515")
    DataSource ds;

    private static final Logger LOG = Logger.getLogger(BookController.class.getName());

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Inside the doGet method");

        //response.sendRedirect("/bgonzalez2-fp/index.html");
        Book book = new Book();
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookform.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Inside the doPost method");

        String titleParam = request.getParameter("bookTitle");
        String authorParam = request.getParameter("bookAuthor");
        String genreParam = request.getParameter("bookGenre");
        String dateParam = request.getParameter("bookYearPublished");
        Date formmatedDate = null;

        LOG.info("Received bookTitle=" + titleParam + " authorParam=" + authorParam + " genreParam=" + genreParam + " bookYearPublished=" + dateParam);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (dateParam != null && !(dateParam.isEmpty())) {
            try {
                formmatedDate = format.parse(dateParam);
            } catch (ParseException ex) {
                LOG.log(Level.SEVERE, null, ex);
                try {
                    response.sendRedirect("/bogonzalez2-fp/error.jsp");
                } catch (Exception ex1) {
                    LOG.log(Level.SEVERE, null, ex1);
                }
            }
        }

        Book book = new Book(titleParam, authorParam, genreParam, null);
        LOG.info(book.toString());

        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        if (constraintViolations.size() > 0) {
            LOG.info("We know we have a problem. This failed validation");

            for (ConstraintViolation<Book> bad : constraintViolations) {
                LOG.info(bad.getPropertyPath() + ": " + bad.getMessage());
            }

            request.setAttribute("formattedPublishedYear", format.format(book.getYearPublished()));
            request.setAttribute("mistakes", constraintViolations);
            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookform.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            }

        } else {
            LOG.info("No problems here with user input. Proceed!");

            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookok.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            }

            try (Connection c = ds.getConnection()) {
                String Insert_SQL = "insert into book "
                        + "(title, author, genre, yearPublished)"
                        + " values (?,?,?,?)";
                try (PreparedStatement ps = c.prepareStatement(Insert_SQL)) {
                    ps.setString(1, book.getTitle());
                    ps.setString(2, book.getAuthor());
                    ps.setString(3, book.getGenre());
                    ps.setString(4, dateParam);
                    
                    ps.executeUpdate();
                }
                c.close();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
                try {
                    response.sendRedirect("/bogonzalez2-fp/error.jsp");
                } catch (Exception ex1) {
                    LOG.log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

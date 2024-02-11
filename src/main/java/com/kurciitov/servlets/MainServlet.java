package com.kurciitov.servlets;

import com.kurciitov.storage.PhraseStorageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(
//        name = "MainServlet",
//        urlPatterns = {
//                "/help/service/v1/support"
//        })
public class MainServlet extends HttpServlet {

    private static final int STATUS_OK = 200;
    private static final int STATUS_CREATED = 201;

    private final PhraseStorageService phraseStorageService = new PhraseStorageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        try(PrintWriter writer = resp.getWriter()) {
            String randomPhrase = phraseStorageService.getRandomPhrase();
            writer.println(randomPhrase);
        } catch (IOException ex) {
            throw new IOException("Ошибка ввода-вывода");
        }
        resp.setStatus(STATUS_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        String phrase = req.getParameter("phrase");
        phraseStorageService.create(phrase);
        resp.setStatus(STATUS_CREATED);
    }
}

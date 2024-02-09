package com.kurciitov.servlets;

import com.kurciitov.storage.PhraseStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainServletTest {

    private MainServlet mainServlet;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private StringWriter responseWriter;

    private PhraseStorageService phraseStorageService;

    private static final int STATUS_OK = 200;

    @BeforeEach
    public void setUp() throws IOException{
        mainServlet = new MainServlet();
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        phraseStorageService = mock(PhraseStorageService.class);

        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }

    @Test
    void doGet() throws ServletException, IOException {
        String prhase = "I help you";

        mainServlet.doGet(httpServletRequest, httpServletResponse);
        doReturn(prhase).when(phraseStorageService).getRandomPhrase();

        assertEquals(prhase, phraseStorageService.getRandomPhrase());
        verify(httpServletResponse, times(1)).setStatus(STATUS_OK);
    }

    @Test
    void doPost() throws ServletException, IOException {
        String prhase = "I help you";


        phraseStorageService.create(prhase);
        httpServletResponse.setStatus(STATUS_OK);
        mainServlet.doPost(httpServletRequest, httpServletResponse);

        verify(phraseStorageService, times(1)).create(prhase);
        verify(httpServletResponse, times(1)).setStatus(STATUS_OK);
    }
}
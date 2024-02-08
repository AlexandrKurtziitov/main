package com.kurciitov.servlets;

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

    private static final int STATUS_OK = 200;

    @BeforeEach
    public void setUp() throws IOException{
        mainServlet = new MainServlet();
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }

    @Test
    void doGet() throws ServletException, IOException {
        mainServlet.doGet(httpServletRequest, httpServletResponse);
        verify(httpServletResponse, times(1)).setStatus(STATUS_OK);
    }

    @Test
    void doPost() {
    }
}
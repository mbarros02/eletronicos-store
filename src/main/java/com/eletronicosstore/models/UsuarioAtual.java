package com.eletronicosstore.models;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioAtual {

    private static final String SESSION_KEY = "usuarioLogado";

    public static Usuario getUsuarioAtual(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Usuario) session.getAttribute(SESSION_KEY);
        }
        return null;
    }

    public static void setUsuarioAtual(HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, usuario);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}

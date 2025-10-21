package com.eletronicosstore.service;

public class ChecarNulo {
    public static boolean checarValorNulo(String... valores) {
        for (String c : valores) {
            if (c == null || c.isBlank()) {
                return true;
            }
        }
        return false;
    }
}

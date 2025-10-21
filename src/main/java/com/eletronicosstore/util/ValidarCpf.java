package com.eletronicosstore.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidarCpf {
    private static final CPFValidator validator = new CPFValidator();

    public static boolean valido(String cpf) {
        try {
            validator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
}

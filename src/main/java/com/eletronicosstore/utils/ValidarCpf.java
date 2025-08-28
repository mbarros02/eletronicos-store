package com.eletronicosstore.utils;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.InvalidValue;

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

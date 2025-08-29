package com.eletronicosstore.database;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {

    public static String hashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public static boolean verificarSenha(String senha, String senhaHash) {
        return BCrypt.checkpw(senha, senhaHash);
    }
}

package authTestes;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.service.ValidarSenha;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class LoginUsuariosTest {
    private Usuario usuario;
    private UsuarioDao dao;
    private String hash = "123456";

    @Before
    public void configuracoesTeste() {
        usuario = new Usuario();
        dao = new UsuarioDao();
        hash = ValidarSenha.hashSenha(hash);
        usuario.setSenha1("123456");
    }

    @Test
    public void testarLoginUsuarioValido() {
        String email = "adm@teste.com";
        usuario = dao.buscarPorEmail(email);
        assertNotNull("O cliente deve existir no banco.", usuario);
        boolean validaSenha = ValidarSenha.verificarSenha("123456", usuario.getSenha1());
        assertTrue("Login com Sucesso!", validaSenha);
    }

    @Test
    public void testarLoginUsuarioInvalido() {
        String email = "testelogin5@barros.com";
        usuario = dao.buscarPorEmail(email);
        assertNull("O cliente não existe no banco.", usuario);
    }

    @Test
    public void testarLoginUsuarioSenhaInvalida() {
        String email = "adm@teste.com";
        usuario = dao.buscarPorEmail(email);
        assertNotNull("O cliente não existe no banco.", usuario);
        boolean validaSenha = ValidarSenha.verificarSenha("1234", usuario.getSenha1());
        assertFalse("Falha de Login!", validaSenha);
    }
}

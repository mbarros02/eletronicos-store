package authTestes;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.service.ValidarSenha;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LoginClientesTest {

    private Cliente cliente;
    private ClienteDao dao;
    private String hash = "123456";

    @Before
    public void configuracoesTeste() {
        cliente = new Cliente();
        dao = new ClienteDao();
        hash = ValidarSenha.hashSenha(hash);
        cliente.setSenha1("123456");
    }

    @Test
    public void testarLoginClienteValido() {
        String email = "usuTeste@teste.com";
        cliente = dao.buscarPorEmail(email);
        assertNotNull("O cliente deve existir no banco.", cliente);
        boolean validaSenha = ValidarSenha.verificarSenha("123456", cliente.getSenha1());
        assertTrue("Login com Sucesso!", validaSenha);
    }

    @Test
    public void testarLoginClienteInvalido() {
        String email = "testelogin5@barros.com";
        cliente = dao.buscarPorEmail(email);
        assertNull("O cliente não existe no banco.", cliente);
    }

    @Test
    public void testarLoginClienteSenhaInvalida() {
        String email = "usuTeste@teste.com";
        cliente = dao.buscarPorEmail(email);
        assertNotNull("O cliente não existe no banco.", cliente);
        boolean validaSenha = ValidarSenha.verificarSenha("1234", cliente.getSenha1());
        assertFalse("Falha de Login!", validaSenha);
    }
}

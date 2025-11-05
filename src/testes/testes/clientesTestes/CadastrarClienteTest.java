package clientesTestes;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.service.ValidarSenha;
import com.eletronicosstore.util.ValidarCpf;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CadastrarClienteTest {

    private Cliente cliente;
    private ClienteDao dao;
    private String hash = "123456";
    private String senhaInvalida = "123";
    private String cpfInvalido = "444.444.444-89";
    private int tamanhoSenha = hash.length();
    private LocalDate dataEspecifica = LocalDate.of(2002,1,26);

    @Before
    public void configuracoesTeste() {
        if(tamanhoSenha >= 4) {
            hash = ValidarSenha.hashSenha(hash);
            cliente = new Cliente(7, "Usuario-Teste", "483.379.270-20", "M", dataEspecifica, "usuTeste@teste.com", hash);
            dao = new ClienteDao();
        } else {
            System.out.println("Senha inválida!");
        }
    }

    @Test
    public void testarCpfValido() {
        boolean cpfValido = ValidarCpf.valido(cliente.getCpf());
        assertTrue("O cpf não é válido.", cpfValido);
    }

    @Test
    public void testarCpfInvalido() {
        boolean cpfValido = ValidarCpf.valido(cpfInvalido);
        assertFalse("O cpf é inválido.", cpfValido);
    }

    @Test
    public void testarSenhaValida() {
        int tamanhoSenha = cliente.getSenha1().length();
        assertTrue("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha >= 4);
    }

    @Test
    public void testarSenhaInvalida() {
        int tamanhoSenha = senhaInvalida.length();
        assertTrue("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha < 4);
    }

    @Test
    public void cadastraClienteTeste() {
        Cliente resultado = dao.cadastrar(cliente);
        assertNotNull("O retorno do cadastro não deve ser nulo.", resultado);
        assertEquals("O nome do usuário cadastrado deve ser igual ao informado.",
                cliente.getNome(), resultado.getNome());
        assertEquals("O e-mail deve ser mantido após o cadastro.",
                cliente.getEmail(), resultado.getEmail());
    }

    @Test
    public void testarSenhaCriptografada() {
        boolean heshado = ValidarSenha.verificarSenha("123456",hash);
        assertTrue("Senha correta, criptografada!", heshado);
    }

    @Test
    public void testarSenhaNaoCriptografada() {
        boolean heshado = ValidarSenha.verificarSenha("123",hash);
        assertFalse("Senha incorreta, não hashada!", heshado);
    }
}

package usuariosTestes;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.service.ValidarSenha;
import com.eletronicosstore.util.ValidarCpf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CadastrarUsuariosTest {

    private Usuario input;
    private UsuarioDao dao;
    private String hash = "123456";
    private String cpfInvalido = "444.444.444-89";
    private int tamanhoSenha = hash.length();

    @Before
    public void configuracoesTest() {
        if(tamanhoSenha >= 4) {
            hash = ValidarSenha.hashSenha(hash);
            input = new Usuario(10,"User_Teste", "447.668.848-92", "adm@teste.com", hash, 1);
            dao = new UsuarioDao();
        } else {
            System.out.println("Senha inválida!");
        }
    }

    @Test
    public void testarCpfInvalido() {
        boolean cpfValido = ValidarCpf.valido(cpfInvalido);
        assertFalse("O cpf não é válido.", cpfValido);
    }

    @Test
    public void testarCpfValido() {
        boolean cpfValido = ValidarCpf.valido(input.getCpf());
        assertTrue("O cpf é válido.", cpfValido);
    }

    @Test
    public void testarSenhaInvalida() {
        int tamanhoSenha = input.getSenha1().length();
        assertFalse("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha <= 4);
        System.out.println("Senha válida!");
    }

    @Test
    public void testarSenhaValida() {
        int tamanhoSenha = input.getSenha1().length();
        assertTrue("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha > 4);
        System.out.println("Senha válida!");
    }

    @Test
    public void testarCadastrarUsuario() {
        Usuario resultado = dao.cadastrar(input);
        assertNotNull("O retorno do cadastro não deve ser nulo.", resultado);
        assertEquals("O nome do usuário cadastrado deve ser igual ao informado.",
                input.getNome(), resultado.getNome());
        assertEquals("O e-mail deve ser mantido após o cadastro.",
                input.getEmail(), resultado.getEmail());
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

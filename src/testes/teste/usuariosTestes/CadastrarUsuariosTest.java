package usuariosTestes;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.util.ValidarCpf;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CadastrarUsuariosSucessoTest {

    private Usuario usuario;
    private UsuarioDao dao;

    @Before
    public void configuracoesTest() {
        usuario = new Usuario( "PessoaTeste", "447.668.848-92", "tppgf@teste.com", "12345", 1);
        dao = new UsuarioDao();
    }

    @Test
    public void testarCpfInvalido() {
        boolean cpfValido = ValidarCpf.valido(usuario.getCpf());
        assertFalse("O cpf não é válido.", cpfValido);
    }

    @Test
    public void testarCpfValido() {
        boolean cpfValido = ValidarCpf.valido(usuario.getCpf());
        assertTrue("O cpf é válido.", cpfValido);
    }

    @Test
    public void testarSenhaInvalida() {
        int tamanhoSenha = usuario.getSenha1().length();
        assertFalse("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha <= 4);
        System.out.println("Senha válida!");
    }

    @Test
    public void testarSenhaValida() {
        int tamanhoSenha = usuario.getSenha1().length();
        assertTrue("A senha deve conter no mínimo 4 caracteres.", tamanhoSenha > 4);
        System.out.println("Senha válida!");
    }

    @Test
    public void testarCadastrarUsuario() {
        Usuario resultado = dao.cadastrar(usuario);
        assertNotNull("O retorno do cadastro não deve ser nulo.", resultado);
        assertEquals("O nome do usuário cadastrado deve ser igual ao informado.",
                usuario.getNome(), resultado.getNome());
        assertEquals("O e-mail deve ser mantido após o cadastro.",
                usuario.getEmail(), resultado.getEmail());
    }

    @After
    public void limparTestes() {
        System.out.println("Teste efetuado!");
    }

}

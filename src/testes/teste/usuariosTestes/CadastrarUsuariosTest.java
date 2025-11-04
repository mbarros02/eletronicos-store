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

    private Usuario usuario;
    private UsuarioDao dao;
    private String hash = "123456";
    private int tamanhoSenha = hash.length();

    @Before
    public void configuracoesTest() {
        if(tamanhoSenha >= 4) {
            hash = ValidarSenha.hashSenha(hash);
            usuario = new Usuario(2,"Marcello", "871.422.930-71", "testelogin@barros.com", hash, hash, 1);
            dao = new UsuarioDao();
        } else {
            System.out.println("Senha inválida!");
        }
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

    @Test
    public void testarSenhaCriptografada() {
        boolean heshado = ValidarSenha.verificarSenha(usuario.getSenha1(),hash);
        assertFalse("Senha correta, criptografada!", heshado);
    }

    @Test
    public void testarSenhaNaoCriptografada() {
        boolean heshado = ValidarSenha.verificarSenha(usuario.getSenha1(),hash);
        assertTrue("Senha incorreta, não hashada!", heshado);
    }

    @After
    public void limparTestes() {
        System.out.println("Teste efetuado!");
    }

}

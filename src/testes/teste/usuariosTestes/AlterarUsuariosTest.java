package usuariosTestes;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.util.ValidarCpf;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlterarUsuariosTest {

    private Usuario usuario;
    private UsuarioDao dao;

    @Before
    public void configurarAlterar(){
        dao = new UsuarioDao();
    }

    @Test
    public void testarAlterarUsuario() {
        dao.cadastrar(usuario = new Usuario("PessoaTeste", "447.668.848-92", "tipo2@teste.com", "12345", 1));
        usuario.setNome("Marcello");
        usuario.setId(67);
        Usuario atualizado = dao.alterar(usuario);
        int tamanhoNome = atualizado.getNome().length();

        assertNotNull(atualizado);
        assertTrue("O nome precisa conter no mínimo 8 caracteres.", tamanhoNome >= 8);
        System.out.println("Nome alterado com sucesso!");
    }

    @Test
    public void alterarCpfValido() {
        dao.cadastrar(usuario = new Usuario("PessoaTeste", "447.668.848-92", "tipo1@teste.com", "12345", 1));
        usuario.setCpf("226.456.668-26");
        usuario.setId(68);
        Usuario atualizadoCpf = dao.alterar(usuario);

        boolean cpfValido = ValidarCpf.valido(atualizadoCpf.getCpf());
        assertFalse("O cpf é válido.", cpfValido);
    }

    @Test
    public void alterarCpfInvalido() {
        dao.cadastrar(usuario = new Usuario("PessoaTeste", "447.668.848-92", "tipo@teste.com", "12345", 1));
        usuario.setCpf("226.456.668-26");
        usuario.setId(69);
        Usuario atualizadoCpf = dao.alterar(usuario);

        boolean cpfValido = ValidarCpf.valido(atualizadoCpf.getCpf());
        assertFalse("O cpf é válido.", cpfValido);
    }

}

package service;

import model.Noticia;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.TestMockUtil;

public class ProcessadorDeNoticiasTest {
    private ProcessadorDeNoticias processarNoticia;

    @BeforeEach
    void setUp() {
        processarNoticia = new ProcessadorDeNoticias();
    }

    @Test
    @DisplayName("Deve lançar uma IllegalArgumentException quando link invalido")
    void extrairConteudoDasNoticias_LancaIllegalArgumentException_SeMalSucedido() {

        String html = TestMockUtil.retornarLinkInvalido();
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> processarNoticia.extrairConteudoDasNoticias(html));
    }
    @Test
    @DisplayName("Deve lançar uma NullPointerException quando a pagina do link contem elementos invalidos")
    void extrairConteudoDasNoticias_LancaNullPointerException_SeMalSucedido() {

        String html = TestMockUtil.retornarPaginaComParametrosNaoMapeados();
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> processarNoticia.extrairConteudoDasNoticias(html));
    }
    @Test
    @DisplayName("Deve retorna um objeto do tipo Noticia")
    void extrairConteudoDasNoticias_RetornaUmObjetoNoticia_SeBemSucedido (){
        String noticia = TestMockUtil.retornarLinkComNoticiaValida();
        Assertions.assertThat(processarNoticia.extrairConteudoDasNoticias(noticia))
                .isNotNull()
                .isInstanceOf(Noticia.class);
    }

}

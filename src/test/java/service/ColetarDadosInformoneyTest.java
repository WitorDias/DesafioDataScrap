package service;

import model.Noticia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ColetarDadosInfomoney;
import utils.TestMockUtil;

import java.net.UnknownHostException;

public class ColetarDadosInformoneyTest {
    private ColetarDadosInfomoney coletarDadosInfomoney;

    @BeforeEach
    void setUp() {
        coletarDadosInfomoney = new ColetarDadosInfomoney();
    }
    @Test
    @DisplayName("Deve retornar um set com links")
    void coletarLinksDasNoticias_RetornaSetComLinks_SeBemSucedido() {

        String html = TestMockUtil.retornarHtmlCom3LinksValidos();
        Assertions.assertThat(coletarDadosInfomoney.coletarLinksDasNoticias(html))
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }
    @Test
    @DisplayName("Deve retornar uma lista vazia quando nulo ou vazio")
    void coletarLinksDasNoticias_RetornaListaVaziaQuandoNuloOuVazio_SeMalSucedido() {

        String html = null;
        Assertions.assertThat(coletarDadosInfomoney.coletarLinksDasNoticias(html))
                .isNotNull()
                .isEmpty();
        String html2 = "";
        Assertions.assertThat(coletarDadosInfomoney.coletarLinksDasNoticias(html2))
                .isNotNull()
                .isEmpty();
    }
    @Test
    @DisplayName("Deve lançar uma IllegalArgumentException quando link invalido")
    void extrairConteudoDasNoticias_LancaIllegalArgumentException_SeMalSucedido() {

        String html = TestMockUtil.retornarLinkInvalido();
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> coletarDadosInfomoney.extrairConteudoDasNoticias(html));
    }
    @Test
    @DisplayName("Retorna um objeto do tipo Noticia")
    void extrairConteudoDasNoticias_RetornaUmObjetoNoticia_SeBemSucedido (){
        String noticia = TestMockUtil.retornarLinkComNoticiaValida();
        Assertions.assertThat(coletarDadosInfomoney.extrairConteudoDasNoticias(noticia))
                .isNotNull()
                .isInstanceOf(Noticia.class);
    }

}

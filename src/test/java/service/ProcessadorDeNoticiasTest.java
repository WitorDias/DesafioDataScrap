package service;

import model.Noticia;
import org.assertj.core.api.Assertions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import utils.TestMockUtil;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

public class ProcessadorDeNoticiasTest {

    private ProcessadorDeNoticias processadorSpy;

    @BeforeEach
    void setUp() {
        processadorSpy = Mockito.spy(new ProcessadorDeNoticias());
    }

    @Test
    @DisplayName("Deve lançar uma IllegalArgumentException quando link invalido")
    void extrairConteudoDasNoticias_LancaIllegalArgumentException_SeMalSucedido() {

        String html = TestMockUtil.retornarLinkInvalido();
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> processadorSpy.extrairConteudoDasNoticias(html));
    }

    @Test
    @DisplayName("Deve retorna um objeto do tipo Noticia")
    void extrairConteudoDasNoticias_RetornaUmObjetoNoticia_SeBemSucedido () throws IOException {

        File paginaHtml = TestMockUtil.retornarPaginaComNoticia1();

        Document mockDocument = Jsoup.parse(paginaHtml, "UTF-8");

        doReturn(mockDocument).when(processadorSpy).obterDocumento(anyString());

        String noticia = TestMockUtil.retornarLinkComNoticiaValida();

        Assertions.assertThat(processadorSpy.extrairConteudoDasNoticias(noticia))
                .isNotNull()
                .isInstanceOf(Noticia.class);
    }

    @Test
    @DisplayName("Deve verificar se o metodo funciona corretamente dado um mock de noticia")
    void extrairConteudoDasNoticias_VerificaSeOMetodoFuncionaDevidamente_SeBemSucedido() throws IOException {

        File paginaHtml = TestMockUtil.retornarPaginaComNoticia1();

        Document mockDocument = Jsoup.parse(paginaHtml, "UTF-8");

        doReturn(mockDocument).when(processadorSpy).obterDocumento(anyString());

        String mockUrl = TestMockUtil.retornarLinkNoticia1();
        Noticia noticia = processadorSpy.extrairConteudoDasNoticias(mockUrl);

        assertNotNull(noticia);
        assertEquals("Equipe InfoMoney", noticia.getAutor());
        assertEquals("Americanas: AMER3 dispara 180% após lucro bilionário no 3T; é a virada da varejista?",noticia.getTitulo());
        assertEquals("14/11/2024 18h31", noticia.getDataPublicacao());
        assertEquals(mockUrl, noticia.getUrl());
    }
}

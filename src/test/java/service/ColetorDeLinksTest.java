package service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestMockUtil;

public class ColetorDeLinksTest {
    private ColetorDeLinks coletorLinks;

    @BeforeEach
    void setUp() {
        coletorLinks = new ColetorDeLinks();
    }
    @Test
    @DisplayName("Deve retornar um set com links")
    void coletarLinksDasNoticias_RetornaSetComLinks_SeBemSucedido() {

        String html = TestMockUtil.retornarHtmlCom3LinksValidos();
        Assertions.assertThat(coletorLinks.coletarLinksDasNoticias(html))
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }
    @Test
    @DisplayName("Deve retornar uma lista vazia quando nulo ou vazio")
    void coletarLinksDasNoticias_RetornaListaVaziaQuandoNuloOuVazio_SeMalSucedido() {

        String html = null;
        Assertions.assertThat(coletorLinks.coletarLinksDasNoticias(html))
                .isNotNull()
                .isEmpty();
        String html2 = "";
        Assertions.assertThat(coletorLinks.coletarLinksDasNoticias(html2))
                .isNotNull()
                .isEmpty();
    }
}

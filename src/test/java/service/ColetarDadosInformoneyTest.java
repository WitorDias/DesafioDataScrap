package service;

import model.Noticia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestMockUtil;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class ColetarDadosInformoneyTest {
    private ColetarDadosInfomoney coletarDadosInfomoney;
    private ColetorDeLinks linkCollectorMock;
    private ProcessadorDeNoticias noticiaProcessorMock;

    @BeforeEach
    void setUp() {
        linkCollectorMock = mock(ColetorDeLinks.class);
        noticiaProcessorMock = mock(ProcessadorDeNoticias.class);
        coletarDadosInfomoney = new ColetarDadosInfomoney(linkCollectorMock, noticiaProcessorMock);
    }

    @Test
    @DisplayName("Deve retornar um Set de String se bem sucedido")
    void coletarLinks_RetornaUmSetDeStrings_SeBemSucedido(){
        String htmlDoSelenium = TestMockUtil.retornarConteudoSelenium();
        Assertions.assertThat(coletarDadosInfomoney.coletarLinks(htmlDoSelenium))
                .isNotNull()
                .hasSize(24)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Deve verificar se o metodo entrarNosLinksEExtrairConteudoDasNoticias interage com extrairConteudoDasNoticias")
    void entrarNosLinksEExtrairConteudoDasNoticias_ProcessaNoticias_SeBemSucedido(){
        String linkNoticia1 = TestMockUtil.retornarLinkNoticia1();

        Noticia mockNoticia = Noticia.builder()
                .url(linkNoticia1)
                .subtitulo("CEO global da Enel reitera intenção de renovar concessão de distribuição em São Paulo")
                .autor("Reuters")
                .conteudo("Banco vê riscos fiscais e políticos afetando os...")
                .dataPublicacao("18/11/2024 12h59")
                .build();

        Set<String> noticias = new LinkedHashSet<>();
        noticias.add(linkNoticia1);

        when(noticiaProcessorMock.extrairConteudoDasNoticias(linkNoticia1)).thenReturn(mockNoticia);

        coletarDadosInfomoney.entrarNosLinksEExtrairConteudoDasNoticias(noticias);

        verify(noticiaProcessorMock, times(1)).extrairConteudoDasNoticias("https://www.infomoney.com.br/mercados/cemig-seguira-com-privatizacao-se-houver-aprovacao-de-pl-e-pec-na-assembleia-diz-ceo/");

    }

}

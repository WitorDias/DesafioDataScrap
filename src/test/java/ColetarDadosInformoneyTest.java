import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ColetarDadosInfomoney;
import utils.TestMockUtil;

import java.util.LinkedHashSet;
import java.util.Set;

public class ColetarDadosInformoneyTest {
    private ColetarDadosInfomoney coletarDadosInfomoney;

    @BeforeEach
    void setUp() {
        coletarDadosInfomoney = new ColetarDadosInfomoney();
    }
    @Test
    @DisplayName("Deve retornar um set com links")
    void coletarLinks_RetornaSetComLinks_SeBemSucedido() {

        String link = TestMockUtil.retornarLinkValido();
        Assertions.assertThat(coletarDadosInfomoney.coletarLinks(link))
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

    }
}

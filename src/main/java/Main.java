import service.ColetarDadosInfomoney;
import utils.SeleniumUtil;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        ColetarDadosInfomoney executar = new ColetarDadosInfomoney();
        Set<String> conteudoSelenium = executar.coletarLinksDasNoticias(SeleniumUtil.pegarDadosColetadosPeloSelenium());
        executar.entrarNosLinksEExtrairConteudoDasNoticias(conteudoSelenium);


    }
}

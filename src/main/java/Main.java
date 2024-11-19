import service.ColetarDadosInfomoney;
import utils.SeleniumUtil;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        ColetarDadosInfomoney exec = new ColetarDadosInfomoney();
        Set<String> dadosSelenium = exec.coletarLinks(SeleniumUtil.pegarDadosColetadosPeloSelenium());
        exec.entrarNosLinksEExtrairConteudoDasNoticias(dadosSelenium);
        //Merge Realizado


    }
}

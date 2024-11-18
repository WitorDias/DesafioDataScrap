package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

public class TestMockUtil {
    public static String retornarHtmlCom3LinksValidos(){
        return """
        <html>
            <body>
                <div class="flex flex-col gap-3 p-4"></div>
                <div class="px-0">
                    <a href="/link1">Link 1</a>
                    <a href="/link2">Link 2</a>
                </div>
                <div class="px-0">
                    <a href="/link3">Link 3</a>
                </div>
            </body>
        </html>
        """;
    }

    public static String retornarLinkInvalido(){
        return "//asuhasuhaushaush";
    }
    public static Set<String> retornarLinksComoSetValidos(){
        Set<String> linksMock = new LinkedHashSet<>();
        linksMock.add("https://www.infomoney.com.br/mercados/petrobras-mais-do-que-resultado-dividendo-surpreende-e-indica-ha-espaco-para-mais-desempenho-acoes/");
        linksMock.add("https://www.infomoney.com.br/mercados/minerio-de-ferro-cai-antes-de-china-revelar-estimulos-desempenho-acao-vale-vale3/");
        linksMock.add("https://www.infomoney.com.br/mercados/temporada-de-resultados-do-3t24-e-destaque-veja-acoes-e-setores-para-ficar-de-olho/");
        return linksMock;
    }
    public static String retornarLinkComNoticiaValida(){
        return "https://www.infomoney.com.br/mercados/petrobras-mais-do-que-resultado-dividendo-surpreende-e-indica-ha-espaco-para-mais-desempenho-acoes/";
    }

    public static String retornarPaginaComParametrosNaoMapeados(){
        return "https://www.infomoney.com.br/mercados/ibovespa-hoje-bolsa-de-valores-ao-vivo-11112024/";
    }

    public static String retornarConteudoSelenium() {
        String arquivoPath = "src/main/resources/HtmlDaPaginaPrincipal.html";

        try {
            Path path = Paths.get(arquivoPath);
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String retornarPaginaComNoticia1() {
        String arquivoPath = "src/main/resources/PaginaHtml1.html";

        try {
            Path path = Paths.get(arquivoPath);
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String retornarLinkNoticia1(){
        return "https://www.infomoney.com.br/mercados/cemig-seguira-com-privatizacao-se-houver-aprovacao-de-pl-e-pec-na-assembleia-diz-ceo/";
    }
    public static String retornarPaginaComNoticia2() {
        String arquivoPath = "src/main/resources/PaginaHtml2.html";

        try {
            Path path = Paths.get(arquivoPath);
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}

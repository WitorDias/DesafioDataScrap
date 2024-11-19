package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static String retornarLinkComNoticiaValida(){
        return "https://www.infomoney.com.br/mercados/petrobras-mais-do-que-resultado-dividendo-surpreende-e-indica-ha-espaco-para-mais-desempenho-acoes/";
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
    public static File retornarPaginaComNoticia1() {

        Path resourcePath = Paths.get("src/main/resources/PaginaHtml1.html");
        return resourcePath.toFile();
    }

    public static String retornarLinkNoticia1(){
        return "https://www.infomoney.com.br/mercados/americanas-acoes-amer3-disparam-mais-de-20-apos-lucro-de-r-103-bi-no-3o-tri/";
    }

}

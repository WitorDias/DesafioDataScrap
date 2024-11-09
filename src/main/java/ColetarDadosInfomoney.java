import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ColetarDadosInfomoney {
    public void coletarLinksNoticias(){
        Set<String> linksList = new LinkedHashSet<>();
        String baseUrl = "https://www.infomoney.com.br/mercados/";

        try {
            Document doc = Jsoup.connect(baseUrl).get();
            Elements noticiasLinks = doc.select("div.px-0");
            for (Element newsBlock : noticiasLinks) {
                Elements links = newsBlock.select("a");
                for (Element link : links) {
                    String href = link.attr("href");
                    linksList.add(href);
                }
            }

            String filtro = "https://www.infomoney.com.br/mercados/";
            linksList.removeIf(link -> link.startsWith("https://lp.") || link.equals(filtro));
            System.out.println(linksList);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

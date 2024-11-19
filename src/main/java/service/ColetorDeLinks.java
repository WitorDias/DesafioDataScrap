package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.FuncoesUtilitarias;

import java.util.LinkedHashSet;
import java.util.Set;

public class ColetorDeLinks {
    public Set<String> coletarLinksDasNoticias(String seleniumArquivo){
        Set<String> linksList = new LinkedHashSet<>();

        if (seleniumArquivo == null || seleniumArquivo.isEmpty()) {
            return linksList;
        }

        try{
            Document doc = Jsoup.parse(seleniumArquivo);
            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("div.flex.flex-col.gap-3.p-4")); //Elemento: div tempo real
            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("div.p-4.flex.flex-col.gap-4")); //Elemento: tabela de ativos
            Elements blocoNoticias = doc.select("div.px-0");
            for (Element extrairLinks : blocoNoticias) {
                Elements links = extrairLinks.select("a");
                for (Element link : links) {
                    String href = link.attr("href");
                    linksList.add(href);
                }
            }
        }catch (Exception e){
            System.err.println("Erro ao processar os elementos do documento: " + e.getMessage());
        }
        String filtro = "https://www.infomoney.com.br/mercados/";
        linksList.removeIf(link -> link.startsWith("https://lp.") || link.equals(filtro) || link.contains("ao-vivo") || link.contains("ao vivo"));
        return linksList;
    }
}

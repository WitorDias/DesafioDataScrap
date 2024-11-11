package service;

import model.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.FuncoesUtilitarias;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ColetarDadosInfomoney {
    public Set<String> coletarLinksDasNoticias(String seleniumArquivo){
        Set<String> linksList = new LinkedHashSet<>();
        Document doc = Jsoup.parse(seleniumArquivo);
        FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("div.flex.flex-col.gap-3.p-4")); //div tempo real
        FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("div.p-4.flex.flex-col.gap-4")); //tabela de ativos
        Elements blocoNoticias = doc.select("div.px-0");
        for (Element extrairLinks : blocoNoticias) {
            Elements links = extrairLinks.select("a");
            for (Element link : links) {
                String href = link.attr("href");
                linksList.add(href);
            }
        }
        String filtro = "https://www.infomoney.com.br/mercados/";
        linksList.removeIf(link -> link.startsWith("https://lp.") || link.equals(filtro));
        return linksList;
    }

    public Noticia extrairConteudoDasNoticias(String url){

        Noticia noticiaConteudos = null;

        try {
            Document doc = Jsoup.connect(url).get();
            Element conteudo = doc.selectFirst("main");

            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("p.py-2.mt-auto.text-wl-neutral-500.text-xs.text-center")); //remover publicidade antes do artigo
            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("p.font-normal.text-base.text-wl-neutral-600.max-w-md.mx-auto")); //remover publicidade depois do artigo

            String titulo = conteudo.selectFirst("div[data-ds-component='article-title'] h1").text();
            String subtitulo = conteudo.selectFirst("div.text-lg").text();
            String autor = conteudo.selectFirst("a.text-base").text();
            String data = conteudo.selectFirst("main p:nth-of-type(2) time").text();
            Element artigo = doc.selectFirst("article.im-article.clear-fix[data-ds-component=article]");
            String conteudoTexto = artigo.select("p").text();

            noticiaConteudos = Noticia.builder()
                    .url(url)
                    .titulo(titulo)
                    .subtitulo(subtitulo)
                    .autor(autor)
                    .conteudo(conteudoTexto)
                    .dataPublicacao(data)
                    .build();

            System.out.println(noticiaConteudos);

        }catch (IOException e){
            e.printStackTrace();
        }
        return noticiaConteudos;
    }

    public void entrarNosLinksEExtrairConteudoDasNoticias(Set<String> lista){
        lista.forEach(this::extrairConteudoDasNoticias);

    }
}

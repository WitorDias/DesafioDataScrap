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
    public void obterLinksENoticias(){
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
            linksList.forEach(this::extrairConteudoDasNoticias);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void extrairConteudoDasNoticias(String url){

        try {
            Document doc = Jsoup.connect(url).get();
            Element conteudo = doc.selectFirst("main");

            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("p.py-2.mt-auto.text-wl-neutral-500.text-xs.text-center"));
            FuncoesUtilitarias.removerElementoSeExistir(doc.selectFirst("p.font-normal.text-base.text-wl-neutral-600.max-w-md.mx-auto"));

            String titulo = conteudo.selectFirst("div[data-ds-component='article-title'] h1").text();
            String subtitulo = conteudo.selectFirst("div.text-lg").text();
            String autor = conteudo.selectFirst("a.text-base").text();
            String data = conteudo.selectFirst("main div:nth-of-type(3) div:nth-of-type(1) div div p:nth-of-type(2) time").text();
            Element artigo = doc.selectFirst("article.im-article.clear-fix[data-ds-component=article]");
            String conteudoTexto = artigo.select("p").text();

            Noticia noticiaConteudos = Noticia.builder()
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
    }
}
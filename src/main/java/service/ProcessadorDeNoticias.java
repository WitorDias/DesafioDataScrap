package service;

import model.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.FuncoesUtilitarias;

import java.io.IOException;

public class ProcessadorDeNoticias {
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
            String conteudoArtigo = artigo.select("p").text();

            noticiaConteudos = Noticia.builder()
                    .url(url)
                    .titulo(titulo)
                    .subtitulo(subtitulo)
                    .autor(autor)
                    .conteudo(conteudoArtigo)
                    .dataPublicacao(data)
                    .build();

            System.out.println(url);
            System.out.println(titulo);
            System.out.println(subtitulo);
            System.out.println(autor);
            System.out.println(conteudoArtigo);
            System.out.println(data);

        }catch (IOException e){
            e.printStackTrace();
        }
        return noticiaConteudos;
    }
}

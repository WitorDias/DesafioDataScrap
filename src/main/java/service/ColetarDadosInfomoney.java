package service;

import java.util.Set;

public class ColetarDadosInfomoney {
    private final ColetorDeLinks coletarLink;
    private final ProcessadorDeNoticias processarNoticia;

    public ColetarDadosInfomoney() {
        this.coletarLink = new ColetorDeLinks();
        this.processarNoticia = new ProcessadorDeNoticias();
    }

    public ColetarDadosInfomoney(ColetorDeLinks linkCollector, ProcessadorDeNoticias noticiaProcessor) {
        this.coletarLink = linkCollector;
        this.processarNoticia = noticiaProcessor;
    }

    public Set<String> coletarLinks(String seleniumArquivo) {
        return coletarLink.coletarLinksDasNoticias(seleniumArquivo);
    }

    public void entrarNosLinksEExtrairConteudoDasNoticias(Set<String> lista) {
        lista.forEach(processarNoticia::extrairConteudoDasNoticias);
    }
}

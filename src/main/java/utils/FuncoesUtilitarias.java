package utils;

import org.jsoup.nodes.Element;

public class FuncoesUtilitarias {
    public static void removerElementoSeExistir(Element elemento){
        if(elemento != null){
            elemento.remove();
        }

    }
}

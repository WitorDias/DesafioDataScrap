package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Noticia {
    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private String conteudo;
    private String dataPublicacao;

}

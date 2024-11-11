# Importante
## Este projeto faz uma coleta de notícias do site infomoney, atribui as notícias objetos e as imprime.

<p align="justify"> Para que o programa execute corretamente se faz necessário ter instalado o ChromeDrive </p>

<p align="justify">Em seguida em: `src/main/java/utils/SeleniumUtil.java` altere o segundo argumento da linha seguir para o local onde o ChromeDrive está instalado:

```java
public class SeleniumUtil {
    public static String pegarDadosColetadosPeloSelenium() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeD\\chromedriver.exe");
    }
}

```
### Instalação do ChromeDrive
<p align="justify">Vá ao seu Google Chrome, busque por "Ajuda" em configurações, em seguida, "Sobre o Google Chrome".</p>
<p> Copie o código da versão do seu navegador e busque por uma versão do "chromedriver" compatível aqui:</p>
<a href="https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json">https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json</a>
<p align="justify">Para uma melhor visualização, certifique-se de ter um formatador de Json no navegador.</p>


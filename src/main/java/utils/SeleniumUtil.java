package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtil {
    public static String pegarDadosColetadosPeloSelenium() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeD\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://www.infomoney.com.br/mercados/";

        try {
            driver.get(url);

            // Espera até que o corpo da página tenha sido carregado completamente
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Aguarda um tempo adicional para garantir que a página está interativa
            Thread.sleep(5000);

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Interações com o botão "Carregar mais"
            for (int x = 0; x <= 2; x++) {
                jsExecutor.executeScript("window.scrollTo(0, 500);");
                jsExecutor.executeScript(
                        "setTimeout(() => {" +
                                "   Array.prototype.slice.call(document.getElementsByTagName('button'), 0)" +
                                "   .find(x => x.innerText == 'Carregar mais')?.click();" +
                                "}, 500);"
                );
                Thread.sleep(5000);
            }

            // Retorna o HTML final da página
            return driver.getPageSource();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            driver.close();
        }
    }
}

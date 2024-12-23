package org.example;

import com.google.common.cache.Cache;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WebScraper {
    private static final Logger log = LoggerFactory.getLogger(WebScraper.class);
    private static final int THREAD_COUNT = 5;


    //test
    public void test(){

        String URL = "https://u.gg/lol/champions/aatrox/build";

        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");

        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript", "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.main-header")));
            WebElement elements = driver.findElement(By.cssSelector("div.role-value"));
            System.out.println(elements.getText());


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore Scraping Champions");
        } finally {
            driver.quit();
        }
    }

    public List<String> scrapeChampion(){
        List<String> champions = new ArrayList<String>();
        String URL = "https://wiki.leagueoflegends.com/en-us/List_of_champions";

        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");

        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript", "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inline-image.label-only.champion-icon.tooltips-init-complete")));
            List<WebElement> elements = driver.findElements(By.cssSelector(".inline-image.label-only.champion-icon.tooltips-init-complete"));

            for (WebElement element : elements) {
                WebElement link = element.findElement(By.tagName("a"));

                String content = link.getText();
                String result = content.split("\n")[0];
                champions.add(result);

            }

        } catch (Exception e) {
            log.error("e: ", e);
            System.out.println("Errore Scraping Champions");
        } finally {
            driver.quit();
        }
        return champions;
    }
    public List<String> scrapeRunes(){
        String URL = "https://wiki.leagueoflegends.com/en-us/Rune";
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        List<String> rune = new ArrayList<>();
        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript" , "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.inline-image.label-after.rune-icon.tooltips-init-complete")));
            WebElement table = driver.findElement((By.cssSelector("table.article-table.rune-table")));
            List<WebElement> elements = table.findElements(By.cssSelector("span.inline-image.label-after.rune-icon.tooltips-init-complete"));

            for (WebElement element : elements) {
                WebElement linkelemeent = element.findElement(By.tagName("a"));
                String text = element.getText();
                String result = text.replaceFirst("^\\s+", "");
                rune.add(result);
            }

        } catch (Exception e) {
            System.out.println("Errore Scraping Links");
            System.out.println("Error:" + e);
        } finally {
            driver.quit();
        }
        return rune;
    }
    public List<String> scrapeSpell(){
        String URL = "https://wiki.leagueoflegends.com/en-us/Summoner_spell";
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        List<String> spell = new ArrayList<>();
        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript" , "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        String runa = "";

        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid-image.label-after.spell-icon.tooltips-init-complete")));
            WebElement table = driver.findElement((By.cssSelector("table.article-table")));
            List<WebElement> elements = table.findElements(By.cssSelector("div.grid-image.label-after.spell-icon.tooltips-init-complete"));


            for (WebElement element : elements) {
                WebElement linkelemeent = element.findElement(By.tagName("a"));
                String text = element.getText();
                String result = text.replaceFirst("^\\s+", "");
                spell.add(result);
            }

        } catch (Exception e) {
            System.out.println("Errore Scraping Links");
            System.out.println("Error:" + e);
        } finally {
            driver.quit();
        }
        return spell;
    }
    public List<Champion> scrapeLinks(){
        List<Champion> champions = new ArrayList<>();
        String URL = "https://u.gg/lol/champions";

        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript" , "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".champion-link")));
            List<WebElement> elements = driver.findElements(By.cssSelector(".champion-link"));
            for (WebElement element : elements) {
                String link =  element.getAttribute("href");
                String text = element.getText();
                champions.add(new Champion(text, link));
            }

        } catch (Exception e) {
            System.out.println("Errore Scraping Links");
            System.out.println("Error:" + e);
        }finally {
            driver.quit();
        }
        return champions;
    }
    public Champion scrapeBuildPage(Champion champion){


        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        //Non Apre Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-images", "--disable-extensions", "--disable-javascript" , "--no-sandbox" , "--log-level=3");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(champion.link);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".champion-ranking-stats-normal")));




            //Statistiche
            List<WebElement> elements = driver.findElements(By.cssSelector(".value"));
            champion.stats.setTier(elements.get(0).getText());
            champion.stats.setWinrate(elements.get(1).getText().replace("%",""));
            champion.stats.setPickrate(elements.get(3).getText().replace("%",""));
            champion.stats.setBanrate(elements.get(4).getText().replace("%",""));

            ArrayList<String> rune = new ArrayList<>();
            String name;
            String result = "";

            //Rune Principali
            WebElement element;
            element = driver.findElement(By.cssSelector("div.rune-tree.primary-tree"));
            rune.add(element.findElement(By.cssSelector("div.pointer")).getText());
            elements = element.findElements(By.cssSelector(".perk-active"));
            for (WebElement elemento : elements) {
                name = elemento.findElement(By.tagName("img")).getAttribute("alt");
                String[] words = name.split(" ");
                if (words.length > 2) {
                    // Reconstruct the string excluding the first two words
                    result = String.join(" ", java.util.Arrays.copyOfRange(words, 2, words.length));
                }
                rune.add(result);
            }

            //Rune Secondarie
            element = driver.findElement(By.cssSelector("div.secondary-tree"));
            rune.add(element.findElement(By.cssSelector("div.pointer")).getText());
            elements = element.findElements(By.cssSelector(".perk-active"));
            for (WebElement elemento : elements) {
                name = elemento.findElement(By.tagName("img")).getAttribute("alt");
                String[] words = name.split(" ");
                if (words.length > 2) {
                    // Reconstruct the string excluding the first two words
                    result = String.join(" ", java.util.Arrays.copyOfRange(words, 2, words.length));
                }
                rune.add(result);
            }

            //Rune Secondarie
            element = driver.findElement(By.cssSelector("div.rune-tree.stat-shards-container"));
            elements = element.findElements(By.cssSelector(".shard-active"));
            for (WebElement elemento : elements) {
                name = elemento.findElement(By.tagName("img")).getAttribute("alt");
                String[] words = name.split(" ");
                if (words.length > 2) {
                    result = String.join(" ", java.util.Arrays.copyOfRange(words, 1, words.length - 1));
                    rune.add(result);
                }
            }
            champion.build.setRune(rune);

            //Summoner
            element = driver.findElement(By.cssSelector("div.content-section_content.summoner-spells"));
            elements = element.findElements(By.tagName("img"));
            String summoner1 = elements.get(0).getAttribute("alt");
            String summoner2 = elements.get(1).getAttribute("alt");
            String[] words = summoner1.split(" ");
            summoner1 = String.join(" ", java.util.Arrays.copyOfRange(words, 2, words.length));
            words = summoner2.split(" ");
            summoner2 = String.join(" ", java.util.Arrays.copyOfRange(words, 2, words.length));

            champion.build.setsummoner(summoner1, summoner2);


            //Counter
            elements = driver.findElements(By.cssSelector("div.champion-name"));
            for(int i = 0; i < 3; i++){ champion.setCounter(elements.get(i).getAttribute("innerHTML"), i); }


            //role
            element = driver.findElement(By.cssSelector("div.role-value"));
            champion.role = element.findElement(By.tagName("div")).getAttribute("innerHTML");

            //nome
            element = driver.findElement(By.tagName("h1"));
            champion.name = element.findElement(By.tagName("span")).getAttribute("innerHTML");

        } catch (Exception e) {
            System.out.println("Errore Scraping Stats");
            System.out.println(e.getMessage());
        }finally {

            driver.quit();
        }
        return champion;
    }
    public void scrapeInParallel(List<Champion> champions) {
        final int WAIT_TIME_MS = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        int totalChampions = champions.size();
        int batchCount = (int) Math.ceil((double) totalChampions / THREAD_COUNT);

        for (int i = 0; i < batchCount; i++) {
            int start = i * THREAD_COUNT;
            int end = Math.min(start + THREAD_COUNT, totalChampions);
            List<Champion> batch = champions.subList(start, end);

            List<Future<Champion>> futures = new ArrayList<>();

            for (Champion champion : batch) {
                Callable<Champion> task = () -> scrapeBuildPage(champion);
                futures.add(executorService.submit(task));
            }


            // Wait for the batch to complete
            for (Future<Champion> future : futures) {
                try {
                    Champion result = future.get(); // Get the result of each task
                    System.out.println("Scraped data for: " + result.link);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Error processing a Champion: " + e);
                }
            }

        }


        executorService.shutdown();
    }
}

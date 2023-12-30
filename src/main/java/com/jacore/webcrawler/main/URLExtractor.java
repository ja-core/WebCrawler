package com.jacore.webcrawler.main;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.services.URLService;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
public class URLExtractor implements InitializingBean {

    private Set<String> urlsBeforeDeduplication;
    private final URLService urlService;
    private final HTMLParser htmlParser;

    public URLExtractor(URLService urlService, HTMLParser htmlParser) {
        this.urlService = urlService;
        this.htmlParser = htmlParser;
    }

    @Override
    public void afterPropertiesSet() {
        extract();
    }

    public void extract() {
        String parse = htmlParser.parse();
        urlsBeforeDeduplication = new HashSet<>();
        try {
            Document doc = Jsoup.parse(parse);
            doc.select("a").stream()
                    .map(a -> a.attr("href"))
                    .filter(a -> a.matches("^http\\w?[:/]{3}.+/$"))
                    .forEach(urlsBeforeDeduplication::add);
        } catch (NullPointerException e) {
            extract();
        }
        dedulicate(urlsBeforeDeduplication);
    }

    public void dedulicate(Set<String> urlsBeforeDeduplication) {
        urlsBeforeDeduplication.stream()
                .filter((a -> urlService.findURLByUrl(a).isEmpty()))
                .forEach(a -> urlService.saveURL(new URL(a)));
        extract();
    }
}

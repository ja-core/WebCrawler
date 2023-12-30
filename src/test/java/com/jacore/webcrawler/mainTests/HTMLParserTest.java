package com.jacore.webcrawler.mainTests;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.main.HTMLParser;
import com.jacore.webcrawler.services.URLService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HTMLParserTest {

    @Mock
    private URLService urlService;

    @InjectMocks
    private HTMLParser htmlParser;


    @Test
    public void parse() {
        String htmlForTesting = "title>HTML Page for";
        String urlToCheck = "http://web.simmons.edu/~grovesd/comm244/notes/week3/html-test-page.html";
        when(urlService.findURLById(0L)).thenReturn(Optional.of(new URL(0L, urlToCheck)));
        String parse = htmlParser.parse().strip().substring(66, 85);

        assert parse.equals(htmlForTesting);
    }
}
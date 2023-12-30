package com.jacore.webcrawler.main;

import com.jacore.webcrawler.services.URLService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Data
@Component
public class HTMLParser {

    public HTMLParser(URLService urlService) {
        this.urlService = urlService;
    }
    private final URLService urlService;

    private Long urlIdCounter = 0L;

    public String parse() {
        String text = null;
        try {
            URL url = new URL(urlService.findURLById(urlIdCounter++).get().getUrl());
            URLConnection cn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(cn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String inputLine = br.readLine();
            while (inputLine != null) {
                sb.append(inputLine).append("\n");
                inputLine = br.readLine();
            }
            br.close();
            text = sb.toString();

        } catch (Exception e) {
            parse();
        }
        return text;
    }
}

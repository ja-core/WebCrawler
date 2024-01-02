package com.jacore.webcrawler.repositoryTests;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.repositories.URLRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class URLRepositoryTest {

    @Autowired
    private URLRepository urlRepository;

    private final String urlToCheck = "https://github.com/";
    private final URL url = new URL(urlToCheck);

    @Test
    public void getURLById() {

        urlRepository.save(url);

        URL saved = urlRepository.findById(url.getId()).get();

        assert saved.getUrl().equals(url.getUrl());
        assert saved.getId().equals(url.getId());
    }

    @Test
    void saveURL() {
        URL save = urlRepository.save(url);

        assert save.getId().equals(url.getId());
        assert save.getUrl().equals(urlToCheck);
    }

    @Test
    public void getURLByUrl() {

        urlRepository.save(url);

        URL saved = urlRepository.findByUrl(url.getUrl()).get();

        assert saved.getUrl().equals(urlToCheck);
        assert saved.getId().equals(url.getId());
    }
}
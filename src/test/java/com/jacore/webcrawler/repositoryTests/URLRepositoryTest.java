package com.jacore.webcrawler.repositoryTests;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.repositories.URLRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        Assertions.assertThat(saved.getUrl()).isEqualTo(url.getUrl());
        Assertions.assertThat(saved.getId()).isEqualTo(url.getId());
    }


}


package com.jacore.webcrawler.serviceTests;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.repositories.URLRepository;
import com.jacore.webcrawler.services.URLService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class URLServiceTest {

    @Mock
    private URLRepository urlRepository;

    @InjectMocks
    private URLService urlService;

    private final Long id = 0L;
    private final String urlToCheck = "https://github.com/";

    @Test
    void findURLById() {
        when(urlRepository.findById(id)).thenReturn(Optional.of(new URL(id, urlToCheck)));
        URL url = urlService.findURLById(id).get();

        assert url.getUrl().equals(urlToCheck);
        assert url.getId().equals(id);
    }

    @Test
    void findURLBYUrl() {
        when(urlRepository.findByUrl(urlToCheck)).thenReturn(Optional.of(new URL(id, urlToCheck)));
        URL url = urlService.findURLByUrl(urlToCheck).get();

        assert url.getUrl().equals(urlToCheck);
        assert url.getId().equals(id);
    }

    @Test
    void saveURL() {
        when(urlRepository.save(new URL(id, urlToCheck))).thenReturn(new URL(id, urlToCheck));

        URL url = urlService.saveURL(new URL(id, urlToCheck));

        assert url.getUrl().equals(urlToCheck);
        assert url.getId().equals(id);
    }
}
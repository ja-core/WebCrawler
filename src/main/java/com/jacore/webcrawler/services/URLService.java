package com.jacore.webcrawler.services;

import com.jacore.webcrawler.entities.URL;
import com.jacore.webcrawler.repositories.URLRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Data
@Service
@Transactional(readOnly = true)
public class URLService {

    private final URLRepository urlRepository;

    @Transactional
    public URL saveURL(URL url) {
        return urlRepository.save(url);
    }

    public Optional<URL> findURLById(Long id) {
        return urlRepository.findById(id);
    }

    public Optional<URL> findURLByUrl(String url) {
        return urlRepository.findByUrl(url);
    }
}

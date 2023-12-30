package com.jacore.webcrawler.repositories;

import com.jacore.webcrawler.entities.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    Optional<URL> findURLByUrl(String url);
}

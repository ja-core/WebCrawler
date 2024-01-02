package com.jacore.webcrawler.repositories;

import com.jacore.webcrawler.entities.URL;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    Optional<URL> findByUrl(String url);
}

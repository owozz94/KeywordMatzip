package com.kwmz.keywordmatzip.app.repository;

import com.kwmz.keywordmatzip.app.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {

    //where keyword=?
    List<Search> findByKeyword(String keyword);
}

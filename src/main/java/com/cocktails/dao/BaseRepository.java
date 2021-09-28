package com.cocktails.dao;

import com.cocktails.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "base", path = "base")
public interface BaseRepository extends JpaRepository<Base, Long> {
}
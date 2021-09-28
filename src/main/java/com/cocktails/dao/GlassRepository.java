package com.cocktails.dao;

import com.cocktails.entity.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface GlassRepository extends JpaRepository<Glass, Long> {
}
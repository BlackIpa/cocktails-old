package com.cocktails.dao;

import com.cocktails.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface UnitRepository extends JpaRepository<Unit, Long> {
}

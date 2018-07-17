package com.example.apiDeneme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface soruRepository extends JpaRepository<soruModel, Long>{



}

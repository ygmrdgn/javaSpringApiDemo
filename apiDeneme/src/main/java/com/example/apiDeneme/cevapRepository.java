package com.example.apiDeneme;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.apiDeneme.cevapModel;


@Repository
public interface cevapRepository extends JpaRepository<cevapModel, Long> {
	
	List<cevapModel> findBySoruId(Long soruId);

}

package com.example.apiDeneme;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RestController
public class soruController {
	
	
	@Autowired
	soruRepository sr;
	
	@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
	@GetMapping("/sorular")
	public List<soruModel> getSorular(){
		return sr.findAll();				
	}
	
	
	
	@GetMapping("/sorular/{soruId}")
	    public soruModel getSoruModel(@PathVariable Long id) {
		return sr.getOne(id);
	    }
	   
	    
	    @PostMapping("/sorular")
	    public soruModel createSoruModel(@Valid @RequestBody soruModel soru) {
	        return sr.save(soru);
	    }
	    
	    @PutMapping("/sorular/{soruId}")
	    public soruModel updateSoruModel(@PathVariable Long soruId,
	                                   @Valid @RequestBody soruModel questionRequest) {
	        return sr.findById(soruId)
	        		    .map(soru -> {
	        		    	 soruModel aQuestion =questionRequest;
	                    soru.setTitle(questionRequest.getTitle());
	                    soru.setDescription(questionRequest.getDescription());
	                    return sr.save(soru);
	                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + soruId));
	    }
	    
	    @CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
	    @DeleteMapping("/sorular/{soruId}")
	    public ResponseEntity<?> deleteSoruModel(@PathVariable Long soruId) {
	        return sr.findById(soruId)
	                .map(soru -> {
	                    sr.delete(soru);
	                    return ResponseEntity.ok().build();
	                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + soruId));
	    }
	    

}

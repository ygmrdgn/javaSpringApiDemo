package com.example.apiDeneme;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class cevapController {
	
	@Autowired
	cevapRepository cr;
	
	@Autowired
	soruRepository sr;
	
	 @GetMapping("/sorular/{soruId}/cevaplar")
	    public List<cevapModel> getCevaplarBySoruId(@PathVariable Long soruId) {
	        return cr.findBySoruId(soruId);
	    }

	    @PostMapping("/sorular/{soruId}/cevaplar")
	    public cevapModel addCevapModel(@PathVariable Long soruId,
	                            @Valid @RequestBody cevapModel cevap) {
	        return sr.findById(soruId)
	                .map(soru -> {
	                    cevap.setSoru(soru);
	                    return cr.save(cevap);
	                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + soruId));
	    }
	    
	    
	    @PutMapping("/sorular/{soruId}/cevaplar/{cevapId}")
	    public cevapModel updateAnswer(@PathVariable Long soruId,
	                               @PathVariable Long cevapId,
	                               @Valid @RequestBody cevapModel cm) {
	        if(!sr.existsById(soruId)) {
	            throw new ResourceNotFoundException("Question not found with id " + soruId);
	        }

	        return cr.findById(cevapId)
	                .map(cevap -> {
	                    cevap.setText(cm.getText());
	                    return cr.save(cevap);
	                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + cevapId));
	    }

	    
	    @DeleteMapping("/sorular/{soruId}/cevaplar/{cevapId}")
	    public ResponseEntity<?> deleteCevapModel(@PathVariable Long soruId,
	                                          @PathVariable Long cevapId) {
	        if(!sr.existsById(soruId)) {
	            throw new ResourceNotFoundException("Question not found with id " + soruId);
	        }

	        return cr.findById(cevapId)
	                .map(cevap -> {
	                    cr.delete(cevap);
	                    return ResponseEntity.ok().build();
	                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + cevapId));

	    }

}

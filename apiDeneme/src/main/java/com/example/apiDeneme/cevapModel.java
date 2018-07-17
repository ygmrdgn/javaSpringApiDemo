package com.example.apiDeneme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cevaplar")
public class cevapModel extends denetimModel {
   

	@Id
    @GeneratedValue(generator = "cevap_generator")
    @SequenceGenerator(
            name = "cevap_generator",
            sequenceName = "cevap_sequence",
            initialValue = 1000
    )
    private Long id;
	

    @Column(columnDefinition = "text")
    
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soru_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private soruModel soru;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public soruModel getSoru() {
		return soru;
	}

	public void setSoru(soruModel soru) {
		this.soru = soru;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cevapModel other = (cevapModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
    
}

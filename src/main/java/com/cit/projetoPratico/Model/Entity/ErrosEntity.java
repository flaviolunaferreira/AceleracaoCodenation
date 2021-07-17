package com.cit.projetoPratico.Model.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Views.Public.class)
public class ErrosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Public.class)
	private Long id;
	@JsonView(Views.Public.class)
	private LevelEnum level;
	@JsonView(Views.Public.class)
	private String description;
	@JsonView(Views.Log.class)
	private String log;
	@JsonView(Views.Public.class)
	private String origin;
	@JsonView(Views.Public.class)
	private LocalDate date;
		
}

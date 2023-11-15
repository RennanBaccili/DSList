package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;

public record GameMinDto(
		Long id,
		String title,
		Integer year,
		String imgUrl,
		String shortDescription
		) {


	public GameMinDto(Game entity) {
		this(entity.getId(),entity.getTitle(),entity.getYear(),entity.getImgUrl(),entity.getShortDescription());
	}
	
	  public GameMinDto(GameMinProjection projection) {
	        this(
	            projection.getId(),
	            projection.getTitle(),
	            projection.getYear(),
	            projection.getImgUrl(),
	            projection.getShortDescription()
	        );
	    }
}

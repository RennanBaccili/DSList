package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;

public record GameDTO(
		 Long id,
		 String title,
		 Integer year,
		 String genre,
		 String platforms,
		 Double score,
		 String imgUrl,
		 String shortDescription,
		 String longDescription) {
	
    public GameDTO(Game game) {
        // Aqui você pode acessar os campos do objeto Game e passá-los para o construtor
        this(game.getId(), game.getTitle(), game.getYear(), game.getGenre(), game.getPlatforms(), game.getScore(),
                game.getImgUrl(), game.getShortDescription(), game.getLongDescription());
    }


}

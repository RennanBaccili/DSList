package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repository.GameListRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Transactional(readOnly = true)
	public GameListDTO findById(Long id) {
		GameList result = repository.findById(id).get();
		return new GameListDTO(result);
	}
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> games = repository.findAll();																// kd game sera estanciado no GameMinDto
		List<GameListDTO> dto = games.stream().map(game -> new GameListDTO(game)).toList();
		return dto;
	}
	
	
}

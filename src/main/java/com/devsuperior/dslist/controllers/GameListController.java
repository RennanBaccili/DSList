package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDto;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService service;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GameListDTO> findById(@PathVariable Long id){
		GameListDTO result = service.findById(id);
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping
	public ResponseEntity<List<GameListDTO>> findAll(){
		List<GameListDTO> result = service.findAll();
		return ResponseEntity.ok(result);
	}
	
	//Metodo busca de game

	@GetMapping(value = "/{listId}/games")
	public ResponseEntity<List<GameMinDto>> findAll(@PathVariable Long listId){
		List<GameMinDto> result = gameService.findGameByIdList(listId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/{listId}/games")
	public void move(@PathVariable Long listId,@RequestBody ReplacementDTO body){
		service.move(listId, body.sourceIndex(), body.destionationIndex());
	}
}

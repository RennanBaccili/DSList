package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repository.GameListRepository;
import com.devsuperior.dslist.repository.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Autowired
	private GameRepository gameRepository;
	
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
	
	@Transactional  
	public void move(Long listId,int sourceIndex,int destinationIndex) {
		//vou selecionar a lista, seja lista de games 1 ou 2 etc
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		//Vou remover o item da posição atual da lista que ele se encontra
		GameMinProjection obj = list.remove(sourceIndex); // removo ele da posição de origem
		// adiciono o item removido
		list.add(destinationIndex,obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		// os unicos numeros que serão afetados na lista são entre o menor indice alterado e o maior indice alterado
		int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;
		
		// esse for vai atualizar as posições dos numeros alterados no banco de dados
		// ele vai pegar cada posição desde o indice minimo 2 3 4 até o indice maximo, 
		// atualizando a ordem no banco de dados um por um
		for(int i = min; i<max; i++) {
			repository.updateBelongingPosition(listId, list.get(i).getId(), i);
			}
	
	}
	
	
}

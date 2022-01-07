package pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pokemon.dto.PokemonDTO;
import pokemon.service.PokemonService;
import pokemon.service.PokemonUpload;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private PokemonUpload pokemonUpload;
	private boolean dataSaved = false;
	
	@GetMapping
	public ResponseEntity<String> saveData() {
		pokemonUpload.saveData();
		dataSaved = true;
		return new ResponseEntity<String>("POKEMON DATA LOADED ON DB!", HttpStatus.OK);
	}
	@GetMapping(path="/heaviest", produces = "application/json")
	public ResponseEntity<List<PokemonDTO>> get5Heaviest() {
		if(!dataSaved) {
			saveData();
			dataSaved = true;
		}
		List<PokemonDTO> result = pokemonService.getHeaviestPokemons();
		return new ResponseEntity<List<PokemonDTO>>(result, HttpStatus.OK);
	}
	@GetMapping(path="/highest", produces = "application/json")
	public ResponseEntity<List<PokemonDTO>> get5Highest() {
		if(!dataSaved) {
			saveData();
			dataSaved = true;
		}
		List<PokemonDTO> result = pokemonService.getHighestPokemons();
		return new ResponseEntity<List<PokemonDTO>>(result, HttpStatus.OK);
	}
	@GetMapping(path="/experience", produces = "application/json")
	public ResponseEntity<List<PokemonDTO>> get5MoreBaseExperience() {
		if(!dataSaved) {
			saveData();
			dataSaved = true;
		}
		List<PokemonDTO> result = pokemonService.getMoreBaseExperiencePokemons();
		return new ResponseEntity<List<PokemonDTO>>(result, HttpStatus.OK);
	}
}

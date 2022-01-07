package pokemon.service;

import java.util.List;

import pokemon.dto.PokemonDTO;

public interface PokemonServiceInterface {
	public void start();
	public List<PokemonDTO> getHeaviestPokemons();
	public List<PokemonDTO> getHighestPokemons();
	public List<PokemonDTO> getMoreBaseExperiencePokemons();
}

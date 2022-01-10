package pokemon.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pokemon.dto.PokemonDTO;
import pokemon.entity.Pokemon;
import pokemon.repository.PokemonRepository;

@Service
public class PokemonService implements PokemonServiceInterface {
	@Autowired
	private PokemonUpload pokemonUpload;
	@Autowired
	private PokemonRepository pokemonRepo;
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public void start() {
		pokemonUpload.saveData();
	}

	@Override
	public List<PokemonDTO> getHeaviestPokemons() {
		List<PokemonDTO> resultHeaviestPokemons = pokemonRepo.findTop5ByOrderByWeightDesc()
											.get()
											.stream()
											.map(this::convertToDto)
									        .collect(Collectors.toList());
		return resultHeaviestPokemons;
	}

	@Override
	public List<PokemonDTO> getHighestPokemons() {
		List<PokemonDTO> resultHighestPokemons = pokemonRepo.findTop5ByOrderByHeightDesc()
											.get()
											.stream()
											.map(this::convertToDto)
									        .collect(Collectors.toList());
		return resultHighestPokemons;
	}

	@Override
	public List<PokemonDTO> getMoreBaseExperiencePokemons() {
		List<PokemonDTO> resultMoreBaseExperiencePokemons = pokemonRepo.findTop5ByOrderByBaseExperienceDesc()
														.get()
														.stream()
														.map(this::convertToDto)
												        .collect(Collectors.toList());
		return resultMoreBaseExperiencePokemons;
	}
	
	private PokemonDTO convertToDto(Pokemon pokemon) {
	    PokemonDTO pokemonDto = modelMapper.map(pokemon, PokemonDTO.class);
	    return pokemonDto;
	}
	
}

package pokemon.service;

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
		List<Pokemon> result = pokemonRepo.findTop5ByOrderByWeightDesc().get();
		return result.stream()
				.map(this::convertToDto)
		        .collect(Collectors.toList());
	}

	@Override
	public List<PokemonDTO> getHighestPokemons() {
		List<Pokemon> result = pokemonRepo.findTop5ByOrderByHeightDesc().get();
		return result.stream()
				.map(this::convertToDto)
		        .collect(Collectors.toList());
	}

	@Override
	public List<PokemonDTO> getMoreBaseExperiencePokemons() {
		List<Pokemon> result = pokemonRepo.findTop5ByOrderByBaseExperienceDesc().get();
		return result.stream()
				.map(this::convertToDto)
		        .collect(Collectors.toList());
	}
	
	private PokemonDTO convertToDto(Pokemon pokemon) {
	    PokemonDTO pokemonDto = modelMapper.map(pokemon, PokemonDTO.class);
	    return pokemonDto;
	}
	
}

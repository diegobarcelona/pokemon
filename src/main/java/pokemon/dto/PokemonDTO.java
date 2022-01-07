package pokemon.dto;

import lombok.Data;

@Data
public class PokemonDTO {
	private String name;
	private String color;
	private Double height;
	private Double weight;
	private Double baseExperience;
}

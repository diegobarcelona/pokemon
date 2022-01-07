package pokemon.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonConfiguration {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}

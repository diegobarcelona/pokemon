package pokemon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pokemon.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	Optional<List<Pokemon>> findTop5ByOrderByBaseExperienceDesc();
	Optional<List<Pokemon>> findTop5ByOrderByHeightDesc();
	Optional<List<Pokemon>> findTop5ByOrderByWeightDesc();
}

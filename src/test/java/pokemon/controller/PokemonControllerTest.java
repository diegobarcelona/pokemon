package pokemon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import pokemon.Application;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class PokemonControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
	void testSaveData() throws Exception {
		mvc.perform(get("/pokemon"))
      .andExpect(status().isOk());
	}
	
	@Test
	void testGet5Heaviest() throws Exception {
		mvc.perform(get("/pokemon/heaviest")
			.accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight").value(4600.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].weight").value(3000.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].weight").value(2350.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].weight").value(2200.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[4].weight").value(2100.0));
	}
	
	@Test
	void testGet5Highest() throws Exception {
		mvc.perform(get("/pokemon/highest")
			.accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].height").value(88.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].height").value(65.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].height").value(40.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].height").value(35.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[4].height").value(25.0));
	}
	
	@Test
	void testGet5MoreBaseExperience() throws Exception {
		mvc.perform(get("/pokemon/experience")
			.accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].baseExperience").value(395.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].baseExperience").value(306.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].baseExperience").value(270.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].baseExperience").value(270.0))
      .andExpect(MockMvcResultMatchers.jsonPath("$[4].baseExperience").value(261.0));
	}
	
}

package pokemon.service;

import java.time.Duration;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import pokemon.entity.Pokemon;
import pokemon.repository.PokemonRepository;

@Component
public class PokemonUpload {
	@Autowired
	private PokemonRepository pokemonRepo;
	private final RestTemplate restTemplate;
	private PokemonUpload(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500))
				.build();
	}
	private String getJSON(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }
	public void saveData() {
		String name = null, height = null, weight = null, baseExperience = null, color = null;
		for(int n=1; n<=10220; n++) {
			String url = "https://pokeapi.co/api/v2/pokemon/"+n;
			try {
				String json = this.getJSON(url);
				JSONObject obj = new JSONObject(json);
				Iterator<?> keys = obj.keys();
				while(keys.hasNext()) {
					String key = keys.next().toString();
					if(key.equals("name"))
						name = obj.get("name").toString();
					else if(key.equals("height"))
						height = obj.get("height").toString();
					else if(key.equals("base_experience"))
						baseExperience = obj.get("base_experience").toString();
					else if(key.equals("weight"))
						weight = obj.get("weight").toString();
					else if(key.equals("game_indices")) {
						JSONArray arr = obj.getJSONArray(key);
						try {
							JSONObject subObj = (JSONObject) arr.get(0);
							Iterator<?> keysB = subObj.keys();
							while(keysB.hasNext()) {
								String keyB = keysB.next().toString();
								if(keyB.equals("version")) {
									JSONObject subSubObj = subObj.getJSONObject(keyB);
									Iterator<?> keysC = subSubObj.keys();
									while(keysC.hasNext()) {
										String keyC = keysC.next().toString();
										if(keyC.equals("name")) {
											color = subSubObj.get("name").toString();
										}
									}
								}
							}
						}catch(JSONException ex) {
							color = null;
						}
					}
				}
				if(color!=null&&color.equals("red")) {
					Pokemon pokemon = new Pokemon();
					pokemon.setBaseExperience(Double.valueOf(baseExperience));
					pokemon.setColor(color);
					pokemon.setHeight(Double.valueOf(height));
					pokemon.setName(name);
					pokemon.setWeight(Double.valueOf(weight));
					pokemonRepo.save(pokemon);
				}
				else if(color!=null&&!color.equals("red"))
					break;
					
			} catch(HttpClientErrorException ex) {}
		}
	}
}

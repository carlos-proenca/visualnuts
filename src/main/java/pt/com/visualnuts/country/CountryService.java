package pt.com.visualnuts.country;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryService {
	
	public List<Country> getCountriesFromJson() throws StreamReadException, DatabindException, IOException{
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("database.json");
		return new ObjectMapper().readValue(is, new TypeReference<List<Country>>(){});
	}

	public int getCountrySize(List<Country> countries) {
		return countries.size();
	}

	public CountrySummaryDto getCountriesSummary() throws StreamReadException, DatabindException, IOException {
		List<Country> countriesFromJson = this.getCountriesFromJson();
		return CountrySummaryDto.builder()
				.countriesNumber(getCountriesQuantity(countriesFromJson))
				.countryMostLanguagesGe(getCountryMostLanguageGe(countriesFromJson))
				.numberOfOfficialLanguages(getNumberOfficialLanguages(countriesFromJson))
				.countryHighestOfficialLanguages(getCountryHighestOfficialLanguage(countriesFromJson))
				.mostOfficialLanguage(getCountryWithMoreLanguages(countriesFromJson))
				.build();
	}

	private String getCountryWithMoreLanguages(List<Country> countriesFromJson) {
		List<String> allLanguages = countriesFromJson.stream().map(Country::getLanguages).flatMap( list -> list.stream()).collect(Collectors.toList());
		Map<String, Long> languagesCounted = allLanguages.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		return languagesCounted.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
	}

	private Country getCountryHighestOfficialLanguage(List<Country> countriesFromJson) {
		return countriesFromJson.stream().sorted((x, y) -> Integer.compare(y.getLanguages().size(),x.getLanguages().size())).findFirst().get();
	}

	private long getNumberOfficialLanguages(List<Country> countriesFromJson) {
		return countriesFromJson.stream().map(Country::getLanguages).flatMap( list -> list.stream()).distinct().count();
	}

	private Country getCountryMostLanguageGe(List<Country> countriesFromJson) {
		List<Country> countriesGe = countriesFromJson.stream().filter(country -> country.getLanguages().contains("de")).collect(Collectors.toList());
		return countriesGe.stream().sorted((x, y) -> Integer.compare(y.getLanguages().size(),x.getLanguages().size())).findFirst().get();
	}

	private long getCountriesQuantity(List<Country> countriesFromJson) {
		return countriesFromJson.stream().distinct().count();
	}
}

package pt.com.visualnuts.country;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountrySummaryDto {
	private Long countriesNumber;
	private Country countryMostLanguagesGe;
	private Long numberOfOfficialLanguages;
	private Country countryHighestOfficialLanguages;
	private String mostOfficialLanguage;
}

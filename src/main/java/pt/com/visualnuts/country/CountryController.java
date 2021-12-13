package pt.com.visualnuts.country;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = "Countries API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
	
	private final CountryService service;
	@ApiOperation("Returns coutries list")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Success"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Error"),
		})
	@GetMapping()
	public ResponseEntity<List<Country>> getCountries() throws StreamReadException, DatabindException, IOException {
		return ResponseEntity.ok().body(this.service.getCountriesFromJson());
	}
	
	
	@ApiOperation("Returns language and country checks")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Success"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Error"),
		})
	@GetMapping("summary")
	public ResponseEntity<CountrySummaryDto> getCountriesSummary() throws StreamReadException, DatabindException, IOException {
		return ResponseEntity.ok().body(this.service.getCountriesSummary());
	}

}

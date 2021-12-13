package pt.com.visualnuts.number;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = "Number Verifier API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/numbers-validate")
public class NumberVerifierController {

	private final NumberVerifierService service;
	
	@ApiOperation("Returns the list of numbers but when it is divided by 3 "
			+ "the word Visual appears and when it is 5 Nuts and for both Visual Nuts")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message =  "Success"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal Error"),
		})
	@GetMapping("/{number}")
	public ResponseEntity<List<String>> validateNumber(@PathVariable("number") Integer numberToValidate) {
		return ResponseEntity.ok().body(this.service.getVisualNutsListFromNumber(numberToValidate));
	}
}

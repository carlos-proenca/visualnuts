package pt.com.visualnuts.country;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Country {
	@ApiModelProperty(value = "Código do pais")
	private String country;
	@ApiModelProperty(value =  "Linguagens do pais")
	private List<String> languages;
}

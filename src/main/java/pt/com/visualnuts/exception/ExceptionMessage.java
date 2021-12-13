package pt.com.visualnuts.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionMessage {
	private int statusCode;
	private String message;
}

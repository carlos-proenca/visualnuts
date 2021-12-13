package pt.com.visualnuts.number;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NumberVerifierService {
	
	public List<String> getVisualNutsListFromNumber(Integer numberToVerify) {
		var visualNutsList = new ArrayList<String>();
		validateNumber(numberToVerify);
		
		for (int currentNumber = 1; currentNumber <= numberToVerify; currentNumber++) {
			String result = "";
			if (isDivisibleByThree(currentNumber)) {
				result ="Visual ";
			}

			if (isDivisibleByFive(currentNumber)) {
				result = result.concat("Nuts");
			}

			if(result.isBlank()) {
				visualNutsList.add(String.valueOf(currentNumber));
			}else {
				visualNutsList.add(result);
			}
		}
		return visualNutsList;
	}

	private Boolean isDivisibleByFive(int currentNumber) {
		return currentNumber % 5 == 0;
	}

	private Boolean isDivisibleByThree(int currentNumber) {
		return currentNumber % 3 == 0;
	}

	private void validateNumber(Integer numberToVerify) {
		if(numberToVerify == null || numberToVerify < 1) {
			throw new IllegalArgumentException("Please provide a valid number");
		}
	}
}

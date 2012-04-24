package com.github.rabid_fish;

import https.github_com.rabid_fish.MathOperationPortType;
import https.github_com.rabid_fish.math.MathOperationRequest;
import https.github_com.rabid_fish.math.MathOperationResponse;
import https.github_com.rabid_fish.math.ObjectFactory;

import java.math.BigDecimal;
import java.util.List;

public class MathOperationWebServiceImpl implements MathOperationPortType {

	@Override
	public MathOperationResponse processMathOperation(MathOperationRequest mathOperationRequestPart) {
		
//		String operation = mathOperationRequestPart.getOperation();
		
		List<BigDecimal> arguments = mathOperationRequestPart.getArgument();
		BigDecimal result = arguments.get(0);
		
		for (int i = 1; i < arguments.size(); i++) {
			result = result.add(arguments.get(i));
		}
		
		ObjectFactory objectFactory = new ObjectFactory();
		MathOperationResponse response = objectFactory.createMathOperationResponse();
		response.setError("");
		response.setValue(result);
		
		return response;
	}

}

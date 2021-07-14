package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.internal.xml.config.ValidationBootstrapParameters;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class ValidatorUtils<T> {

	private static Validator validator;
	
	static {
		ValidatorFactory factory = Validation.byDefaultProvider()
											 .configure()
											 .messageInterpolator(
												new ResourceBundleMessageInterpolator(
															new PlatformResourceBundleLocator("kr.or.ddit.msgs.errorMessage")
														)
													 
													 ).buildValidatorFactory()  ;
		
		validator =factory.getValidator();
	}
	
	public boolean validate(T target , Map<String , List<String>> errors , Class<?>...groups) {
		Set<ConstraintViolation<T>>violations = validator.validate(target, groups);
		for(ConstraintViolation<T> singletonViolation : violations) {
			String key = singletonViolation.getPropertyPath().toString(); //경로를 문자로 출력
			String message = singletonViolation.getMessage();
			List<String> messages = errors.get(key); //문자를 받아와서 리스트 꺼내옴 
			//하나의 조건에서도 여러 검증이 있을 수 있어서 List 
			if(messages== null) {
				messages  = new ArrayList<>();
				errors.put(key , messages);
			}
			messages.add(message);
		}
		return violations.size()==0;
	}
	
	
	
	
}

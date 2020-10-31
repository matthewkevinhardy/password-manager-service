package to.uk.mkhardy.passwordmanager.service.utils;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlankListElementValidator implements ConstraintValidator<BlankListElementConstraint, List<String>> {

	@Override
	public boolean isValid(List<String> list, ConstraintValidatorContext context) {
		if(list.isEmpty()) {
			return false;
		}
		boolean emptyElement = list.stream().anyMatch(e->e.trim().isEmpty());
		return !emptyElement;
	}
    
}

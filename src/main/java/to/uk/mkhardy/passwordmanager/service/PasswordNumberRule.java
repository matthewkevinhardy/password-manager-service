package to.uk.mkhardy.passwordmanager.service;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public final class PasswordNumberRule extends PasswordRule {
	
	public PasswordNumberRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
	}

	public boolean isValidPassword(String password) throws PasswordRuleException {
		
		boolean containsNumber = false;
		
		for(char c:password.toCharArray()) {
			if(c>='0' && c<='9') {
				containsNumber=true;
				break;
			}
		}
		
		if(!containsNumber) {
			throw new PasswordRuleException(this);
		}
		
		return true;
	}

}

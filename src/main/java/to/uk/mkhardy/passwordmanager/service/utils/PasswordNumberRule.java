package to.uk.mkhardy.passwordmanager.service.utils;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;

public final class PasswordNumberRule extends PasswordRule {
	
	public PasswordNumberRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
	}

	public boolean isValidPassword(String password) {
		
		boolean containsNumber = false;
		
		for(char c:password.toCharArray()) {
			if(c>='0' && c<='9') {
				containsNumber=true;
				break;
			}
		}
		
		return containsNumber;
	}

}

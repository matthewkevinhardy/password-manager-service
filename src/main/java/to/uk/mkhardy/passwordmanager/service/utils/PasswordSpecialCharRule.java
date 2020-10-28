package to.uk.mkhardy.passwordmanager.service.utils;

import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;

public final class PasswordSpecialCharRule extends PasswordRule {
	
	public PasswordSpecialCharRule(String descriptionKey, String errorMessageKey) {
		super(descriptionKey, errorMessageKey);
	}

	public boolean isValidPassword(String password) {
		
		boolean containsSpecialChar = false;
		
		loop:for(char c:password.toCharArray()) {
			switch(c) {
				case '@': containsSpecialChar=true;break loop;
				case '%': containsSpecialChar=true;break loop;
				case '+': containsSpecialChar=true;break loop;
				case '\\': containsSpecialChar=true;break loop;
				case '/': containsSpecialChar=true;break loop;
				case '\'': containsSpecialChar=true;break loop;
				case '!': containsSpecialChar=true;break loop;
				case '#': containsSpecialChar=true;break loop;
				case '$': containsSpecialChar=true;break loop;
				case '^': containsSpecialChar=true;break loop;
				case '?': containsSpecialChar=true;break loop;
				case ':': containsSpecialChar=true;break loop;
				case '.': containsSpecialChar=true;break loop;
				case '(': containsSpecialChar=true;break loop;
				case ')': containsSpecialChar=true;break loop;
				case '{': containsSpecialChar=true;break loop;
				case '}': containsSpecialChar=true;break loop;
				case '[': containsSpecialChar=true;break loop;
				case ']': containsSpecialChar=true;break loop;
				case '~': containsSpecialChar=true;break loop;
				case '-': containsSpecialChar=true;break loop;
				case '_': containsSpecialChar=true;break loop;
				case '*': containsSpecialChar=true;break loop;
				default: containsSpecialChar=false;
			}
		}
		
		return containsSpecialChar;
	}

}

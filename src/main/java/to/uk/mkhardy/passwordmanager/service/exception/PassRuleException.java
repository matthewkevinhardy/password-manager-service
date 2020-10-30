package to.uk.mkhardy.passwordmanager.service.exception;

import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;

public class PassRuleException extends RuntimeException {

	private static final long serialVersionUID = 7330893745583973558L;
	
	private PasswordRuleException passwordRuleException;
	
	public PassRuleException(PasswordRuleException e) {
		super(e);
		this.passwordRuleException=e;
	}
	
	public PasswordRuleException getPasswordRuleException() {
		return this.passwordRuleException;
	}
}

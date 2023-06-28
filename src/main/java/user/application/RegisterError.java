package user.application;

import shared.domain.valueobject.DomainError;


public class RegisterError extends DomainError {
    
    public RegisterError(String msg){
        super(msg);
    }

}

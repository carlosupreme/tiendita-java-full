package user.domain.entities;

import shared.domain.valueobject.InvalidArgument;

@SuppressWarnings("serial")
public class InvalidPassword extends InvalidArgument {

    public InvalidPassword(String msg) {
        super(msg);
    }

}

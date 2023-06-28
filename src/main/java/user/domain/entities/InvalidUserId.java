package user.domain.entities;

import shared.domain.valueobject.InvalidArgument;

@SuppressWarnings("serial")
public class InvalidUserId extends InvalidArgument {

    public InvalidUserId(String errorMessage) {
        super(errorMessage);
    }

}

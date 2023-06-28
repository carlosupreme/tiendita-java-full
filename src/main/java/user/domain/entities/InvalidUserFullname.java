package user.domain.entities;

import shared.domain.valueobject.InvalidArgument;

@SuppressWarnings("serial")
public class InvalidUserFullname extends InvalidArgument {

    public InvalidUserFullname(String errorMessage) {
        super(errorMessage);
    }

}

package user.domain.entities;

import shared.domain.valueobject.InvalidArgument;

@SuppressWarnings("serial")
public class InvalidUsername extends InvalidArgument {

    public InvalidUsername(String msg) {
        super(msg);
    }

}

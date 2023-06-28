package user.domain.entities;

import shared.domain.valueobject.LongValueObject;

public class UserId extends LongValueObject {

    public UserId(Long value) {
        super(value);
        validate(value);
    }

    public static void validate(Long value) {
        if (null == value || value <= 0) {
            throw new InvalidUserId("Id debe ser mayor a 0");
        }
    }

}

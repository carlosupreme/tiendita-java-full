package shared.domain.valueobject;

import user.domain.entities.InvalidUserId;

public class IdLongValueObject extends LongValueObject {

    public IdLongValueObject(Long value) {
        super(value);
        validate(value);
    }

    public static void validate(Long value) {
        if (null == value || value <= 0) {
            throw new InvalidUserId("Id debe ser mayor a 0");
        }
    }

}

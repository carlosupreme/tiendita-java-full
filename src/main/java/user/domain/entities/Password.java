package user.domain.entities;

import org.mindrot.jbcrypt.BCrypt;
import shared.domain.valueobject.StringValueObject;


public class Password extends StringValueObject {

    public Password(String value) {
        super(value);
    }

    public static Password hash(String password) {
        return new Password(BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    public static boolean check(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}

package shared.domain.valueobject;

import java.util.Objects;

public abstract class ValueObject<T> {

    private final T value;

    public ValueObject(T value) {
        this.value = value;
    }
    
    public T value(){
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final ValueObject<?> that = (ValueObject<?>) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

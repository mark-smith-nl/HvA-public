package hva.ads.college.week08.builder;

public abstract class Builder<T> {

    protected final T instance;

    public Builder(T instance) {
        this.instance = instance;
    }
}

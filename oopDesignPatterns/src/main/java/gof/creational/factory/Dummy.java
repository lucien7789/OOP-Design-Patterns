package gof.creational.factory;

public interface Dummy {

    default String getName() {
        return this.getClass().getSimpleName();
    }
}

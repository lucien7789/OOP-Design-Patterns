package gof.structural.bridge;

public class Monster extends Entity {
    public Monster() {
        super(new Claws());
    }
}

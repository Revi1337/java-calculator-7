package calculator.model;

public class Token {

    private final int cost;

    public Token(String cost) {
        this.cost = Integer.parseInt(cost);
    }

    public int getCost() {
        return cost;
    }
}
package calculator.model;

import java.util.Objects;

public class Token {

    private final int cost;

    public Token(String cost) {
        this.cost = parsePositiveInt(cost);
    }

    public int getCost() {
        return cost;
    }

    private int parsePositiveInt(String value) {
        try {
            if (value.isEmpty()) {
                return 0;
            }
            int integer = Integer.parseInt(value);
            if (integer < 0) {
                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
            }
            return integer;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 사용할 수 있습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Token token)) {
            return false;
        }
        return cost == token.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cost);
    }

}

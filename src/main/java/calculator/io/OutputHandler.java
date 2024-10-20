package calculator.io;

public class OutputHandler {

    public void showEntryMessage() {
        System.out.println(IoMessage.ENTRY.getDescription());
    }

    public void showCalculatedValue(int cost) {
        System.out.printf((IoMessage.RESULT.getDescription()) + "\n", cost);
    }
}
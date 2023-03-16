package helpers;

public enum Operation {
    ADD(1),
    REMOVE(2),
    UPDATE(3),
    VIEW(4),
    VIEW_ALL(5);

    public final int value;
    Operation(int value) {
        this.value = value;
   }

}

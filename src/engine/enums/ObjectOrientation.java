package engine.enums;

public enum ObjectOrientation {
    LEFT ("Left", -3, 0),
    UP_LEFT ("UpLeft", -2, -2),
    UP ("Up", 0, -3),
    UP_RIGHT ("UpRight", 2, -2),
    RIGHT ("Right", 3, 0),
    DOWN_RIGHT ("DownRight", 2, 2),
    DOWN ("Down", 0, 3),
    DOWN_LEFT ("DownLeft", -2, 2);


    private String string;
    public int x;
    public int y;

    ObjectOrientation(String string, int x, int y) {
        this.string = string;
        this.x = x;
        this.y = y;
    }

    public String getString() {
        return string;
    }
}

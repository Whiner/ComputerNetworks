package Generator;

public class Section {

    private String Name;
    private NetworkType Type;
    private int x, y;
    private int width, height;
    private int Cells_Count_X;
    private int Cells_Count_Y;

    public Section(String name, NetworkType type, int x, int y, int width, int height, int cells_Count_X, int cells_Count_Y) {
        Name = name;
        Type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        Cells_Count_X = cells_Count_X;
        Cells_Count_Y = cells_Count_Y;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}

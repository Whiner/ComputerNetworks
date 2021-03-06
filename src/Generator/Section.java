package Generator;

public class Section {

    private String Name;
    private boolean fill;
    private NetworkType Type;
    private int BeginCell_X, BeginCell_Y;
    private int Cells_Count_X;
    private int Cells_Count_Y;

    public int getBeginCell_X() {
        return BeginCell_X;
    }

    public void setBeginCell_X(int beginCell_X) {
        BeginCell_X = beginCell_X;
    }

    public int getBeginCell_Y() {
        return BeginCell_Y;
    }

    public void setBeginCell_Y(int beginCell_Y) {
        BeginCell_Y = beginCell_Y;
    }

    public int getCells_Count_X() {
        return Cells_Count_X;
    }

    public void setCells_Count_X(int cells_Count_X) {
        Cells_Count_X = cells_Count_X;
    }

    public int getCells_Count_Y() {
        return Cells_Count_Y;
    }

    public void setCells_Count_Y(int cells_Count_Y) {
        Cells_Count_Y = cells_Count_Y;
    }

    public Section(String name, NetworkType type, int beginCell_X, int beginCell_Y, int cells_Count_X, int cells_Count_Y) {
        Name = name;
        Type = type;
        BeginCell_X = beginCell_X;
        BeginCell_Y = beginCell_Y;
        Cells_Count_X = cells_Count_X;
        Cells_Count_Y = cells_Count_Y;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public boolean isFill() {
        return fill;
    }

    public void setFill() {
        this.fill = true;
    }
}

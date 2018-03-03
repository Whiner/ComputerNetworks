package Generator;

public class Field {
    private int Cells_Count_X;
    private int Cells_Count_Y;
    private int SizeFieldInPx_X;
    private int SizeFieldInPx_Y;
    private static Field instance;

    public static Field GetInstance(){
        Field localInstance = instance;
        if (localInstance == null) {
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new Field();
            }
        }
        return localInstance;
    }

    private Field(int cells_Count_X, int cells_Count_Y, int sizeFieldInPx_X, int sizeFieldInPx_Y) {
        Cells_Count_X = cells_Count_X;
        Cells_Count_Y = cells_Count_Y;
        SizeFieldInPx_X = sizeFieldInPx_X;
        SizeFieldInPx_Y = sizeFieldInPx_Y;
    }

    private Field() {}

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

    public int getSizeFieldInPx_X() {
        return SizeFieldInPx_X;
    }

    public void setSizeFieldInPx_X(int sizeFieldInPx_X) {
        SizeFieldInPx_X = sizeFieldInPx_X;
    }


    public int getSizeFieldInPx_Y() {
        return SizeFieldInPx_Y;
    }

    public void setSizeFieldInPx_Y(int sizeFieldInPx_Y) {
        SizeFieldInPx_Y = sizeFieldInPx_Y;
    }
}

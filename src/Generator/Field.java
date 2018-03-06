package Generator;

public class Field {


    private int NodeSize;
    private int Cells_Count;
    private int SizeBorderInPx;


    public int getCells_Count() {
        return Cells_Count;
    }

    public void setCells_Count(int cells_Count) {
        Cells_Count = cells_Count;
    }

    public int getSizeBorderInPx() {
        return SizeBorderInPx;
    }

    public void setSizeBorderInPx(int sizeBorderInPx) {
        SizeBorderInPx = sizeBorderInPx;
    }



    public int getNodeSize() {
        return NodeSize;
    }

    private void NodeSize() {
        try{
            NodeSize = SizeBorderInPx / Cells_Count * 2;
        }
        catch (Exception e){

        }
    }




    private static Field instance;

    public static Field GetInstance(){
        if (instance == null) {
            instance = new Field();
        }
        return instance;
    }


    private Field() {}


}

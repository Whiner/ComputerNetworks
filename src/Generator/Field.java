package Generator;

public class Field {

    private int NodeSize;
    private int Cells_Count;
    private int SizeBorderInPx;

    private int LAN_Field_Count;
    private int LAN_Field_Width;

    private int WAN_Field_Height;

    public int getLAN_Field_Count() {
        return LAN_Field_Count;
    }

    public void setLAN_Field_Count(int LAN_Field_Count) {
        this.LAN_Field_Count = LAN_Field_Count;
        try {
            LAN_Width_Calc();
            WAN_Height_Calc();
        }
        catch (Exception e){

        }
    }




    private void LAN_Width_Calc(){
        LAN_Field_Width = SizeBorderInPx / LAN_Field_Count;
    }
    private void WAN_Height_Calc(){
        WAN_Field_Height = SizeBorderInPx / 2;
    }

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

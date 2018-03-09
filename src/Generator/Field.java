package Generator;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private int NodeSize;
    private int Cells_Count;
    private int SizeBorderInPx;

    private Section WAN_Section;
    private List<Section> LAN_Sections;
    private int MaxSectionsCount;

    public int getLAN_Field_Count() {
        return LAN_Sections.size();
    }

    public Section getWAN_Section() {
        return WAN_Section;
    }

    public List<Section> getLAN_Sections() {
        return LAN_Sections;
    }

    public int getMaxSectionsCount() {
        return MaxSectionsCount;
    }

    public boolean AddWAN_Section(){
        if(WAN_Section != null)
            return false;
        WAN_Section = new Section("", NetworkType.WAN, 0, 0, SizeBorderInPx, SizeBorderInPx / 2,
                Cells_Count, Cells_Count / 2);
        return true;
    }
    public boolean AddLAN_Section(){
        if(LAN_Sections == null)
            LAN_Sections = new ArrayList<>();
        if(LAN_Sections.size() >= MaxSectionsCount)
            return false;
        LAN_Sections.add(new Section("" + LAN_Sections.size() + 1,
                NetworkType.LAN,
                SizeBorderInPx - SizeBorderInPx / (LAN_Sections.size() + 1),
                SizeBorderInPx / 2,
                SizeBorderInPx / (LAN_Sections.size() + 1),
                SizeBorderInPx / 2,
                Cells_Count / (LAN_Sections.size() + 1),
                Cells_Count / 2));
        return true;
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

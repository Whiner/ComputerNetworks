package Generator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TopologyGenerator {

    private static Network GenerateNodes(NetworkType Type, int NodeCount, int MaxNodeRelationsCount, Section section) throws GeneratorException {
        if(NodeCount <= 0)
            throw new GeneratorException("Node count must be greater 0");
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5");
        if(section == null)
            throw new GeneratorException("Null section");
        int t_NodeCount = NodeCount;
        if(section.getCells_Count_X()*section.getCells_Count_Y() < NodeCount)
            t_NodeCount = section.getCells_Count_X()*section.getCells_Count_Y();

        Random r = new Random();
        Network network = new Network();
        network.setType(Type);

        try {
            network.CreateParentNode( // создает родительский элемент
                    section.getBeginCell_X()
                            + r.nextInt(section.getCells_Count_X()),
                    section.getBeginCell_Y());
            network.getNodes().get(network.getNodes().size() - 1).setMaxRelationsCount(MaxNodeRelationsCount);
        } catch (Exception e){
            throw new GeneratorException("Generate falled with message: \n" + e.getMessage(), 303);
        }
        for (int i = 0; i < t_NodeCount - 1; i++){ // генерация остальных
            if(network.isAllHaveMaxRelations())
                break;
            int Additionally_Connect_Count;
            if(i < MaxNodeRelationsCount)  //до того как будет достаточно узлов для максимального количества связей будет i
                Additionally_Connect_Count = r.nextInt(i + 1);
            else
                Additionally_Connect_Count = r.nextInt(MaxNodeRelationsCount);
            Direction t_direction = Direction.RandomDirection();  // направление
            int ParentID = r.nextInt(i + 1); //рандомный родительский узел
            Node P_Node = network.GetNodeByID(ParentID);
            if(P_Node != null) {
                if(P_Node.getMaxRelationsCount() <= P_Node.getRelationsCount())
                {
                    i--;
                    continue;
                }
                int X = Direction.Check_X_by_Direction(P_Node, t_direction);
                int Y = Direction.Check_Y_by_Direction(P_Node, t_direction);
                if(X > section.getBeginCell_X() + section.getCells_Count_X() ||
                        X < section.getBeginCell_X() ||
                        Y > section.getBeginCell_Y() + section.getCells_Count_Y() ||
                        Y < section.getBeginCell_Y())
                {
                    i--;
                    continue;
                }
                Node N_By_Coord = network.GetByCoord(X, Y);
                if(N_By_Coord != null)
                {
                    i--;
                    continue;
                }
            }
            else
            {
                i--;
                continue;
            }

            List<Integer> Additionally_Connect_ID = new ArrayList<>(); //с какими соединить еще
            int RandomConnectNode;
            if(i != 0 && Additionally_Connect_Count != 0)
            {
                for (int j = 0; j < Additionally_Connect_Count; j++) {
                    do {
                        RandomConnectNode = r.nextInt(i + 1);
                    } while (RandomConnectNode == ParentID || Additionally_Connect_ID.contains(RandomConnectNode));
                    Additionally_Connect_ID.add(RandomConnectNode);
                }

            }
            boolean NewNode;
            try {
                NewNode = network.AddNode(t_direction, ParentID, Additionally_Connect_ID);
                if(!NewNode)
                {
                    i--;
                    continue;
                }
                network.GetLastNode().setMaxRelationsCount(MaxNodeRelationsCount);
            }
            catch (GeneratorException e){
                //if(e.getCodeError() == 104)
                //    continue;
                if(e.getCodeError() == 106)
                    i--;
                else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        section.setFill();
        return network;

    }

    public static Network GenerateWAN(int NodeCount, int MaxNodeRelationsCount) throws Exception {
        if(NodeCount <= 0)
            throw new GeneratorException("Node count must be greater 0", 300);
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5", 301);
        if(Field.GetInstance().getWAN_Section() == null)
            throw new GeneratorException("WAN Section is null", 305);


        Field.GetInstance().getWAN_Section().setFill();


        return GenerateNodes(
                NetworkType.WAN,
                NodeCount,
                MaxNodeRelationsCount,
                Field.GetInstance().getWAN_Section());


    }

    public static Network GenerateLAN(int NodeCount, int MaxNodeRelationsCount) throws GeneratorException {
        if(NodeCount <= 0)
            throw new GeneratorException("Node count must be greater 0", 300);
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5", 301);
        if(Field.GetInstance().getLAN_Sections().isEmpty())
            throw new GeneratorException("LAN sections is null");

        Section t_Section = null;
        for(Section t: Field.GetInstance().getLAN_Sections()){
            if(!t.isFill())
            {
                t_Section = t;
                break;
            }
        }
        if(t_Section == null) {
            throw new GeneratorException("All LAN sections is full");
        }


        return GenerateNodes(
                NetworkType.LAN,
                NodeCount,
                MaxNodeRelationsCount,
                t_Section);

    }
}

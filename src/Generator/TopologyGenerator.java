package Generator;


import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

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
        try { // переделать под section
            network.CreateParentNode( // создает родительский элемент
                    section.getBeginCell_X()
                            + r.nextInt(section.getBeginCell_X() + section.getCells_Count_X()),
                    section.getBeginCell_Y());
        } catch (Exception e){
            throw new GeneratorException("Generate falled with message: \n" + e.getMessage(), 303);
        }

        for (int i = 0; i < t_NodeCount - 1; i++){ // генерация остальных
            int ConnectCount;
            if (i == 0)
                ConnectCount = 0;
            else{
                int MaxRel;
                if(i < MaxNodeRelationsCount)  //до того как будет достаточно узлов для максимального количества связей будет i
                    MaxRel = i;
                else
                    MaxRel = MaxNodeRelationsCount - 1;
                ConnectCount = r.nextInt(MaxRel);
            }

            Direction t_direction = Direction.RandomDirection();  // направление
            int ParentID = r.nextInt(i + 1); //рандомный родительский узел

            Node P_Node = network.GetNodeByID(ParentID);
            if(P_Node != null) {
                int X = Direction.Check_X_by_Direction(P_Node, t_direction); // check this
                int Y = Direction.Check_Y_by_Direction(P_Node, t_direction);

                if(X > section.getBeginCell_X() + section.getCells_Count_X() ||
                        X < section.getBeginCell_X() ||
                        Y > section.getBeginCell_Y() + section.getCells_Count_Y() ||
                        Y < section.getBeginCell_Y())
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

            List<Integer> ConnectID = new ArrayList<>(); //с какими соединить еще
            int RandomConnectNode;
            if(i != 0 && ConnectCount != 0)
            {
                for (int j = 0; j < ConnectCount; j++) {
                    do {
                        RandomConnectNode = r.nextInt(i + 1);
                    } while (RandomConnectNode == ParentID || ConnectID.contains(RandomConnectNode));
                    ConnectID.add(RandomConnectNode);
                }

            }
            boolean NewNode;
            try {
                switch (ConnectCount) {
                    case 0:
                        NewNode = network.AddNode(t_direction, ParentID);
                        break;
                    case 1:
                        NewNode = network.AddNode(t_direction, ParentID, ConnectID.get(0));
                        break;
                    case 2:
                        NewNode = network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1));
                        break;
                    case 3:
                        NewNode = network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1),
                                ConnectID.get(2));
                        break;
                    case 4:
                        NewNode = network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1),
                                ConnectID.get(2),
                                ConnectID.get(3));
                        break;
                    default:
                        throw new GeneratorException("Unknown error", 666);
                }
                if(!NewNode)
                {
                    i--;
                    continue;
                }
            }
            catch (GeneratorException e){
                if(e.getCodeError() == 106)
                    i--;
                else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        network.setMaxNodeRelations(MaxNodeRelationsCount);
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

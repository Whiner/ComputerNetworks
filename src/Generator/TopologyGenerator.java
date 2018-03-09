package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TopologyGenerator {

    private static void GenerateNodes(Network network, int NodeCount, int MaxNodeRelationsCount, Section section) throws GeneratorException {
        if(network == null)
            throw new GeneratorException("Null network");
        if(NodeCount <= 0)
            throw new GeneratorException("Node count must be greater 0");
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5");
        if(section == null)
            throw new GeneratorException("Null section");

        Random r = new Random();

        try {
            network.CreateParentNode(
                    Field.GetInstance().getCells_Count() / 4
                            + r.nextInt(Field.GetInstance().getCells_Count() / 2),
                    0);
        } catch (Exception e){
            throw new GeneratorException("Generate falled with message: \n" + e.getMessage(), 303);
        }

        for (int i = 0; i < NodeCount - 1; i++){
            int ConnectCount;
            if (i == 0)
                ConnectCount = 0;
            else
                ConnectCount = r.nextInt(MaxNodeRelationsCount - 1);
            Direction t_direction = Direction.RandomDirection();
            int ParentID = r.nextInt(i + 1);

            Node P_Node = network.GetNodeByID(ParentID);
            int X = Direction.Check_X_by_Direction(P_Node, t_direction); // check this
            int Y = Direction.Check_Y_by_Direction(P_Node, t_direction);

            if(X > section.getX() + section.getWidth() ||
                    X < section.getX() ||
                    Y > section.getY() + section.getHeight() ||
                    Y < section.getY())
            {
                i--;
                continue;
            }


            List<Integer> ConnectID = new ArrayList<>(); //с какими соединить еще
            int RandomConnectNode = 0;
            if(i != 0)
            {
                for (int j = 0; j < ConnectCount; j++) {
                    do {
                        RandomConnectNode = r.nextInt(i + 1);
                    } while (RandomConnectNode == ParentID || ConnectID.contains(RandomConnectNode));
                }
                ConnectID.add(RandomConnectNode);
            }

            try {
                switch (ConnectCount) {
                    case 0:
                        network.AddNode(t_direction, ParentID);
                        break;
                    case 1:
                        network.AddNode(t_direction, ParentID, ConnectID.get(0));
                        break;
                    case 2:
                        network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1));
                        break;
                    case 3:
                        network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1),
                                ConnectID.get(2));
                        break;
                    case 4:
                        network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1),
                                ConnectID.get(2),
                                ConnectID.get(3));
                        break;
                    default:
                        throw new GeneratorException("Unknown error", 666);
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






    }
    public static Network GenerateWAN(int NodeCount, int MaxNodeRelationsCount) throws Exception {
        if(NodeCount <= 0)
            throw new GeneratorException("Node count must be greater 0", 300);
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5", 301);

        Network network = new Network(NetworkType.WAN, NodeCount);
        GenerateNodes(network, NodeCount, MaxNodeRelationsCount, Field.GetInstance().getWAN_Section());

        network.setMaxNodeRelations(MaxNodeRelationsCount);
        return network;


    }

    public static void GenerateLAN(Topology topology,  int MaxNodeCount, int MaxNodeRelationsCount, int RelationsWithWAN){
        //TODO
    }
}

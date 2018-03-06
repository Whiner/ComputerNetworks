package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TopologyGenerator {

    public static void GenerateWAN(Topology topology, int MaxNodeCount, int MaxNodeRelationsCount) throws Exception {
        if(MaxNodeCount <= 0)
            throw new GeneratorException("Max node count must be greater 0", 300);
        if(MaxNodeRelationsCount <= 0 || MaxNodeRelationsCount > 5)
            throw new GeneratorException("Max node relations count must be greater 0 and less 5", 301);

        Network added_network = new Network(NetworkType.WAN, MaxNodeCount);

        Random r = new Random();
        try {
            added_network.CreateParentNode(
                    Field.GetInstance().getCells_Count() / 4
                            + r.nextInt(Field.GetInstance().getCells_Count() / 2),
                    0);
        }catch (Exception e){
            throw new GeneratorException("Generate falled with message: \n" + e.getMessage(), 303);
        }

        for (int i = 0; i < MaxNodeCount - 1; i++){ //err
            int ConnectCount = r.nextInt(MaxNodeRelationsCount - 1);
            Direction t_direction = Direction.RandomDirection();
            int ParentID = r.nextInt(i + 1);
            List<Integer> ConnectID = new ArrayList<>();
            int RandomConnectNode = 0;
            if(i != 0)
                for (int j = 0; j < ConnectCount; j++) {
                    do {
                        RandomConnectNode = r.nextInt(i + 1); // err
                    } while (RandomConnectNode == ParentID || ConnectID.contains(RandomConnectNode));
                }
            ConnectID.add(RandomConnectNode);
            try {
                switch (ConnectCount) {
                    case 0:
                        added_network.AddNode(t_direction, ParentID);
                        break;
                    case 1:
                        added_network.AddNode(t_direction, ParentID, ConnectID.get(0));
                        break;
                    case 2:
                        added_network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1));
                        break;
                    case 3:
                        added_network.AddNode(t_direction, ParentID,
                                ConnectID.get(0),
                                ConnectID.get(1),
                                ConnectID.get(2));
                        break;
                    case 4:
                        added_network.AddNode(t_direction, ParentID,
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
            }
        }

        added_network.setMaxNodeRelations(MaxNodeRelationsCount);
        topology.AddNetwork(added_network);


    }

    public static void GenerateLAN(Topology topology,  int MaxNodeCount, int MaxNodeRelationsCount, int RelationsWithWAN){
        //TODO
    }
}

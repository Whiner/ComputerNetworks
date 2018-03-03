package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        Network network = new Network();
        List<Node> nodes = network.getNodes();

        Field.GetInstance().setCells_Count_X(10);
        Field.GetInstance().setCells_Count_Y(10);
        Field.GetInstance().setSizeFieldInPx_X(100);
        Field.GetInstance().setSizeFieldInPx_Y(100);

        network.setType(NetworkType.WAN);
        network.AddNode(Direction.Up_Left, 0);
        network.AddNode(Direction.Up_Left, 0);
        network.AddNode(Direction.Up_Left, 1, 0);
        network.AddNode(Direction.Up_Left, 2,1, 2);
        network.AddNode(Direction.Up_Left, 3,1);

        for(Node t: nodes){
            for(NodeNavigation tt: t.getConnectedNodes())
            {
                System.out.println(tt.getNode().getCellNumber_X() + " "
                        + tt.getNode().getCellNumber_Y() + " "
                        + tt.getNode().getRelationsCount());
            }
            //System.out.println(t.getCellNumber_X() + " " + t.getCellNumber_Y());
            //System.out.println();
        }

    }
}

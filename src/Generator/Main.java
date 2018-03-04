package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        Network network = new Network();

        Field.GetInstance().setCells_Count_X(10);
        Field.GetInstance().setCells_Count_Y(10);
        Field.GetInstance().setSizeFieldInPx_X(100);
        Field.GetInstance().setSizeFieldInPx_Y(100);

        network.setType(NetworkType.WAN);

        network.AddNode(Direction.None, 0);//id 0
        network.AddNode(Direction.Left_Down, 0); // id 1
        network.AddNode(Direction.Right, 1, 0); // id 2
        network.AddNode(Direction.Down, 2,1, 2); // id 3
        network.AddNode(Direction.Up_Right, 3,0); // id 4

        List<Node> nodes = network.getNodes();
        for(Node t: nodes){
            System.out.println("ID : " + t.getID() + " \nConnected with: ");
            for (NodeNavigation tt: t.getConnectedNodes()){
                System.out.println(tt.getNode().getID());
            }
            //System.out.println(t.getCellNumber_X() + " " + t.getCellNumber_Y());
            //System.out.println();
        }

    }
}

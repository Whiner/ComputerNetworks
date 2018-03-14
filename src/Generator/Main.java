package Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) {


        Field.GetInstance().setCells_Count(10);
        Field.GetInstance().setSizeBorderInPx(1000);
        Field.GetInstance().setMaxSectionsCount(3);
        Field.GetInstance().AddWAN_Section();
        Field.GetInstance().CreateLAN_Sections(2);

        Topology t = new Topology();

        try {
            t.AddNetwork(TopologyGenerator.GenerateWAN(2,3));
            t.AddNetwork(TopologyGenerator.GenerateLAN(8, 4));
            t.AddNetwork(TopologyGenerator.GenerateLAN(8, 4));
        } catch (GeneratorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Network> networks = t.getNetworks();
        for (Network _t: networks){
            System.out.println("--------------------------------------------------------");
            System.out.println("Сеть " + _t.getType());
            List<Node> nodes = _t.getNodes();
            for (Node _tt: nodes){
                System.out.println("----------------------------");
                System.out.println("ID узла: " + _tt.getID() + " \nX = " + _tt.getCellNumber_X() + " Y = " + _tt.getCellNumber_Y());
                System.out.println("Соединения: ");
                for (NodeNavigation _ttt: _tt.getConnectedNodes()){
                    System.out.print(_ttt.getNode().getID() + " ");
                }
                System.out.println();
            }

        }

    }
}

package Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) {


        Field.GetInstance().setCells_Count(10);
        Field.GetInstance().setSizeBorderInPx(1000);

        Topology t = new Topology();

        try {
            TopologyGenerator.GenerateWAN(t, 6,3);
        } catch (GeneratorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Network> networks = t.getNetworks();
       /* for (Network _t: networks){
            System.out.println("Сеть " + _t.getType());
            List<Node> nodes = _t.getNodes();
            for (Node _tt: nodes){
                System.out.println("ID узла: " + _tt.getID());
                System.out.println("Соединения: ");
                for (NodeNavigation _ttt: _tt.getConnectedNodes()){
                    System.out.print(_ttt.getNode().getID() + " ");
                }
                System.out.println();
            }

        }*/

    }
}

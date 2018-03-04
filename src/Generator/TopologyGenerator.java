package Generator;

import java.util.List;

public class TopologyGenerator {
    private List<Network> networks;

    public void GenerateNetwork(NetworkType networkType){
        networks.add(new Network(networkType, 6));

    }
}

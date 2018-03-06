package Generator;

import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.List;

public class Topology {

    private List<Network> networks;
    private boolean WAN;
    private int LAN;

    public Topology(){
       networks = new ArrayList<>();
       LAN = 0;
       WAN = false;
    }

    public void AddNetwork(Network network) throws GeneratorException {
        if(network == null)
            throw new GeneratorException("Network is null pointer", 405);
        if(networks.contains(network))
            throw new GeneratorException("This network is already exist in topology", 400);
        if(network.getType() == NetworkType.WAN){
            if(WAN)
                throw new GeneratorException("WAN network is already exist in topology", 401);
            else
                WAN = true;
        }
        else
            LAN++;
        networks.add(network);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public boolean isWAN() {
        return WAN;
    }

    public void SetWAN() {
        this.WAN = true;
    }

    public int getLAN() {
        return LAN;
    }


}

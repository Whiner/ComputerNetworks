package Generator;

import java.util.List;

public class Node {
    private NetworkType networkType;
    private int RelationsCount;
    private List<NodeNavigation> ConnectedNodes;

    public void ConnectNode(Node node, Direction direction){
        ConnectedNodes.add(new NodeNavigation(node, direction));
    }
    public void DeleteConnectedNode(Node node){ // проверить на наличие и удалить
    }


    public Node(NetworkType networkType, int relationsCount, List<NodeNavigation> connectedNodes, int ID) {
        this.networkType = networkType;
        RelationsCount = relationsCount;
        ConnectedNodes = connectedNodes;
        this.ID = ID;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public int getRelationsCount() {
        return RelationsCount;
    }

    public void setRelationsCount(int relationsCount) {
        RelationsCount = relationsCount;
    }

    public List<NodeNavigation> getConnectedNodes() {
        return ConnectedNodes;
    }

    public void setConnectedNodes(List<NodeNavigation> connectedNodes) {
        ConnectedNodes = connectedNodes;
    }
}

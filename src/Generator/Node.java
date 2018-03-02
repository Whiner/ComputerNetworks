package Generator;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private NetworkType networkType;
    private int RelationsCount;
    private List<NodeNavigation> ConnectedNodes;
    private int Coord_X, Coord_Y;
    private int Size;
    private int ID;

    public void ConnectNode(Node node, Direction direction){ //connect with reverse
        NodeNavigation nodeNavigation = new NodeNavigation(node, direction);
        if(!ConnectedNodes.contains(nodeNavigation))
            ConnectedNodes.add(nodeNavigation);
        if(!node.ConnectedNodes.contains(this))
            node.ConnectedNodes.add(new NodeNavigation(this, direction.reverse()));
    }
    public boolean DeleteConnectedNode(Node node) {
        return ConnectedNodes.remove(node);
    }
    public Node GetNodeByDirection(Direction direction){
        for(NodeNavigation t: ConnectedNodes)
            if(t.getDirection() == direction)
                return t.getNode();
        return null;
    }
    public void CalculateSize(int FieldSizeInPixels, int LineSectionsCount) throws Exception {
        if(FieldSizeInPixels < 1 || LineSectionsCount < 1)
            throw new Exception("Incorrect value");
        else
            Size = FieldSizeInPixels / LineSectionsCount;
    }

    public Node() {
        ConnectedNodes = new ArrayList<>();
    }

    public Node(NetworkType networkType, int ID) {
        this.networkType = networkType;
        this.ID = ID;
    }

    public Node(NetworkType networkType, List<NodeNavigation> connectedNodes, int coord_x, int coord_y, int size, int ID) throws Exception {
        this.networkType = networkType;
        if(connectedNodes == null)
            throw new NullPointerException("Connected Nodes list is NULL");
        RelationsCount = connectedNodes.size();
        ConnectedNodes = connectedNodes;
        Coord_X = coord_x;
        Coord_Y = coord_y;
        if(size < 0)
            throw new Exception("Size must be greater than 0");
        Size = size;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public List<NodeNavigation> getConnectedNodes() {
        return ConnectedNodes;
    }

    public void setConnectedNodes(List<NodeNavigation> connectedNodes) {
        if(connectedNodes == null)
            throw new NullPointerException("Connected Nodes list is NULL");
        ConnectedNodes = connectedNodes;
        RelationsCount = connectedNodes.size();
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) throws Exception {
        if(size < 0)
            throw new Exception("Size must be greater than 0");
        Size = size;
    }

    public int getCoord_X() {
        return Coord_X;
    }

    public void setCoord_X(int coord_X) {
        Coord_X = coord_X;
    }

    public int getCoord_Y() {
        return Coord_Y;
    }

    public void setCoord_Y(int coord_Y) {
        Coord_Y = coord_Y;
    }
}

package Generator;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private NetworkType networkType;
    private int RelationsCount;
    private List<NodeNavigation> ConnectedNodes;
    private int CellNumber_X, CellNumber_Y;
    private int Size;
    private int ID;

    public Node(NetworkType networkType, int cellNumber_X, int cellNumber_Y, int ID) {
        this.networkType = networkType;
        CellNumber_X = cellNumber_X;
        CellNumber_Y = cellNumber_Y;
        this.ID = ID;
        ConnectedNodes = new ArrayList<>();
    }


    public void ConnectNode(Node node, Direction direction){ //connect with reverse
        if(node == null)
            throw  new NullPointerException("Node is null pointer");
        NodeNavigation nodeNavigation = new NodeNavigation(node, direction);
        if(!ConnectedNodes.contains(nodeNavigation)) // сравнение сделать. происходит сравнение адресов
        {
            ConnectedNodes.add(nodeNavigation);
            RelationsCount++;
        }
        try{
            if(!node.ConnectedNodes.contains(this))
            {
                node.ConnectedNodes.add(new NodeNavigation(this, direction.reverse()));
                node.RelationsCount++;
            }
            System.out.println("not null");
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }


    }
    public boolean DeleteConnectedNode(Node node) { //by id сделать
        RelationsCount--;
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
        RelationsCount = 0;
    }

    public Node(NetworkType networkType, int ID) {
        this.ConnectedNodes = new ArrayList<>();
        this.networkType = networkType;
        this.ID = ID;
        this.RelationsCount = 0;
    }

    public Node(NetworkType networkType, List<NodeNavigation> connectedNodes, int coord_x, int coord_y, int size, int ID) throws Exception {
        this.networkType = networkType;
        if(connectedNodes == null)
            throw new NullPointerException("Connected Nodes list is NULL");
        RelationsCount = connectedNodes.size();
        ConnectedNodes = connectedNodes;
        CellNumber_X = coord_x;
        CellNumber_Y = coord_y;
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

    public int getCellNumber_X() {
        return CellNumber_X;
    }

    public void setCellNumber_X(int cellNumber_X) {
        CellNumber_X = cellNumber_X;
    }

    public int getCellNumber_Y() {
        return CellNumber_Y;
    }

    public void setCellNumber_Y(int cellNumber_Y) {
        CellNumber_Y = cellNumber_Y;
    }
    public boolean equals(Node node){
        if(this == node)
            return true;
        return this.CellNumber_X == node.CellNumber_X
                && this.CellNumber_Y == node.CellNumber_Y;
    }

    public int getRelationsCount() {
        return RelationsCount;
    }

    public void setRelationsCount(int relationsCount) {
        RelationsCount = relationsCount;
    }
}

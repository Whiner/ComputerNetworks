package Generator;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private NetworkType Type;
    private List<Node> Nodes;
    private int MaxNodeCount;

    public boolean CheckID(int ID){
        boolean entered = false;
        for(Node t: Nodes) {
            int t_ID = t.getID();
            if(t_ID == ID)
                entered = true;
        }
       return entered;
    }

    public Node GetNodeByID(int ID){
        for(Node t: Nodes)
            if(t.getID() == ID)
                return t;
         return null;
    }

    public boolean AddNode(Direction direction, int ParentNodeID, int ... ConnectWith) throws Exception {
        if(Nodes.size() + 1 > MaxNodeCount)
            throw new Exception("Max Node counts");
        if(!CheckID(ParentNodeID))
            throw new Exception("Node with ID " + ParentNodeID + " are not exist in this network");
//        for (int i = 0; i < ConnectWith.length; i++)
//            if(!CheckID(ConnectWith[i]))
//                throw new Exception("Node with ID " + ConnectWith[i] + " are not exist in this network");
        Node t_Node = GetNodeByID(ParentNodeID);
        //проверка на существование в том направлении узла
        //связать все остальные


        return true;
    }


    public int size(){
        return Nodes.size();
    }

    public Network() {
        Nodes = new ArrayList<>();
        MaxNodeCount = -1;
    }

    public Network(NetworkType type, int maxNodeCount) {
        MaxNodeCount = maxNodeCount;
        Nodes = new ArrayList<>();
        Type = type;
    }

    public NetworkType getType() {
        return Type;
    }

    public void setType(NetworkType type) {
        Type = type;
    }

    public List<Node> getNodes() {
        return Nodes;
    }

    public void setNodes(List<Node> nodes) {
        Nodes = nodes;
    }
}

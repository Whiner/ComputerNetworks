package Generator;

import java.util.ArrayList;
import java.util.List;


public class Network {
    private NetworkType Type;
    private List<Node> Nodes;
    private int MaxNodeCount;

    public void setMaxNodeCount(int maxNodeCount) {
        MaxNodeCount = maxNodeCount;
    }

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

    public void CreateParentNode(int CellNumber_X, int CellNumber_Y) throws Exception {
        if(!Nodes.isEmpty())
            throw new Exception("Parent Node is already exist");
        if(CellNumber_X < 0 || CellNumber_Y < 0
                || CellNumber_X >= Field.GetInstance().getCells_Count_X()
                || CellNumber_Y >= Field.GetInstance().getCells_Count_Y())
            throw new Exception("Out from field borders. X = " + CellNumber_X + " Y = " + CellNumber_Y);
        Nodes.add(new Node(Type, CellNumber_X, CellNumber_Y, 0));

    }
    public void AddNode(Direction direction, int ParentNodeID, int ... ConnectWith) throws Exception {
        if(Nodes.isEmpty())
            throw new Exception("You must create parent node firstly");
        if(direction == Direction.None)
            throw new Exception("Node must have direction");
        if(Nodes.size() + 1 > MaxNodeCount && MaxNodeCount != -1)
            throw new Exception("Max Node counts");
        if(!CheckID(ParentNodeID))
            throw new Exception("Node with ID " + ParentNodeID + " are not exist in this network");

        Node ParentNode = GetNodeByID(ParentNodeID);
        int ID;
        Node Node_By_Direction = ParentNode.GetNodeByDirection(direction);
        if(Node_By_Direction == null)
        {
            int Cell_X = Direction.Check_X_by_Direction(ParentNode, direction);
            if(Cell_X < 0 || Cell_X >= Field.GetInstance().getCells_Count_X())
                throw new Exception("Out from field borders. Horizontal cell index is " + Cell_X);
            int Cell_Y = Direction.Check_Y_by_Direction(ParentNode, direction);
            if(Cell_Y < 0 || Cell_Y >= Field.GetInstance().getCells_Count_Y())
                throw new Exception("Out from field borders. Vertical cell index is " + Cell_Y);
            ID = Nodes.get(Nodes.size() - 1).getID() + 1;
            Nodes.add(new Node(Type, Cell_X, Cell_Y, ID));
        }
        else
            ID = Node_By_Direction.getID();

        Node LastAdded = Nodes.get(ID);
        ParentNode.ConnectNode(LastAdded, direction);

        for (int t: ConnectWith){
            Node ConnectingNode = GetNodeByID(t);
            if(ConnectingNode != null){
                Direction t_direction = Direction.CheckDirection(LastAdded, ConnectingNode);
                if(t_direction != null)
                    LastAdded.ConnectNode(ConnectingNode, t_direction);
            }
        }



    }


    public int Size(){
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

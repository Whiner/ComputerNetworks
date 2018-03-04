package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void AddNode(Direction direction, int ParentNodeID, int ... ConnectWith) throws Exception {
        if(Nodes.isEmpty())
        {
            Nodes.add(new Node(Type,
                    5/*new Random().nextInt(Field.GetInstance().getCells_Count_X())*/,
                    0, 0));
            return;
        }
        if(direction == Direction.None)
            throw new Exception("Node must to have direction");
        if(Nodes.size() + 1 > MaxNodeCount && MaxNodeCount != -1)
            throw new Exception("Max Node counts");
        if(!CheckID(ParentNodeID))
            throw new Exception("Node with ID " + ParentNodeID + " are not exist in this network");
//        for (int i = 0; i < ConnectWith.length; i++)
//            if(!CheckID(ConnectWith[i]))
//                throw new Exception("Node with ID " + ConnectWith[i] + " are not exist in this network");

        Node t_Node = GetNodeByID(ParentNodeID);
        if(t_Node.GetNodeByDirection(direction) == null)
        {
            int Cell_X = Direction.Check_X_by_Direction(t_Node, direction);
            if(Cell_X < 0)
                throw new Exception("Out from field borders. Horizontal cell index less 0");
            int Cell_Y =  Direction.Check_Y_by_Direction(t_Node, direction);
            if(Cell_Y < 0)
                throw new Exception("Out from field borders. Vertical cell index less 0");
            Nodes.add(new Node(Type, Cell_X, Cell_Y, Nodes.get(Nodes.size() - 1).getID() + 1));
        }
        t_Node.ConnectNode(Nodes.get(Nodes.size() - 1), direction);
        Node LastAdded = Nodes.get(Nodes.size() - 1);
        for (int t: ConnectWith){ // 3 add null exc
            t_Node = GetNodeByID(t); //проверки на существование
            Direction t_direction = Direction.CheckDirection(LastAdded, t_Node);
            LastAdded.ConnectNode(t_Node, t_direction);
           // if(t_Node != null)
                //t_Node.ConnectNode();
                // придумать че делать с направлениями. как определить где он находится в пространстве
                // как вариант - сразу делать с X и Y как номерами ячеек на экране

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

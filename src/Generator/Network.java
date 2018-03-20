package Generator;

import java.util.ArrayList;
import java.util.List;


public class Network {
    private NetworkType Type;
    private List<Node> Nodes;
    private int MaxNodeCount;
    public Node GetLastNode(){
        return Nodes.get(Nodes.size() - 1);
    }
    public void setMaxNodeRelations(int maxNodeRelations) throws GeneratorException {
        for(Node t: Nodes){
            t.setMaxRelationsCount(maxNodeRelations);
        }
    }
    public void setMaxNodeCount(int maxNodeCount) {
        MaxNodeCount = maxNodeCount;
    }

    public Node GetByCoord(int x, int y){
        for (Node t: Nodes){
            if(t.getCellNumber_X() == x)
                if(t.getCellNumber_Y() == y)
                    return t;
        }
        return null;
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
    public boolean isAllHaveMaxRelations(){
        for (Node t: Nodes){
            if(t.getRelationsCount() < t.getMaxRelationsCount())
                return false;
        }
        return true;
    }

    public boolean CheckIntersection(Node from, Node to){ //check
        int t_x = Math.abs(from.getCellNumber_X() - to.getCellNumber_X());
        int t_y = Math.abs(from.getCellNumber_Y() - to.getCellNumber_Y());
        if(t_x == 0){
            int start = Math.min(from.getCellNumber_Y(), to.getCellNumber_Y());
            for (int i = 1; i < t_y; i++){
                if(GetByCoord(from.getCellNumber_X(), start + i) != null)
                    return true;
            }
            return false;
        }
        else
            if(t_y == 0){
            int start = Math.min(from.getCellNumber_X(), to.getCellNumber_X());
            for (int i = 1; i < t_x; i++){
                if(GetByCoord(start + i, from.getCellNumber_Y()) != null)
                    return true;
            }
            return false;
        }
        else
            if(t_x == t_y) {
                int start = Math.min(from.getCellNumber_X(), to.getCellNumber_X());
                for (int i = 1; i < t_x; i++){
                    if(GetByCoord(start + i, start + i) != null)
                        return true;
                }
                return false;
        }
        return false;
    }

    public void CreateParentNode(int CellNumber_X, int CellNumber_Y) throws Exception {
        if(!Nodes.isEmpty())
            throw new GeneratorException("Parent Node is already exist", 100);
        if(CellNumber_X < 0 || CellNumber_Y < 0
                || CellNumber_X >= Field.GetInstance().getCells_Count()
                || CellNumber_Y >= Field.GetInstance().getCells_Count())
            throw new GeneratorException("Out from field borders. X = " + CellNumber_X + " Y = " + CellNumber_Y, 101);
        Nodes.add(new Node(Type, CellNumber_X, CellNumber_Y, 0));

    }

    /**между соедияемыми не должно быть других узлов. Иначе соединение проигнорируется*/
    public boolean AddNode(Direction direction, int ParentNodeID, List<Integer> ConnectWith) throws GeneratorException {
        if(Nodes.isEmpty())
            throw new GeneratorException("You must create parent node firstly", 102);
        if(direction == Direction.None)
            throw new GeneratorException("Node must have direction", 103);
        if(Nodes.size() + 1 > MaxNodeCount)
            throw new GeneratorException("Max node counts", 104);
        if(!CheckID(ParentNodeID))
            throw new GeneratorException("Node with ID " + ParentNodeID + " are not exist in this network", 105);


        boolean NewNode = true;
        Node ParentNode = GetNodeByID(ParentNodeID);

        int ID;
        Node Node_By_Direction = ParentNode.GetNodeByDirection(direction);
        if(Node_By_Direction == null)
        {
            int Cell_X = Direction.Check_X_by_Direction(ParentNode, direction);
            if(Cell_X < 0 || Cell_X >= Field.GetInstance().getCells_Count())
                throw new GeneratorException("Out from field borders. Horizontal cell index is " + Cell_X, 106);
            int Cell_Y = Direction.Check_Y_by_Direction(ParentNode, direction);
            if(Cell_Y < 0 || Cell_Y >= Field.GetInstance().getCells_Count())
                throw new GeneratorException("Out from field borders. Vertical cell index is " + Cell_Y, 106);
            ID = Nodes.get(Nodes.size() - 1).getID() + 1;
            Nodes.add(new Node(Type, Cell_X, Cell_Y, ID));
        }
        else
        {
            ID = Node_By_Direction.getID();
            NewNode = false;
        }

        Node LastAdded = Nodes.get(ID);
        try {
            ParentNode.ConnectNode(LastAdded, direction);
        }
        catch (GeneratorException e){
            if(e.getCodeError() == 202){
                Nodes.remove(LastAdded);
            }
            throw e;
        }

        if(ConnectWith != null)
            for (int t: ConnectWith){
                Node ConnectingNode = GetNodeByID(t);
                if(ConnectingNode != null){
                    if(CheckIntersection(LastAdded, ConnectingNode))
                        continue;
                    Direction t_direction = Direction.CheckDirection(LastAdded, ConnectingNode);
                    if(t_direction != null)
                        LastAdded.ConnectNode(ConnectingNode, t_direction);
                }
            }

        return NewNode;
    }


    public int Size(){
        return Nodes.size();
    }

    public Network() {
        Nodes = new ArrayList<>();
        MaxNodeCount = 8965545;
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

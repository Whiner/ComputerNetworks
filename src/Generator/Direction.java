package Generator;

public enum Direction {

    Up{
        @Override
        public Direction reverse() {
            return Down;
        }
    },
    Up_Right{
        @Override
        public Direction reverse() {
            return Left_Down;
        }
    },
    Right{
        @Override
        public Direction reverse() {
            return Left;
        }
    },
    Right_Down{
        @Override
        public Direction reverse() {
            return Up_Left;
        }
    },
    Down{
        @Override
        public Direction reverse() {
            return Up;
        }
    },
    Left_Down{
        @Override
        public Direction reverse() {
            return Up_Right;
        }
    },
    Left{
        @Override
        public Direction reverse() {
            return Right;
        }
    },
    Up_Left{
        @Override
        public Direction reverse() {
            return Right_Down;
        }
    };
    public abstract Direction reverse();
    public static Direction CheckDirection(Node from, Node to){
        if(from.equals(to))
            return null;
        if (from.getCellNumber_X() > to.getCellNumber_X()
                && from.getCellNumber_Y() == to.getCellNumber_Y())
            return Right;
        if (from.getCellNumber_X() > to.getCellNumber_X()
                && from.getCellNumber_Y() < to.getCellNumber_Y())
            return Right_Down;
        if (from.getCellNumber_X() == to.getCellNumber_X()
                && from.getCellNumber_Y() < to.getCellNumber_Y())
            return Down;
        if (from.getCellNumber_X() < to.getCellNumber_X()
                && from.getCellNumber_Y() > to.getCellNumber_Y())
            return Left_Down;
        if (from.getCellNumber_X() < to.getCellNumber_X()
                && from.getCellNumber_Y() == to.getCellNumber_Y())
            return Left;
        if (from.getCellNumber_X() < to.getCellNumber_X()
                && from.getCellNumber_Y() < to.getCellNumber_Y())
            return Up_Left;
        if (from.getCellNumber_X() == to.getCellNumber_X()
                && from.getCellNumber_Y() > to.getCellNumber_Y())
            return Up;
        if (from.getCellNumber_X() > to.getCellNumber_X()
                && from.getCellNumber_Y() > to.getCellNumber_Y())
            return Up_Right;

        return null;
    }
}

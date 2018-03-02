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
}

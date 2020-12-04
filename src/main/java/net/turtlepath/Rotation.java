package net.turtlepath;

public enum Rotation {
    NORTH(0, 1, 3, 2),
    EAST (1, 2, 0, 3),
    SOUTH(2, 3, 1, 0),
    WEST (3, 0, 2, 1);

    private final int id, right, left, opposite;

    Rotation(int id, int right, int left, int opposite) {
        this.id = id;
        this.right = right;
        this.left = left;
        this.opposite = opposite;
    }

    public static Rotation getRot(int id) {
        switch (id & 3) {
            case 0:
                return Rotation.NORTH;
            case 1:
                return Rotation.EAST;
            case 2:
                return Rotation.SOUTH;
            default:
                return Rotation.WEST;
        }
    }

    public int getId() {
        return id;
    }

    public Rotation getRight() {
        return getRot(right);
    }

    public Rotation getLeft() {
        return getRot(left);
    }

    public Rotation getOpposite() {
        return getRot(opposite);
    }
}

package net.turtlepath;

import java.util.Objects;

public class BlockPos {

    private final int x, y, z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public BlockPos add(int x, int y, int z) {
        return new BlockPos(getX() + x, getY() + y, getZ() + z);
    }

    public BlockPos offset(Rotation rot, int n) {
        switch (rot) {
            case NORTH:
                return add(n, 0, 0);
            case EAST:
                return add(0, 0, n);
            case SOUTH:
                return add(0, 0, -n);
            default:
                return add(-n, 0, 0);
        }
    }

    public BlockPos up() {
        return add(0, 1, 0);
    }

    public BlockPos down() {
        return add(0, -1, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPos blockPos = (BlockPos) o;
        return x == blockPos.x && y == blockPos.y && z == blockPos.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "BlockPos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

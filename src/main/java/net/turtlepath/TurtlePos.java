package net.turtlepath;

import java.util.Objects;

public class TurtlePos {

    private final BlockPos pos;
    private final Rotation rot;

    public TurtlePos(BlockPos pos, Rotation rot) {
        this.pos = Objects.requireNonNull(pos);
        this.rot = Objects.requireNonNull(rot);
    }

    public TurtlePos(int x, int y, int z, Rotation rot) {
        this(new BlockPos(x, y, z), rot);
    }

    public BlockPos getPos() {
        return pos;
    }

    public Rotation getRot() {
        return rot;
    }

    public TurtlePos setPos(BlockPos pos) {
        return new TurtlePos(pos, getRot());
    }

    public TurtlePos setRot(Rotation rot) {
        return new TurtlePos(getPos(), rot);
    }

    public TurtlePos rotRight() {
        return setRot(getRot().getRight());
    }

    public TurtlePos rotLeft() {
        return setRot(getRot().getLeft());
    }

    public TurtlePos rotOpposite() {
        return setRot(getRot().getOpposite());
    }

    public TurtlePos posAdd(int x, int y, int z) {
        return setPos(getPos().add(x, y, z));
    }

    public TurtlePos posOffset(int n) {
        return setPos(getPos().offset(getRot(), n));
    }

    public TurtlePos up() {
        return setPos(getPos().up());
    }

    public TurtlePos down() {
        return setPos(getPos().down());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurtlePos turtlePos = (TurtlePos) o;
        return pos.equals(turtlePos.pos) && rot == turtlePos.rot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, rot);
    }

    @Override
    public String toString() {
        return "TurtlePos{" +
                "pos=" + pos +
                ", rot=" + rot +
                '}';
    }
}

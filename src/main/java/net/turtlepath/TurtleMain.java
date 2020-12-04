package net.turtlepath;

import java.util.*;
import java.util.function.Predicate;

public class TurtleMain {

    public static final Predicate<BlockPos> POS_IS_IN_RANGE =
            pos -> pos.getX() >= 0 && pos.getY() >= 0 && pos.getZ() >= 0 &&
                    pos.getX() <= 15 && pos.getY() <= 15 && pos.getZ() <= 15;

    public static final Predicate<TurtlePos> TUR_POS_IS_IN_RANGE = turtlePos -> POS_IS_IN_RANGE.test(turtlePos.getPos());

    public static void main(String[] args) {
        System.out.println("Calculating...");
        long time = System.nanoTime();

        TurtlePos start = new TurtlePos(0, 0, 0, Rotation.NORTH);
        TurtlePos goal = new TurtlePos(2, 0, 2, Rotation.SOUTH);

        HashMap<TurtlePos, TurtlePos> path = new HashMap<>();
        HashSet<TurtlePos> checked = new HashSet<>(), unchecked = new HashSet<>();

        unchecked.add(start);

        mainLoop: while (!unchecked.isEmpty()) {
            for (TurtlePos turtlePos : new HashSet<>(unchecked)) {
                for (TurtlePos neigh : getNeighbours(turtlePos)) {
                    if (!unchecked.contains(neigh) && !checked.contains(neigh)) {
                        path.put(neigh, turtlePos);

                        if (neigh.equals(goal)) {
                            break mainLoop;
                        }

                        unchecked.add(neigh);
                    }
                }
                checked.add(turtlePos);
                unchecked.remove(turtlePos);
            }
        }

        ArrayList<TurtlePos> pathPositions = new ArrayList<>();

        pathPositions.add(goal);

        TurtlePos last = path.get(goal);

        pathPositions.add(last);

        while (!last.equals(start)) {
            last = path.get(last);
            pathPositions.add(last);
        }

        Collections.reverse(pathPositions);

        long nano = System.nanoTime() - time;

        System.out.println(nano);
        System.out.println((double)nano / 1000D);
        System.out.println((double)nano / 1000000D);
        System.out.println((double)nano / 1000000000D);

        System.out.println("Path:");

        printPath(pathPositions);
    }

    private static void printPath(ArrayList<TurtlePos> positions) {
        for (int i = 0; i < positions.size(); i++) {
            int prev = i - 1;
            TurtlePos pos = positions.get(i);

            String actionName = "Start";

            if (prev >= 0) {
                TurtlePos prevPos = positions.get(prev);

                if (pos.getPos().equals(prevPos.getPos())) {
                    if (prevPos.rotLeft().equals(pos)) {
                        actionName = "Turn Left";
                    } else if (prevPos.rotRight().equals(pos)) {
                        actionName = "Turn Right";
                    } else if (prevPos.rotOpposite().equals(pos)) {
                        actionName = "Turn Opposite";
                    } else {
                        actionName = "Rot ????";
                    }
                } else {
                    if (prevPos.posOffset(1).equals(pos)) {
                        actionName = "Forward";
                    } else if (prevPos.posOffset(-1).equals(pos)) {
                        actionName = "Back";
                    } else if (prevPos.up().equals(pos)) {
                        actionName = "Up";
                    } else if (prevPos.down().equals(pos)) {
                        actionName = "Down";
                    } else {
                        actionName = "Move ????";
                    }
                }
            }

            System.out.println(pos + " (" + actionName + ")");
        }
    }

    private static void addIf(ArrayList<TurtlePos> list, TurtlePos pos) {
        if (TurtleMain.TUR_POS_IS_IN_RANGE.test(pos)) {
            list.add(pos);
        }
    }

    public static ArrayList<TurtlePos> getNeighbours(TurtlePos turtlePos) {
        ArrayList<TurtlePos> list = new ArrayList<>();
        list.add(turtlePos.rotOpposite());
        list.add(turtlePos.rotRight());
        list.add(turtlePos.rotLeft());

        addIf(list, turtlePos.posOffset(1));
        addIf(list, turtlePos.up());
        addIf(list, turtlePos.down());

        return list;
    }
}

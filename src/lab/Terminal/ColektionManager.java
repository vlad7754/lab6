package lab.Terminal;

import lab.lab34.Rocket;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class ColektionManager {

        TreeSet<Rocket> ts = new TreeSet<>();
   public Set<Rocket> treeSet =  Collections.synchronizedSet(ts);
    private static ColektionManager instance;

    private ColektionManager() {
    }

    public static ColektionManager getInstance() {
        if (instance == null) {
            instance = new ColektionManager();
        }
        return instance;
    }

}

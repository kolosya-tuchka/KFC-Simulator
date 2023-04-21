package NewDayNewGame.Core.Physics;

import NewDayNewGame.Core.BitMask;
import NewDayNewGame.Core.Pair;
import NewDayNewGame.Core.Physics.Collisions.Collider;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Physics {
    private static Physics instance;
    private ArrayList<Collider> colliders;
    private BitMask[] layersMask;
    private TreeMap<String, Integer> layers;
    private HashSet<Pair<Collider, Collider>> collisions;

    public void onSceneLoad() {
        colliders.clear();
        collisions.clear();
    }

    public Physics(@NotNull BitMask @NotNull [] layersMask, @NotNull TreeMap<String, Integer> layers) {
        instance = this;
        this.layersMask = layersMask.clone();
        this.layers = (TreeMap<String, Integer>)layers.clone();
        collisions = new HashSet<>();
        colliders = new ArrayList<>();
    }

    public static Physics getInstance() {
        return instance;
    }

    public void addCollider(Collider collider) {
        colliders.add(collider);
    }

    public void removeCollider(Collider collider) {
        colliders.remove(collider);
    }

    public boolean hasLayer(String layer) {
        return layers.containsKey(layer);
    }

    public void update() throws IOException {
        ArrayList<Collider> toDestroy = new ArrayList<>();
        for (var collider : colliders) {
            if (collider.gameObject().destroyFlag) {
                toDestroy.add(collider);
                continue;
            }
            collider.updateShape();
        }
        for (var collider : toDestroy) {
            colliders.remove(collider);
        }
        for (int i = 0; i < colliders.size(); ++i) {
            Collider first = colliders.get(i);
            var body1 = first.gameObject().getComponent(PhysBody.class);
            if (!first.enabled()) {
                continue;
            }
            int layer1 = layers.get(first.gameObject().layer());
            for (int j = i + 1; j < colliders.size(); ++j) {
                Collider second = colliders.get(j);
                var body2 = second.gameObject().getComponent(PhysBody.class);
                if (body1 == null && body2 == null) {
                    continue;
                }
                if (!second.enabled()) {
                    continue;
                }
                int layer2 = layers.get(second.gameObject().layer());
                if (layersMask[layer1].getBit(layer2) == 0) {
                    continue;
                }

                var pair = new Pair<>(first, second);

                if (first.isTouching(second)) {
                    if (hasCollision(pair)) {
                        if (first.isTrigger || second.isTrigger) {
                            first.gameObject().onTriggerStay(second);
                            second.gameObject().onTriggerStay(first);
                        } else {
                            first.gameObject().onCollisionStay(second);
                            second.gameObject().onCollisionStay(first);
                        }
                    } else {
                        collisions.add(pair);
                        if (first.isTrigger || second.isTrigger) {
                            first.gameObject().onTriggerEnter(second);
                            second.gameObject().onTriggerEnter(first);
                        } else {
                            first.gameObject().onCollisionEnter(second);
                            second.gameObject().onCollisionEnter(first);
                        }
                    }
                } else if (hasCollision(pair)) {
                    removeCollision(pair);
                    if (first.isTrigger || second.isTrigger) {
                        first.gameObject().onTriggerExit(second);
                        second.gameObject().onTriggerExit(first);
                    } else {
                        first.gameObject().onCollisionExit(second);
                        second.gameObject().onCollisionExit(first);
                    }
                }
            }
        }
    }

    public String[] getLayers() {
        return layers.keySet().toArray(new String[0]);
    }

    private boolean hasCollision(Pair<Collider, Collider> pair) {
        for (var other : collisions) {
            if (pair.equals(other)) {
                return true;
            }
        }
        return false;
    }

    private void removeCollision(Pair<Collider, Collider> pair) {
        for (var other : collisions) {
            if (pair.equals(other)) {
                collisions.remove(other);
                return;
            }
        }
    }
}

package NewDayNewGame.Core.Physics;

import NewDayNewGame.Core.BitMask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class PhysicsCreator {
    private BitMask[] layersMask;
    private TreeMap<String, Integer> layers;

    public PhysicsCreator loadLayersMask(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        int size = scanner.nextInt();
        layersMask = new BitMask[size];
        for (int i = 0; i < size; ++i) {
            int[] bits = new int[size];
            for (int j = 0; j < size; ++j) {
                bits[j] = scanner.nextInt();
            }
            layersMask[i] = new BitMask(bits);
        }
        return this;
    }

    public PhysicsCreator loadLayers(File input) throws FileNotFoundException {
        layers = new TreeMap<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String layer = scanner.next();
            int num = scanner.nextInt();
            layers.put(layer, num);
        }
        return this;
    }

    public Physics createPhysics() {
        return new Physics(layersMask, layers);
    }
}

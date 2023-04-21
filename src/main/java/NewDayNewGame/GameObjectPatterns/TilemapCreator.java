package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Pair;
import NewDayNewGame.Core.Vector2;
import NewDayNewGame.GameObjectPatterns.Tiles.TileCreator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class TilemapCreator extends GameObjectCreator {
    private String tilesConfigPath = "src/main/java/NewDayNewGame/LevelConfigs/tilesConfig.txt", tilemapPath = "src/main/java/NewDayNewGame/LevelConfigs/tilemap.txt";
    private final TreeMap<String, Pair<TileCreator, String>> tiles;
    private String sortingLayer = "default";
    private int orderInLayer = 0;
    private Vector2 tileSize = new Vector2(1, 1);

    public TilemapCreator() {
        tiles = new TreeMap<>();
    }

    public TilemapCreator setTilemapPath(String tilemapPath) {
        this.tilemapPath = tilemapPath;
        return this;
    }

    public TilemapCreator setTilesConfigPath(String tilesConfigPath) {
        this.tilesConfigPath = tilesConfigPath;
        return this;
    }

    public TilemapCreator setRenderingOrder(String sortingLayer, int orderInLayer) {
        this.sortingLayer = sortingLayer;
        this.orderInLayer = orderInLayer;
        return this;
    }

    public TilemapCreator setTileSize(Vector2 tileSize) {
        this.tileSize = new Vector2(tileSize);
        return this;
    }

    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject tilemapParent = super.create();
        File tilesConfig = new File(tilesConfigPath);
        File tilemap = new File(tilemapPath);
        if (!tilemap.exists() || !tilesConfig.exists()) {
            return null;
        }
        Scanner scanner = new Scanner(tilesConfig);
        while (scanner.hasNext()) {
            String id = scanner.next();
            TileCreator tileCreator = (TileCreator) Class.forName(scanner.next()).newInstance();
            tiles.put(id, new Pair<>(tileCreator, scanner.next()));
        }
        scanner = new Scanner(tilemap);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                String id = scanner.next();
                if (!tiles.containsKey(id)) {
                    continue;
                }
                Vector2 position = (new Vector2(j - width / 2, i * 2 - height / 2)).scale(tileSize);
                var tileSettings = tiles.get(id);
                var tile = tileSettings.first.setImage(tileSettings.second).setSize(tileSize).setRenderingOrder(sortingLayer, orderInLayer).
                setName("tile " + (i + 1) + " " + (j + 1)).setParent(tilemapParent.transform()).create(new Vector2(position));
            }
        }
        return tilemapParent;
    }
}

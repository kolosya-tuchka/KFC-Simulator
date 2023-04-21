package NewDayNewGame.Core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Debug {
    private static Debug instance;
    private final OutputStream outputStream;

    public Debug(OutputStream outputStream) {
        this.outputStream = outputStream;
        instance = this;
    }

    public static Debug getInstance() {
        return instance;
    }

    public void Log(@NotNull String message) throws IOException {
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
    }
}

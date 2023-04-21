package NewDayNewGame.Core;

public class Time {
    private static Time instance;
    private double _deltaTime;
    public double timeScale = 1;

    private double frameStart;
    private double frameEnd;

    public double deltaTime() {
        return _deltaTime * timeScale;
    }

    public Time() {
        frameStart = System.currentTimeMillis();
        instance = this;
    }

    public static Time getInstance() {
        return instance;
    }

    protected void startFrame() {
        double temp = System.currentTimeMillis();
        _deltaTime = (temp - frameStart) / 1000;
        frameStart = temp;
    }

    protected void endFrame() {
        frameEnd = System.currentTimeMillis();
    }

    protected void nextFrame() throws InterruptedException {
        long sleepValue = (long)Math.max((1.0 / Game.getInstance().targetFPS * 1000 - (frameEnd - frameStart)), 0);
        Thread.sleep(sleepValue);
    }
}

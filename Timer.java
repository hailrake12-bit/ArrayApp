package algorithmsnstructures;

public class Timer {
    private long startTime;
    private long stopTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        stopTime = System.currentTimeMillis();
    }

    public String getTime(){
        return stopTime-startTime + "ms";
    }
}

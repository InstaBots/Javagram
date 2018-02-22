package net.steppschuh.instabots.actions;

public abstract class Action {

    protected long timestamp;

    public Action() {
        timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "timestamp=" + timestamp +
                '}';
    }

    public abstract void perform();

    /*
        Getter & Setter
     */

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}

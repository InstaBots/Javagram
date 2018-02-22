package net.steppschuh.instabots.database;

import net.steppschuh.instabots.actions.Action;

import java.util.List;

public interface Database {

    public void persistPerformedAction(Action action);

    public void persistQueuedAction(Action action);

    public List<? extends Action> getQueuedActions();

}

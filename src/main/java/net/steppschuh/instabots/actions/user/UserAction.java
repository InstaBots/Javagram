package net.steppschuh.instabots.actions.user;

import net.steppschuh.instabots.actions.Action;

public abstract class UserAction extends Action {

    protected String userId;

    public UserAction(String userId) {
        this.userId = userId;
    }

    /*
        Getter & Setter
     */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

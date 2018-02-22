package net.steppschuh.instabots.actions.post;

public class CommentPostAction extends PostAction {

    protected String comment;

    public CommentPostAction(String postId) {
        super(postId);
    }

    @Override
    public void perform() {

    }

    /*
        Getter & Setter
     */

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

package net.steppschuh.instabots.pages.elements;

import net.steppschuh.instabots.models.User;
import net.steppschuh.instabots.pages.InstagramPage;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class UserListItemElement extends InstagramElement {

    private User user;

    public UserListItemElement(@Nonnull WebElement rootElement) {
        super(rootElement);
    }

    public UserListItemElement(InstagramPage page, WebElement rootElement) {
        super(page, rootElement);
    }

    @Override
    protected void parse() {

    }

    /*
        Getter & Setter
     */

    public User getUser() {
        return user;
    }

}

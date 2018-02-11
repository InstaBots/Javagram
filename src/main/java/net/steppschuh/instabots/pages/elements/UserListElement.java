package net.steppschuh.instabots.pages.elements;

import net.steppschuh.instabots.models.User;
import net.steppschuh.instabots.pages.InstagramPage;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UserListElement extends InstagramElement {

    private List<User> users = new ArrayList<>();

    public UserListElement(@Nonnull WebElement rootElement) {
        super(rootElement);
    }

    public UserListElement(InstagramPage page, WebElement rootElement) {
        super(page, rootElement);
    }

    @Override
    protected void parse() {

    }

    /*
        Getter & Setter
     */

    public List<User> getUsers() {
        return users;
    }

}

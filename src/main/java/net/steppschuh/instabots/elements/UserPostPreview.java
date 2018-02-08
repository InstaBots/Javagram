package net.steppschuh.instabots.elements;

import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class UserPostPreview extends PostPreview {

    public UserPostPreview(@Nonnull WebElement rootElement) {
        super(rootElement);
    }

}

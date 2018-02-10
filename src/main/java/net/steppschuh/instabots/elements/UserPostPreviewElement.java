package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.pages.InstagramPage;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class UserPostPreviewElement extends PostPreviewElement {

    public UserPostPreviewElement(InstagramPage page, WebElement rootElement) {
        super(page, rootElement);
    }

}

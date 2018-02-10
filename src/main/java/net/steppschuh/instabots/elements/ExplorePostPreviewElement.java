package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.pages.InstagramPage;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class ExplorePostPreviewElement extends PostPreviewElement {

    public ExplorePostPreviewElement(InstagramPage page, WebElement rootElement) {
        super(page, rootElement);
    }

}

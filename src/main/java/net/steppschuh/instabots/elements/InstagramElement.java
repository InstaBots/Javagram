package net.steppschuh.instabots.elements;

import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class InstagramElement {

    protected WebElement rootElement;

    public InstagramElement(@Nonnull WebElement rootElement) {
        this.rootElement = rootElement;
    }

}

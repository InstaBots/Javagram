package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.pages.InstagramPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;

public abstract class InstagramElement {

    protected InstagramPage page;
    protected WebElement rootElement;

    public InstagramElement(@Nonnull WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public InstagramElement(InstagramPage page, WebElement rootElement) {
        this.page = page;
        this.rootElement = rootElement;
    }

}

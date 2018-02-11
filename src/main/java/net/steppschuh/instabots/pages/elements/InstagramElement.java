package net.steppschuh.instabots.pages.elements;

import net.steppschuh.instabots.pages.InstagramPage;
import net.steppschuh.instabots.utils.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public abstract class InstagramElement {

    protected InstagramPage page;
    protected WebElement rootElement;

    public InstagramElement(@Nonnull WebElement rootElement) {
        this(null, rootElement);
    }

    public InstagramElement(InstagramPage page, WebElement rootElement) {
        this.page = page;
        this.rootElement = rootElement;
        parse();
    }

    protected abstract void parse();

    public boolean hasElement(@Nonnull By by) {
        return WebElementUtil.hasElement(rootElement, by);
    }

}

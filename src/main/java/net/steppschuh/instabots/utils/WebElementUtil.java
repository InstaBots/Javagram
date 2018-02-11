package net.steppschuh.instabots.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public final class WebElementUtil {

    private WebElementUtil() {
    }

    public static boolean hasElement(@Nonnull WebElement rootElement, @Nonnull By by) {
        return !rootElement.findElements(by).isEmpty();
    }

    public static boolean hasElement(@Nonnull WebDriver webDriver, @Nonnull By by) {
        return !webDriver.findElements(by).isEmpty();
    }

}

package net.steppschuh.instabots.models;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tags extends ArrayList<String> {

    private static final Pattern PATTERN = Pattern.compile("[##]+([A-Za-z0-9-_]+)");

    public Tags() {
    }

    public Tags(Collection<? extends String> c) {
        super(c);
    }

    public Tags(@Nonnull String tags) {
        this(getTagListFromString(tags));
    }

    public static List<String> getTagListFromString(String string) {
        List<String> tags = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(string);
        while (matcher.find()) {
            String tag = matcher.group();
            tag = tag.substring(1).trim();
            tags.add(tag);
        }
        return tags;
    }

}

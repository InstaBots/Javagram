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

    @Override
    public String toString() {
        return toString(size());
    }

    public String toString(int maximumCount) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(size())
                .append(" tags");

        if (isEmpty() || maximumCount < 1) {
            return stringBuilder.toString();
        } else {
            if (maximumCount < size()) {
                stringBuilder.append(", including: ");
            } else {
                stringBuilder.append(": ");
            }
            for (int tagIndex = 0; tagIndex < maximumCount && tagIndex < size(); tagIndex++) {
                stringBuilder.append("#").append(get(tagIndex)).append(" ");
            }
            String tags = stringBuilder.toString();
            return tags.substring(0, tags.length() - 1);
        }
    }

    public static Tags from(@Nonnull String tags) {
        return new Tags(getTagListFromString(tags));
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

package net.steppschuh.instabots.models;

import net.steppschuh.instabots.pages.InstagramPage;
import net.steppschuh.instabots.utils.ParserUtil;
import net.steppschuh.markdowngenerator.text.TextBuilder;

public class Post {

    public enum Type {
        PHOTO, VIDEO, GALLERY
    }

    private static final String POST_BY_ID_URL = InstagramPage.HOME_URL + "p/";

    public static final int COUNT_UNKNOWN = -1;

    /**
     * The ID of the post, as included in the URL to the post.
     * Example: "Be7y9UYjz6j"
     */
    private String id;

    /**
     * The source URL of the post image.
     */
    private String imageUrl;

    /**
     * The title of the post, as seen next to the post.
     */
    private String title;

    /**
     * The tags included in the {@link #title}.
     */
    private Tags tags = new Tags();

    /**
     * The total number of people that liked the post.
     */
    private int likesCount = COUNT_UNKNOWN;

    /**
     * The total number of people that commented on the post.
     */
    private int commentsCount = COUNT_UNKNOWN;

    /**
     * The user that published the post.
     */
    private User user = new User();

    /**
     * The name of the tagged location, if available.
     */
    private String locationName;

    /**
     * Timestamp of the publish date of the post.
     */
    private long timestamp;

    private Type type;

    public Post() {
    }

    public String getUrl() {
        return getUrl(id);
    }

    @Override
    public String toString() {
        return toCompactString();
    }

    public String toCompactString() {
        return "Post " + id;
    }

    public String toDetailedString() {
        TextBuilder textBuilder = new TextBuilder()
                .append("Post by ").append(user);

        if (likesCount != COUNT_UNKNOWN) {
            textBuilder.append(" with ")
                    .append(likesCount).append(" likes");
        }

        textBuilder.newLine()
                .append(getTags().toString(5))
                .newLine()
                .append(getUrl());

        return textBuilder.toString();
    }

    public static String getTruncatedTitle(String title) {
        return getTruncatedTitle(title, 30);
    }

    public static String getTruncatedTitle(String title, int maximumLength) {
        if (title.length() <= maximumLength) {
            return title;
        }
        return title.substring(0, maximumLength) + "[...]";
    }

    public static String getUrl(String postId) {
        return POST_BY_ID_URL + postId + "/";
    }

    public static String getIdFromUrl(String url) {
        String postId = ParserUtil.getStringBetween(url, "/p/", "/");
        if (!isValidId(postId)) {
            throw new IllegalArgumentException("Unable to extract post ID from URL: " + url);
        }
        return postId;
    }

    public static boolean isValidId(String postId) {
        if (postId == null || postId.length() != 11) {
            return false;
        } else if (postId.contains("/")) {
            return false;
        }
        // TODO: add more checks
        return true;
    }

    /*
        Getter & Setter
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tags getTags() {
        if (tags == null || tags.isEmpty()) {
            tags = Tags.from(title);
        }
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

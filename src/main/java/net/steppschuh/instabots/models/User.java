package net.steppschuh.instabots.models;

import net.steppschuh.instabots.pages.InstagramPage;
import net.steppschuh.markdowngenerator.text.TextBuilder;

/**
 * Created by Steppschuh on 08.02.18.
 */
public class User {

    private static final String USER_BY_ID_URL = InstagramPage.HOME_URL;

    /**
     * The ID of the user, as included in the URL to the profile.
     * Example: "natgeo"
     */
    private String id;

    /**
     * The human-readable name of the user.
     * Example: "National Geographic"
     */
    private String name;

    /**
     * The description, as seen on top of a user page.
     * Example: "Experience the world through the eyes of National Geographic photographers."
     */
    private String description;

    /**
     * The website URL provided by the user, if available.
     */
    private String websiteUrl;

    /**
     * The source URL of the user image.
     */
    private String imageUrl;

    /**
     * The total number of posts published by the user.
     */
    private int postsCount;

    /**
     * The total number of people that follow the user.
     */
    private int followersCount;

    /**
     * The total number of people that the user is following.
     */
    private int followingCount;

    /**
     * Indicator if the user has been verified (blue badge behind the user ID).
     */
    private boolean isVerified;

    public User() {
    }

    public String getUrl() {
        return getUrl(id);
    }

    @Override
    public String toString() {
        return toCompactString();
    }

    public String toCompactString() {
        return getNameOrId();
    }

    public String toDetailedString() {
        TextBuilder textBuilder = new TextBuilder()
                .append(name)
                .append(" (@").append(id).append(")");

        if (isVerified) {
            textBuilder.append(" - Verified");
        }

        if (postsCount != Post.COUNT_UNKNOWN) {
            textBuilder.newLine()
                    .append(postsCount).append(" posts,")
                    .append(" ").append(followersCount).append(" followers,")
                    .append(" ").append(followingCount).append(" following");
        }

        if (hasWebsite()) {
            textBuilder.newLine().append(websiteUrl);
        }

        textBuilder.newLine()
                .append(getUrl());

        return textBuilder.toString();
    }

    public String getNameOrId() {
        if (id == null && name == null) {
            return "Unknown";
        } else if (name != null) {
            return name;
        } else {
            return id;
        }
    }

    public boolean hasWebsite() {
        return websiteUrl != null;
    }

    public static String getUrl(String userId) {
        return USER_BY_ID_URL + userId + "/";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

}

package org.apache.struts.model;

public class ThemeDescriptor {

    private final String id;
    private final String displayName;
    private final String contextPath;

    public ThemeDescriptor(final String id, final String displayName, final String contextPath) {
        this.id = id;
        this.displayName = displayName;
        this.contextPath = contextPath;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String toString() {
        return "ThemeDescriptor{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", contextPath='" + contextPath + '\'' +
                '}';
    }
}
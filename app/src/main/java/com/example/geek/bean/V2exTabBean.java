package com.example.geek.bean;

public class V2exTabBean {
    private String linkHref;
    private String tab;
    private boolean isChecked;

    public V2exTabBean(String linkHref, String tab, boolean isChecked) {
        this.linkHref = linkHref;
        this.tab = tab;
        this.isChecked = isChecked;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "linkHref='" + linkHref + '\'' +
                ", tab='" + tab + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}

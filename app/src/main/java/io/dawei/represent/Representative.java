package io.dawei.represent;

public class Representative {
    private String name;
    private String party;
    private String phoneNumber;
    private String url;

    Representative(String name, String party, String phoneNumber, String url) {
        this.name = name;
        this.party = party;
        this.phoneNumber = phoneNumber;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getParty() {
        return party;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUrl() {
        return url;
    }
}

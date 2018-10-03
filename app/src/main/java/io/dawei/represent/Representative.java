package io.dawei.represent;

public class Representative {
    private String name;
    private String party;
    private String phoneNumber;
    private String url;
    private String contact_form;
    private String bioguide_id;

    Representative(String name, String party, String phoneNumber, String url, String contact_form, String bioguide_id) {
        this.name = name;
        this.party = party;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.contact_form = contact_form;
        this.bioguide_id = bioguide_id;
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

    public String getBioguide_id() {
        return bioguide_id;
    }

    public String getContact_form() {
        return contact_form;
    }
}

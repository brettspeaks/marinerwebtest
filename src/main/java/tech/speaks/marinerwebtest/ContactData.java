package tech.speaks.marinerwebtest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper class for calls to the contact api because axios sends post/put requests as "data":{contact_info}
 */
public class ContactData {

    @JsonProperty("data")
    private Contact contact;

    Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "contact=" + contact +
                '}';
    }
}

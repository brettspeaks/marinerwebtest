package tech.speaks.marinerwebtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContactLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Contact contact = new Contact();
        contact.setFirstName("Brian");
        contact.setLastName("Olson");
        contact.setEmail("bols@me.com");
        contact.setPhoneNumber("555-555-5555");
        contactRepository.save(contact);

        contact = new Contact();
        contact.setFirstName("Tanner");
        contact.setLastName("Hutton");
        contact.setEmail("th@gmail.com");
        contact.setPhoneNumber("123-123-1234");
        contactRepository.save(contact);

        contact = new Contact();
        contact.setFirstName("Brett");
        contact.setLastName("Speaks");
        contact.setEmail("bs@aol.com");
        contact.setPhoneNumber("321-321-1234");
        contactRepository.save(contact);
    }
}

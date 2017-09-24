package tech.speaks.marinerwebtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * The rest controller for the contacts api. Supports select, insert, update, and delete. If this were real life,
 * it's probably not the best idea to serve all contacts on a get to /api/contacts. The url structure is as follows:
 *
 * GET /api/contacts
 * GET /api/contacts/{id}
 * POST /api/contacts
 * PUT /api/contacts/{id}
 * DELETE /api/contacts/{id}
 *
 * All post and put data should be sent as raw json and is then serialized to the Contact object.
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/api/contacts")
public class ContactsController {

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @GetMapping
    Iterable<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    Contact getContactById(@PathVariable Integer id){
        return contactRepository.findOne(id);
    }

    @PostMapping
    Contact createNewContact(@RequestBody Contact contact){

        if (contact != null) {
            contactRepository.save(contact);
        }
        return contact;
    }

    @PutMapping("/{id}")
    Contact updateContact(@PathVariable Integer id, @RequestBody Contact contact){
        Contact contactToUpdate = contactRepository.findOne(id);

        if(id != null && contactToUpdate != null) {
            contactToUpdate.setFirstName(contact.getFirstName());
            contactToUpdate.setLastName(contact.getLastName());
            contactToUpdate.setEmail(contact.getEmail());
            contactToUpdate.setPhoneNumber(contact.getPhoneNumber());
        }
        return contactRepository.save(contactToUpdate);
    }

    @DeleteMapping("/{id}")
    void deleteContact(@PathVariable Integer id){
        if (id != null) {
            contactRepository.delete(id);
        }
    }
}

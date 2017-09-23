package tech.speaks.marinerwebtest;

import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ContactRepository contactRepository;

    @Test
    public void testAdd(){
        Contact c = new Contact();
        c.setFirstName("test");
        c.setLastName("test");
        c.setEmail("email@me.com");
        c.setPhoneNumber("123-123-1234");

        entityManager.persist(c);

        Iterable<Contact> contacts = contactRepository.findAll();

        Assert.assertEquals(1, Iterables.size(contacts));

        for (Contact contact : contacts) {
            Assert.assertEquals("test", contact.getFirstName());
            Assert.assertEquals("test", contact.getLastName());
        }
    }

    @Test
    public void testRemove(){
        Contact c = new Contact();
        c.setFirstName("test");
        c.setLastName("test");
        c.setEmail("email@me.com");
        c.setPhoneNumber("123-123-1234");

        entityManager.persist(c);

        Iterable<Contact> contacts = contactRepository.findAll();

        Assert.assertEquals(1, Iterables.size(contacts));

        for (Contact contact: contacts) {
            contactRepository.delete(contact.getId());
        }

        contacts = contactRepository.findAll();

        Assert.assertEquals(0, Iterables.size(contacts));
    }
}

<template>
    <div id="create-contact">
        <h1>Create Contact</h1>

        <p><router-link :to="{ name: 'all_contacts' }">Return to contacts</router-link></p>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="addContact">
            <div class="form-group">
                <label name="contact_id">ID</label>
                <input type="text" class="form-control" disabled v-model="contact.id" id="contact_id">
            </div>

            <div class="form-group">
                <label name="contact_first_name">First Name</label>
                <input type="text" class="form-control" v-model="contact.firstName" id="contact_first_name" required>
            </div>

            <div class="form-group">
                <label name="contact_last_name">Last Name</label>
                <input type="text" class="form-control" v-model="contact.lastName" id="contact_last_name" required>
            </div>

            <div class="form-group">
                <label name="contact_email">Email</label>
                <input type="email" class="form-control" v-model="contact.email" id="contact_email" required>
            </div>

            <div class="form-group">
                <label name="contact_phone">Phone</label>
                <input type="tel" class="form-control" v-model="contact.phoneNumber" id="contact_phone" required>
            </div>

            <div class="form-group">
                <button class="btn btn-primary">Create</button>
            </div>
        </form>
    </div>
</template>

<script>
    import Notification from './notifications.vue';
    import axios from 'axios';

    export default{
        data(){
            return{
                contact: {},
                notifications:[]
            }
        },

        methods: {
            addContact: function()
            {
                // Validation here.

                axios.post('http://localhost:8080/api/contacts', {
                    data: this.contact
                }).then(reponse => {
                    this.notifications.push({ type: 'success', message: 'Contact created!'})
                }).catch(e => {
                    this.notifications.push({ type: 'error', message: 'There was an error! Contact not created.'})
                });

            }
        },

        components: {
            'notification' : Notification
        }
    }
</script>

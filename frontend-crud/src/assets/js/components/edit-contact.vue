<template>
    <div id="update-contact">
        <h1>Update Contact</h1>

        <p><router-link :to="{ name: 'all_contacts' }">Return to contacts</router-link></p>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="editContact">
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
                <button class="btn btn-primary">Update</button>
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
                contact:{},
                notifications:[]
            }
        },

        created: function(){
            this.getContact();
        },

        methods: {
            getContact: function()
            {
                const getUrl = 'http://localhost:8080/api/contacts/' + this.$route.params.id;

                axios.get(getUrl)
                    .then(response => {
                        this.contact = response.data;
                    })
                    .catch(e => {
                        this.notifications.push({
                            type: 'error',
                            message: 'Unable to load contact info.'
                        })
                    });

            },

            editContact: function()
            {
                // Validation here.

                const putUrl = 'http://localhost:8080/api/contacts/' + this.$route.params.id;

                axios.put(putUrl, { data: this.contact})
                    .then(response => {
                        this.notifications.push({
                            type: 'success',
                            message: 'Contact updated successfully!'
                        })
                    })
                    .catch(e => {
                        this.notifications.push({
                            type: 'error',
                            message: 'Error updating contact.'
                        })
                    });
            }
        },

        components: {
            'notification' : Notification
        }
    }
</script>

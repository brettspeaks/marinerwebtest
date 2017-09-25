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

            <div class="form-group" :class="{'has-error': errors.has('contact_first_name') }">
                <label class="control-label" for="contact_first_name">First Name</label>
                <input type="text"
                       class="form-control"
                       v-model="contact.firstName"
                       v-validate="'required'"
                       name="contact_first_name"
                >
                <p class="text-danger" v-if="errors.has('contact_first_name')">{{ errors.first('contact_first_name') }}</p>
            </div>

            <div class="form-group" :class="{'has-error': errors.has('contact_last_name') }">
                <label class="control-label" for="contact_last_name">Last Name</label>
                <input type="text"
                       class="form-control"
                       v-model="contact.lastName"
                       v-validate="'required'"
                       name="contact_last_name"
                >
                <p class="text-danger" v-if="errors.has('contact_last_name')">{{ errors.first('contact_last_name') }}</p>
            </div>

            <div class="form-group" :class="{'has-error': errors.has('contact_email') }">
                <label class="control-label" for="contact_email">Email</label>
                <input
                        type="text"
                        class="form-control"
                        v-model="contact.email"
                        v-validate="'required|email'"
                        name="contact_email"
                >
                <p class="text-danger" v-if="errors.has('contact_email')">{{ errors.first('contact_email') }}</p>
            </div>

            <div class="form-group" :class="{'has-error': errors.has('contact_phone') }">
                <label class="control-label" for="contact_phone">Phone</label>
                <input type="tel"
                       class="form-control"
                       v-model="contact.phoneNumber"
                       v-validate="{rules: { regex: /^\D?(\d{3})\D?\D?(\d{3})\D?(\d{4})$/ } }"
                       name="contact_phone"
                >
                <p class="text-danger" v-if="errors.has('contact_phone')">{{ errors.first('contact_phone') }}</p>
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
                if(this.errors.any()){
                    this.notifications.push({
                        type: 'error',
                        message: 'Unable to update contact. Fix form errors and submit again.'
                    })
                }
                else{
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
                                type: 'danger',
                                message: 'Error updating contact.'
                            })
                        });
                }

            }
        },

        components: {
            'notification' : Notification
        }
    }
</script>

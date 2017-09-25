<template>
    <div id="delete-contact">
        <h1>Delete Contact {{ contact.firstName }} {{ contact.lastName }} </h1>

        <p><router-link :to="{ name: 'all_contacts' }">Return to contacts</router-link></p>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="deleteContact">
            <p><button class="btn btn-danger">Delete Contact</button></p>
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
                        this.contact = response.data
                    })
                    .catch(e => {
                        this.notifications.push({
                            type: 'error',
                            message: 'Unable to load contact data.'
                        })
                    });
            },

            deleteContact: function()
            {
                const deleteUrl = 'http://localhost:8080/api/contacts/' + this.$route.params.id;

                axios.delete(deleteUrl, { data: this.contact})
                    .then(response => {
                        this.$router.push({ name: 'all_contacts' })
                    })
                    .catch(e => {
                        this.notifications.push({
                            type: 'danger',
                            message: 'Contact could not be deleted.'
                        })
                    });
            }
        },

        components: {
            'notification' : Notification
        }
    }
</script>

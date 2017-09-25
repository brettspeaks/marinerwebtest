<template>
    <div id="all-products">
        <h1>All Your Contacts</h1>

        <p><router-link :to="{ name: 'create_contact' }" class="btn btn-primary">Create New Contact</router-link></p>

        <div class="form-group">
            <input type="text" name="search" v-model="contactSearch" placeholder="Search contacts by first or last name." class="form-control" v-on:keyup="searchContacts">
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <td>ID</td>
                <td><a href="#" v-on:click="sortBy('firstName')">First Name</a></td>
                <td><a href="#" v-on:click="sortBy('lastName')">Last Name</a></td>
                <td>Email</td>
                <td>Phone</td>
                <td>Actions</td>
            </tr>
            </thead>

            <tbody>
                <tr v-for="contact in orderedContacts">
                    <td>{{ contact.id }}</td>
                    <td>{{ contact.firstName }}</td>
                    <td>{{ contact.lastName }}</td>
                    <td>{{ contact.email }}</td>
                    <td>{{ contact.phoneNumber }}</td>
                    <td>
                        <router-link :to="{name: 'edit_contact', params: { id: contact.id }}" class="btn btn-primary">Edit</router-link>
                        <router-link :to="{name: 'delete_contact', params: { id: contact.id }}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import axios from 'axios';

    export default{
        data(){
            return{
                sortKey: "id",
                reverse: false,
                contacts: [],
                originalContacts: [],
                contactSearch: '',
            }
        },

        created: function()
        {
            this.fetchContactData();
        },

        computed: {
            orderedContacts: function(){
                const order = this.reverse ? 'desc' :'asc';
                return this._.orderBy(this.contacts, this.sortKey, order)
            }
        },

        methods: {
            sortBy: function(sortKey){
                this.reverse = (this.sortKey == sortKey) ? ! this.reverse : false;

                this.sortKey = sortKey;
            },
            fetchContactData: function()
            {
                axios.get('http://localhost:8080/api/contacts')
                .then(response => {
                  this.contacts = response.data;
                  this.originalContacts = this.contacts;
                })
                .catch(e => {
                  this.errors.push(e);
                })
            },

            searchContacts: function()
            {
                if(this.contactSearch == '')
                {
                    this.contacts = this.originalContacts;
                    return;
                }

                let searchedContacts = [];
                for(let i = 0; i < this.originalContacts.length; i++)
                {
                    let contactFirstName = this.originalContacts[i]['firstName'].toLowerCase();
                    let contactLastName = this.originalContacts[i]['lastName'].toLowerCase();
                    if(contactFirstName.indexOf(this.contactSearch.toLowerCase()) >= 0 ||
                        contactLastName.indexOf(this.contactSearch.toLowerCase()) >= 0)
                    {
                        searchedContacts.push(this.originalContacts[i]);
                    }
                }

                this.contacts = searchedContacts;
            }
        }
    }
</script>

import Vue from 'vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter);

import VueResource from 'vue-resource';
Vue.use(VueResource);

import VeeValidate from 'vee-validate';
Vue.use(VeeValidate);

import lodash from 'lodash';
import VueLodash from 'vue-lodash';

Vue.use(VueLodash, lodash)

import App from './App.vue'

const AllContacts = require('./assets/js/components/all-contacts.vue');
const CreateContact = require('./assets/js/components/create-contact.vue');
const EditContact = require('./assets/js/components/edit-contact.vue');
const DeleteContact = require('./assets/js/components/delete-contact.vue');

const routes = [
    {
        name: 'all_contacts',
        path: '/',
        component: AllContacts
    },
    {
        name: 'create_contact',
        path: '/contacts/create',
        component: CreateContact
    },
    {
        name: 'edit_contact',
        path: '/contacts/edit/:id',
        component: EditContact
    },
    {
        name: 'delete_contact',
        path: '/contacts/delete/:id',
        component: DeleteContact
    }
];
var router = new VueRouter({ routes: routes, mode: 'history' });
new Vue(Vue.util.extend({ router }, App)).$mount('#app');
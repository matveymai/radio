import Vue from 'vue'
import 'api/resource'
import '@babel/polyfill'
import App from 'pages/App.vue'
import {connect} from './util/ws.js'
import Vuetify from "vuetify"
import store from 'store/store'
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    render: a => a(App),
    vuetify: new Vuetify({}),
})


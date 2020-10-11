import Vue from 'vue'
import 'api/resource.js'
import router from 'router/router.js'
import '@babel/polyfill'
import App from 'pages/App.vue'
import {connect} from './util/ws.js'
import Vuetify from "vuetify"
import store from 'store/store.js'
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App),
    vuetify: new Vuetify({}),
})


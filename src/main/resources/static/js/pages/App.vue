<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Sarafan</v-toolbar-title>
      <v-spacer></v-spacer>
      <div v-if="profile">
        {{profile.name}}&nbsp;
      <v-btn icon href="/logout">
         <v-icon>exit_to_app</v-icon>
      </v-btn>
      </div>
    </v-app-bar>
    <v-main>
      <v-container v-if="!profile">Необходимо авторизоваться через
        <a href="/login">Google</a>
      </v-container>
      <v-container v-if="profile">
        <message-list :messages="messages"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import MessageList from 'components/messages/MessageList.vue'
import { addHandler } from 'util/ws.js'
import { getIndex } from 'util/collections.js'

export default {
  components:{
    MessageList
  },
  data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile
    }
  },
  created() {
    addHandler(data => {
      let index = getIndex(this.messages, data.id);
      if (index>-1){
        this.messages.splice(index, 1, data);
      } else {
        this.messages.push(data);
      }
    })
  }
}
</script>

<style scoped>
.main-app{
  color: darkgreen;
}
</style>
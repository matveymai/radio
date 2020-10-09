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
      if (data.objectType === 'MESSAGE') {
        const index = this.messages.findIndex(item => item.id === data.body.id)

            switch (data.eventType){
              case 'CREATE':
              case 'UPDATE':
                if (index > -1){
                  this.messages.splice(index, 1, data.body)
                }else {
                  this.messages.push(data.body)
                }
                break
              case 'REMOVE':
                this.messages.splice(index, 1)
                break
              default:
                console.error(`looks like the event type is unknown "${data.eventType}" `)
            }
        } else {
        console.error(`looks like the object type is unknown "${data.objectType}" `)
      }
    })
  }
}
</script>

<style scoped>

</style>
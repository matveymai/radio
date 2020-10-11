<template>
  <v-layout align-content-space-around justify-start column>
    <message-form :messageAttr="message"/>

    <message-row v-for="message in sortedMessages"
                 :key="message.id"
                 :message="message"
                 :editMessage="editMessage"
    />
  </v-layout>
</template>

<script>
import MessageRow from "./MessageRow.vue"
import MessageForm from "./MessageForm.vue"
import { mapGetters } from 'vuex';

export default {
  name: 'MessageList',
  components: {
    MessageRow,
    MessageForm
  },
  data(){
    return {
      message: null
    }
  },
  computed: mapGetters(['sortedMessages']),
  methods: {
    editMessage(message){
      this.message = message
    },
  }
}
</script>

<style scoped>

</style>
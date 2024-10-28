<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons>
          <ion-back-button text="Back" default-href="/"></ion-back-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <parent></parent>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { useRouter } from "vue-router";
import {
  IonPage,
  IonContent,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonBackButton,
} from "@ionic/vue";
import { defineComponent } from "vue";
import Parent from "@/components/Props/Parent.vue";

export default defineComponent({
  name: "Props",
  components: {
    IonPage,
    IonContent,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonBackButton,
    Parent,
  },
  setup() {
    const router = useRouter();
    return { router };
  },
});
</script>
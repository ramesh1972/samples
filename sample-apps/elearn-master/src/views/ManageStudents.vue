<template>
  <ion-page>
    <ion-content>
      <ion-grid>
        <ion-row>
          <ion-header>
            <ion-toolbar>
              <ion-buttons slot="start">
                <ion-menu-button></ion-menu-button>
              </ion-buttons>
            </ion-toolbar>
          </ion-header>
        </ion-row>
        <ion-row>
          <ion-col> Manage Students </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { useRouter } from "vue-router";
import {
  IonPage,
  IonContent,
  IonCol,
  IonGrid,
  IonRow,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonMenuButton,
} from "@ionic/vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Dashboard",
  components: {
    IonPage,
    IonContent,
    IonCol,
    IonGrid,
    IonRow,
    IonHeader,
    IonToolbar,
    IonButtons,
    IonMenuButton,
  },
  setup() {
    const router = useRouter();
    return { router };
  },
});
</script>
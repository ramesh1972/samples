<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Learn Vue Concepts</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <div class="contentPos">
        <ion-list class="listPos">
          <ion-item v-for="(item, i) in items" :key="i" :routerLink="item.src">
            <ion-label>
              <h4 class="titleText">{{ item.title }}</h4>
              <h1 class="contentText">{{ item.content }}</h1>
            </ion-label>
          </ion-item>
        </ion-list>
      </div>
    </ion-content>
  </ion-page>
</template>

<script>
import {
  IonItem,
  IonList,
  IonLabel,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "LearnVue",
  components: {
    IonContent,
    IonHeader,
    IonPage,
    IonList,
    IonLabel,
    IonItem,
    IonToolbar,
    IonTitle
  },
  data: () => {
    return {
      items: [
        {
          color: "danger",
          src: "/props",
          title: "Props",
          content: "Pass Props from Parent to Child",
        }
      ],
    };
  },
  setup() {
    const router = useRouter();
    return { router };
  },
});
</script>

<style scoped>
.contentPos {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
.listPos {
  width: 30%;
  min-width: 270px;
}

ion-item.item {
  --ion-background-color: rgb(243, 232, 232);
  --border-radius: 12px;
}

ion-item.item:hover {
  --ion-background-color: rgb(73, 73, 68);
  --ion-color:white;
}

.titleText {
  color: black;
  font-size: 24px;
}

.contentText {
  color: gray;
  font-size: 12px;
  font-weight: bold;
}
</style>
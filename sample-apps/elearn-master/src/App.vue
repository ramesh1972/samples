<template>
  <ion-app>
    <ion-content>
      <ion-split-pane contentId="content1">
        <ion-menu side="start" menuId="first" contentId="content1">
          <ion-content class="panelWidth">
            <side-panel></side-panel>
          </ion-content>
        </ion-menu>

        <ion-router-outlet id="content1"></ion-router-outlet>
      </ion-split-pane>
    </ion-content>
  </ion-app>
</template>

<script lang="ts">
import { IonApp, IonRouterOutlet, IonContent, IonSplitPane } from "@ionic/vue";
import { defineComponent } from "vue";
import SidePanel from "@/components/SidePanel/SidePanel.vue";

export default defineComponent({
  name: "App",
  components: {
    IonApp,
    IonRouterOutlet,
    IonContent,
    IonSplitPane,
    SidePanel,
  },
});
</script>

<style scoped>
@import url("./assets/styles/base.css");
@import url("./assets/styles/icons.css");

ion-split-pane {
  --side-min-width:260px;
  --side-width:260px;
}

ion-menu {
  --width: 260px;
}

.panelWidth {
  width: 260px;
}
</style>
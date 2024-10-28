<template>
  <div class="hamburger-menu">
    <ion-list class="ion-no-padding ion-no-margin">
      <ion-item>
        <logo></logo>
      </ion-item>
      <ion-item>
        <profile></profile>
      </ion-item>
      <ion-item>
        <Menu></Menu>
      </ion-item>
    </ion-list>
  </div>
</template>

<script>
import { IonItem, IonList } from "@ionic/vue";
import Logo from "./Logo.vue";
import Profile from "./Profile.vue";
import Menu from "./Menu.vue";

export default {
  name: "SidePanel",
  components: {
    IonItem,
    IonList,
    Logo,
    Profile,
    Menu,
  },
};
</script>

<style scoped>
.hamburger-menu {
  background: #fff;
  bottom: 0;
  font-family: "proxima-nov,sans-serif, Helvetica, Arial";
  font-size: 14px;
  overflow-y: auto;
  overflow-x: hidden;
  position: fixed;
  top: 0;
  max-height: 100vh;
  width: 260px;
  z-index: 92;
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.15);
  align-items: center;
  justify-content: center;
  height: 100%;
}

ion-item {
  --padding-start: 0px;
  --padding-end: 0px;
  --inner-padding-end: 0px;
}
</style>
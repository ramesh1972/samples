<template>
  <div class="profile">
    <ion-item class="photo ion-no-padding" lines="none"><img src="../../assets/video-bg.jpg" /></ion-item>
    <ion-item class="title ion-no-padding" lines="none"><ion-label>Jeniffer</ion-label></ion-item>
    <ion-item class="caption ion-no-padding" lines="none"><ion-label>Adyar, Chennai</ion-label></ion-item>
    <ion-item class="shortURL ion-no-padding" lines="none">
        <ion-label><a class="link" href="#"><i class="hub-link"></i>jeniffer.sulekha.com</a></ion-label></ion-item>
  </div>
</template>

<script>
import {IonItem, IonLabel } from "@ionic/vue";

export default {
  name: "Profile",
  components: { IonItem, IonLabel },
};
</script>

<style scoped>
.brand-logo {
  width: calc(100% - 50px);
  padding: 0 20px;
}
.brand-logo img {
  width: 80px;
}
.profile {
  text-align: center;
  margin: 20px 0;
  width:100%;
}
.photo {
  margin-bottom: 10px;
}
.photo img {
  width: 96px;
  height: 96px;
  object-fit: cover;
  border-radius: 100%;
  display: block;
  margin: 0 auto;
}
.profile .title {
  font-size: 16px;
  text-transform: uppercase;
  font-weight: 600;
  color: #666;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 12px;
    text-align: center;
}

.profile .caption {
  font-size: 13px;
  color: #666;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.profile .shortURL {
  font-size: 12px;
  color: #666;
  margin: 10px 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 12px;
    text-align: center;
}

.profile .link {
  font-size: 12px;
  color: #666;
  margin: 10px 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 4px;
    text-align: center;
}
.profile .hub-link {
  margin-right: 5px;
}

ion-item {
    --min-height: 5px;
}

ion-label {
    --min-height: 10px;
}
</style>
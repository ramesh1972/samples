<template>
  <div class="accordian">
    <div
      v-on:click="toggleGroup(groups, group)"
      v-for="(group, i) in groups"
      :key="i"
      class="accordion-item"
    >
      <ion-item class="accordion-header" lines="none" :routerLink="group.name == 'Dashboard' ? '/dashboard': ''">
        {{ group.name }}
      </ion-item>
      <div
        v-if="group.name != 'Dashboard' && group.show"
        class="accordion-body"
      >
        <div class="sub-menu">
          <ion-item v-for="(item, j) in group.items" :key="j" class="sub-menu-item" :routerLink="item.path" lines="none">
            {{ item.title }}
          </ion-item>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { IonItem } from "@ionic/vue";

export default {
  name: "Menu",
  components: {
    IonItem
  },
  data: () => {
    return {
      groups: [
        {
          name: "Dashboard",
          show: false,
          items: [],
        },
        {
          name: "Manage Courses",
          show: true,
          items: [
            { title: "Create / Edit / Delete Course", path: "" },
            { title: "Setup Demo Class", path: "" },
            { title: "Setup One on One Packs", path: "" },
          ],
        },
        {
          name: "Manage Students",
          show: false,
          items: [
            { title: "Students by Course", path: "/managestudents" },
            { title: "Prospective Demo Students", path: "" },
            { title: "Course Certificate", path: "" },
          ],
        },
        {
          name: "My Calendar",
          show: false,
          items: [
            { title: "Reschedule Sessions", path: "/managestudents" },
            { title: "Launch Sessionse", path: "/managestudents" },
          ],
        },
        {
          name: "Profile",
          show: false,
          items: [
            { title: "Profile Information", path: "/managestudents" },
            { title: "Successful Student", path: "/managestudents" },
            { title: "KYC", path: "/managestudents" },
          ],
        },
      ],
    };
  },
  methods: {
    toggleGroup: function (groups, group) {
      groups.forEach((element) => {
        console.log(element.name);
        element.show = false;
      });
      group.show = true;
    },
  },
};
</script>

<style scoped>
ion-item {
  --min-height: 16px;
  --ion-font-family: "proxima-nova,sans-serif, Helvetica, Arial";
}

.accordion {
  border-top: 1px solid #ddd;
  border-right: 1px solid #ddd;
  border-left: 1px solid #ddd;
  padding: 0;
  transition: all 0.3s linear;
  width: 260px;
}

.accordion-header {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  cursor: pointer;
  -webkit-tap-highlight-color: transparent;
  line-height: 1.5;
  padding: 0.5rem;
  background-color: #fff;
  border-bottom: 1px solid #ddd;
  position: relative;
  color: #707070;
  text-transform: capitalize;
  font-size: 14px;
  border-left: 5px solid #fff;
  width: 260px;
}
.accordion-body {
  display: flex;
  border-bottom: 1px solid #ddd;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  background: #fff;
  transition: all 0.3s linear;
  width: 260px;
}

.sub-menu {
  display: flex;
  flex-direction: column;
  cursor: pointer;
  -webkit-tap-highlight-color: transparent;
  line-height: 1.5;
  padding: 0.5rem;
  padding-left: 1.2rem;
  background-color: #fff;
  position: relative;
  text-transform: capitalize;
  font-size: 14px;
  letter-spacing: 0.2px;
  color: #707070;
  width: 260px;
}

.sub-menu-item {
  cursor: pointer;
  line-height: 1.5;
  padding: 0.5rem;
  padding-left: 0.2rem;
  background-color: #fff;
  position: relative;
  text-transform: capitalize;
  font-size: 14px;
  letter-spacing: 0.2px;
  color: #707070;
  width: 260px;
}

.accordion-header:hover {
  border-left: 5px solid #e53935;
  transition: all 0.3s;
  background: #f7f7f7;
  color: #333;
  font-weight: bold;
}

.sub-menu a:hover {
  color: #333;
}
</style>
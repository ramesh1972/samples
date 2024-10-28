import { Component } from '@angular/core';
import { Hero } from '../hero';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})

export class HeroesComponent {
  hero: Hero = {
    id: 1,
    name: 'Windstorm'
  };

  shero : Hero = {
    id: 2,
    name: 'Superman'
  };

  addtoheros(){
    this.heroesList.push(this.shero);
  }   
  
}
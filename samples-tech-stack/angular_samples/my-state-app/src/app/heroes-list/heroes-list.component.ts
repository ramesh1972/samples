import { Component } from '@angular/core';
import { Hero } from '../hero';

@Component({
  selector: 'app-heroes-list',
  templateUrl: './heroes-list.component.html',
  styleUrl: './heroes-list.component.css'
})

export class HeroesListComponent {
  heroesList: Hero[] = [
    { id: 1, name: 'Windstorm' },
    { id: 2, name: 'Bombasto' },
    { id: 3, name: 'Magneta' },
    { id: 4, name: 'Tornado' }
  ];
  newHero: string = '';

  addHero() {
    if (this.newHero) {
      const hero: Hero = { // Instantiate a new object of type Hero
        id: this.heroesList.length + 1,
        name: this.newHero
      };

      this.heroesList.push(hero);
      this.newHero = '';
    }
  }

  mergeSort() {
    this.heroesList = this.mergeSortArray(this.heroesList);
  }

  mergeSortArray(array: Hero[]): Hero[] {
    if (array.length <= 1) {
      return array;
    }

    const middle = Math.floor(array.length / 2);
    const left = array.slice(0, middle);
    const right = array.slice(middle);

    return this.merge(this.mergeSortArray(left), this.mergeSortArray(right));
  }
}

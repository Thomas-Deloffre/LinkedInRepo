import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';

@Component({
  selector: 'app-edit-pokemon',
  templateUrl: './edit-pokemon.component.html',
  styleUrl: './edit-pokemon.component.scss'
})
export class EditPokemonComponent implements OnInit{

  pokemon: Pokemon | undefined ;

  constructor(
    private route: ActivatedRoute,
    private PokemonService: PokemonService
  ) { }

  ngOnInit() {
      const pokemonId: string | null = this.route.snapshot.paramMap.get('id');
      if (pokemonId) {
        this.PokemonService.getPokemonById(+pokemonId)
        .subscribe(pokemon => this.pokemon = pokemon);
      }else {
        this.pokemon = undefined;
      }
    } 

}

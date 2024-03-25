import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon } from '../pokemon';
import { PokemonService } from '../pokemon.service';

@Component({
  selector: 'app-detail-pokemon',
  templateUrl: './detail-pokemon.component.html',
  styleUrl: './detail-pokemon.component.scss'
})
export class DetailPokemonComponent implements OnInit{

  pokemonList: Pokemon[];
  pokemon: Pokemon | undefined;

  constructor (
    private route: ActivatedRoute, 
    private router: Router,
    private PokemonService: PokemonService) { }

  ngOnInit() {

    const pokemonId: string | null = this.route.snapshot.paramMap.get('id');

    if (pokemonId) {
      this.PokemonService.getPokemonById(+pokemonId)
        .subscribe(pokemon => this.pokemon = pokemon);
    }   
  }

  deletePokemon(pokemon: Pokemon) {
    this.PokemonService.deletePokemonById(pokemon.id)
      .subscribe(() => this.goToPokemonList());
  }

  goToPokemonList() {
    this.router.navigate(['/pokemons']);
  }

  goToPokemonEdit(pokemon:Pokemon) {
    this.router.navigate(['/edit/pokemon', pokemon.id])
  }

}

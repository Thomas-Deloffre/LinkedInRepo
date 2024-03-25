import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon';
import { Router } from '@angular/router';
import { Observable, Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { PokemonService } from '../pokemon.service';

@Component({
  selector: 'app-search-pokemon',
  templateUrl: './search-pokemon.component.html',
  styleUrl: './search-pokemon.component.scss'
})
export class SearchPokemonComponent implements OnInit {
  // { ..."a"..."ab"...."abz"...."abc"...}
  searchTerms = new Subject<string>();
  // {.... pokemonList(a)...pokemonList(ab)...}
  pokemons$: Observable<Pokemon[]>;

  constructor(
    private router: Router,
    private PokemonService: PokemonService
    ) { }

  ngOnInit(): void {
    this.pokemons$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term) => this.PokemonService.searchPokemonList(term))

    )
  }

  search(term: string) {
    this.searchTerms.next(term);
  }

  gotToDetail(pokemon: Pokemon) {
    const link = ['/pokemon', pokemon.id];
    this.router.navigate(link)
  }

}

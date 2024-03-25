import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  message: string = 'vous êtes déconnecté';
  name: string; 
  password: string;
  auth: AuthService;

  constructor(
    private authService: AuthService, 
    private router: Router
    ) {}

  ngOnInit(): void {
    this.auth = this.authService;
  }

  setMessage() {
    if(this.auth.isLoggedIn) {
      this.message = 'Vous êtes connecté';
    }else {
      this.message = 'Indentifiant ou mot de passe incorrect.'
    }

  }

  login() {
    this.message = "Tentative de connexion en cours..";
    this.auth.login(this.name, this.password)
      .subscribe((isLoggedIn: boolean) => {
        this.setMessage();
        if(isLoggedIn) {
          this.router.navigate(['/pokemons']);
        } else {
          this.password = '';
          this.router.navigate(['/login']);
        }
      })
  }

  logout() {
    this.message = "Deconnexion en cours..";
    this.auth.logout();
  }

}

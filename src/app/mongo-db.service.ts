import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MongoDBService {
  private mongoApiUrl = 'http://'

  constructor(private http: HttpClient) { }
}

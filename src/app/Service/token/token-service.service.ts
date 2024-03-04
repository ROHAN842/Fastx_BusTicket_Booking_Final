import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenServiceService {

  private token: any;

  constructor() {}

  setToken(token: any) {
    this.token = token;
  }

  getToken() {
    return this.token;
  }
  
  clearToken() {
    this.token = null;
  }
}

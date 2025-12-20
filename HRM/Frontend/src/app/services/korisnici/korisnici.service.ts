import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from '../../model/user';
import { LoginService } from '../login/login.service';

@Injectable({
  providedIn: 'root'
})
export class KorisniciService {

  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client: HttpClient, private loginService: LoginService) { }


  getAll() {
    return this.client.get<User[]>(`${this.baseUrl}/users`)
  }

  getOne(id: number) {
    return this.client.get<User[]>(`${this.baseUrl}/users/${id}`)
  }

  create(user: User) {
    return this.client.post(`${this.baseUrl}/users`, user)
  }

  update(id: number, user: User) {
    return this.client.put<User[]>(`${this.baseUrl}/users/${id}`, user)
  }

  delete(id: number) {
    return this.client.delete<User[]>(`${this.baseUrl}/users/${id}`)
  }
}

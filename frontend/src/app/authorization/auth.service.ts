import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authApiUrl = environment.apiUrl + '/api/auth';
  
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.authApiUrl}/login`, { email, password });
  }

  register(username: string, password: string, email: string, groupPasscode: string): Observable<any> {
    return this.http.post<any>(`${this.authApiUrl}/register`, { username, password, email, groupPasscode });
  }

  saveToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  logout(): Observable<any> {
    return this.http.post(`${this.authApiUrl}/logout`, {});
  }
}

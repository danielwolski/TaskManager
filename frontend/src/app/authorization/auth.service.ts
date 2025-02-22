import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/auth/login';
  private registerUrl = 'http://localhost:8080/api/auth/register';
  private logoutUrl = 'http://localhost:8080/api/auth/logout';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.loginUrl, { email, password });
  }

  register(username: string, password: string, email: string, groupPasscode: string): Observable<any> {
    return this.http.post<any>(this.registerUrl, { username, password, email, groupPasscode });
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

  logout(): void {
    localStorage.removeItem('jwtToken');

    //TODO fix on backend side
    // this.http.post(this.logoutUrl, {}).subscribe({
    //   next: () => console.log('Logged out on the server'),
    //   error: (err) => console.error('Server logout error:', err),
    // });
  }
}

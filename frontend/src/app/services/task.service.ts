import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, tap } from 'rxjs';
import { Task, CreateTaskRequest } from '../models/task.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private apiUrl = `${environment.apiUrl}/api/tasks`;

  private tasksUpdatedSubject = new Subject<void>();

  tasksUpdated$ = this.tasksUpdatedSubject.asObservable();

  constructor(private http: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }


  addTask(request: CreateTaskRequest): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, request).pipe(
      tap(() => this.tasksUpdatedSubject.next())
    );
  }

  removeTask(taskId: number): Observable<void> {
    const url = `${this.apiUrl}/${taskId}`;
    return this.http.delete<void>(url).pipe(
      tap(() => this.tasksUpdatedSubject.next())
    );
  }

  toggleIsDone(taskId: number): Observable<void> {
    const url = `${this.apiUrl}/${taskId}`;
    return this.http.patch<void>(url, {}).pipe(
      tap(() => this.tasksUpdatedSubject.next())
    );
  }  
}

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CreateDailyTaskRequest, DailyTask } from '../models/daily-task.model';
import { DailyTaskService } from '../services/daily-task.service';

@Component({
  selector: 'app-daily-task',
  imports: [CommonModule, FormsModule],
  templateUrl: './daily-task.component.html',
  styleUrl: './daily-task.component.css'
})
export class DailyTaskComponent implements OnInit {

  dailyTasks: DailyTask[] = [];

  dailyTaskRequest: CreateDailyTaskRequest = {
    description: ''
  };

  constructor(private dailyTaskService: DailyTaskService
  ) {}

  ngOnInit(): void {
    this.loadDailyTasks();

    this.dailyTaskService.dailyTasksUpdated$.subscribe(() => {
      this.loadDailyTasks();
    });
  }

  removeDailyTask(dailyTaskId: number) {
    this.dailyTaskService.removeDailyTask(dailyTaskId).subscribe(() => {});
  }

  toggleIsDone(dailyTaskId: number) {
    this.dailyTaskService.toggleIsDone(dailyTaskId).subscribe(() => {});
  }

  loadDailyTasks(): void {
    this.dailyTaskService.getDailyTasks().subscribe((data: DailyTask[]) => {
      this.dailyTasks = data;
    });
  }
  
  confirmCreateDailyTask(): void {
    this.dailyTaskService.addDailyTask(this.dailyTaskRequest).subscribe({
      next: (response) => {
        console.log('DailyTask created:', response);
      },
      error: (err) => {
        console.error('Error during creating daily task:', err);
      },
    });
    this.clearAddDailyTaskFormInput();
  }

  clearAddDailyTaskFormInput(): void {
    this.dailyTaskRequest = {
      description: ''
    };
  }  
}

export interface DailyTask {
    id: number;
    done: boolean;
    description: string;
}

export interface CreateDailyTaskRequest {
    description: string;
}
  
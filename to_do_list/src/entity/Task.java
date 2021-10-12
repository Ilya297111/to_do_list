package entity;

public class Task {
    private String taskName;
    private String taskText;
    private boolean status;
    private String category;

    public Task(String category, String taskName, String taskText, boolean status) {
        this.category = category;
        this.taskName = taskName;
        this.taskText = taskText;
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String transformationStatus(boolean status){
        if(status == false) {
            return "Не выполнена";
        }
        else{
            return "Выполнена";
        }
    }

    @Override
    public String toString(){
        return "Категория : " + category + "\nНазвание задачи: " + taskName
                + "\nЗадача: " + taskText + "\nСтатус задачи: " + transformationStatus(this.status);
    }
}

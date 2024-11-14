package logvinov.testTask.userRestApp.model.user;

public enum Role {
    //По конвенции спинг-секьюрити надо использовать ROLE_<роль>.
    //Секьюрити в определенный момент будет проверять роли именно в таком формате.
    ROLE_USER,
    ROLE_ADMIN
}
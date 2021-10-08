export interface ITodo {
    id: number,
    title: string,
    description: string,
    status: status,
    created?: Date,
    deadline?: Date,
}

export interface ITodoList {
    todo: ITodo[],
    doing: ITodo[],
    done: ITodo[],
}

export enum status {
    TODO = "TODO",
    DOING = "DOING",
    DONE = "DONE"
}

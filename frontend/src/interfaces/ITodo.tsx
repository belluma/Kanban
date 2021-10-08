export interface ITodo {
    id: number,
    title: string,
    description: string,
    status: ITodoStatus,
    created?: Date,
    deadline?: Date,
}

export interface ITodoList {
    todo: ITodo[],
    doing: ITodo[],
    done: ITodo[],
}

export enum ITodoStatus {
    TODO = "TODO",
    DOING = "DOING",
    DONE = "DONE"
}

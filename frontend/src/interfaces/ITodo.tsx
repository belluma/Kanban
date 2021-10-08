export interface ITodo {
    id: number,
    title: string,
    description: string,
    status: ITodoStatus,
    created?: Date,
    deadline?: Date,
}

export interface ITodoList {
    TODO: ITodo[],
    DOING: ITodo[],
    DONE: ITodo[],
}

export enum ITodoStatus {
    TODO = "TODO",
    DOING = "DOING",
    DONE = "DONE"
}

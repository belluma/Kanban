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
export interface ITodoChecked {
    TODO: number[],
    DOING: number[],
    DONE: number[],
}
export enum ITodoStatus {
    TODO = "TODO",
    DOING = "DOING",
    DONE = "DONE"
}
interface IError {
    status: number,
    message: string,
    error: boolean,
}
export interface ITodoListState extends ITodoList, IError {}

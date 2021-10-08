export interface ITodo {
    id:number,
    title:string,
    description:string,
    created?:Date,
    deadline?:Date,
}

export interface ITodoList {
    todo: ITodo[],
    doing: ITodo[],
    done: ITodo[],

}

import axios from "axios";
import {ITodo} from "../interfaces/ITodo";

export const getAllTodos = () => {
    return axios.get(`/api/todo`)
        .then(response => response.data)
        .catch(err => (err))
};

export const getTodoById = (id: number) => {
    return axios({
        method: 'get',
        url: `api/todo/${id}`,

    }).then(response => response.data)
        .catch(err => console.log(err))
};

export const searchTodos = (query: string) => {
    return axios({
        method: 'get',
        url: `api/todo/query/${query}`,

    }).then(response => response.data)
        .catch(err => console.log(err))
};

export const createTodo = (todo: ITodo) => {
    return axios({
        method: 'post',
        url: `api/todo`,
        data: todo,

    }).then(response => response.data)
        .catch(err => console.log(err))
};

export const updateTodo = (todo: ITodo, advance: boolean) => {
    const pathv: number = parseInt(String(advance))

    // return axios({
    //     method: 'put',
    //     url: `api/todo/${pathv}`,
    //     data: todo,
    //
    // })
    return axios.post(`/api/todo/${pathv}`)
        .then(response => response)
        .catch(err => console.log(err))
};

export const updateTodoContent = (todo: ITodo) => {
    return axios({
        method: 'put',
        url: `api/todo`,
        data: todo,

    }).then(response => response.data)
        .catch(err => console.log(err))
};


export const deleteTodo = (id: number) => {
    return axios({
        method: 'delete',
        url: `api/todo${id}`,

    }).then(response => response.data)
        .catch(err => console.log(err))
};

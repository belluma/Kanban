import {createAsyncThunk, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {getAllTodos} from '../../services/apiService'
import {ITodo, ITodoList, ITodoStatus} from "../../interfaces/ITodo";
import {RootState} from "../../app/store";


const initialState:ITodoList = {
    TODO:[],
    DOING:[],
    DONE:[],
}

export const getApiData = createAsyncThunk(
    'todoList/fetchTodos'
    ,async() =>{
        const response = await (getAllTodos());
        return response;
    }
)

export const todoListSlice = createSlice({
    name:'todoList',
    initialState,
    reducers:{},
    extraReducers: (builder => {
        builder
            .addCase(getApiData.pending, state => {})
            .addCase(getApiData.fulfilled, (state, action:PayloadAction<ITodo[]>) => {
                state.TODO = [...action.payload.filter(todo => todo.status === ITodoStatus.TODO)]
                state.DOING = [...action.payload.filter(todo => todo.status === ITodoStatus.DOING)]
                state.DONE = [...action.payload.filter(todo => todo.status === ITodoStatus.DONE)]
            })
    })
})

const selectGetAllTodos = (state:RootState) =>  state.todoList.TODO;
const selectGetAllDoing = (state:RootState) =>  state.todoList.DOING;
const selectGetAllDone = (state:RootState) =>  state.todoList.DONE;
export const todosByStatus = {
    TODO: selectGetAllTodos,
    DOING: selectGetAllDoing,
    DONE: selectGetAllDone,
}
export default todoListSlice.reducer;

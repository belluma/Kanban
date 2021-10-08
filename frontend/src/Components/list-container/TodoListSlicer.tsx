import {createAsyncThunk, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {getAllTodos} from '../../services/apiService'
import {ITodo, ITodoList, ITodoStatus} from "../../interfaces/ITodo";
import {RootState} from "../../app/store";


const initialState:ITodoList = {
    todo:[],
    doing:[],
    done:[],
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
    reducers:{
getAllTodos:(a) =>{return a}
    },
    extraReducers: (builder => {
        builder
            .addCase(getApiData.pending, state => {})
            .addCase(getApiData.fulfilled, (state, action:PayloadAction<ITodo[]>) => {
                state.todo = [...action.payload.filter(todo => todo.status === ITodoStatus.TODO)]
                state.doing = [...action.payload.filter(todo => todo.status === ITodoStatus.DOING)]
                state.done = [...action.payload.filter(todo => todo.status === ITodoStatus.DONE)]
            })
    })

})

// export const {getAllTodos} = todoListSlice.actions;
export const selectGetAllTodos = (state:RootState) =>  state.todoList.todo;
export const selectGetAllDoing = (state:RootState) =>  state.todoList.doing;
export const selectGetAllDone = (state:RootState) =>  state.todoList.done;
export default todoListSlice.reducer;

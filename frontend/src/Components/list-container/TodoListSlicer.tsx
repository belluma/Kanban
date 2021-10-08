import {createSlice, createAsyncThunk, PayloadAction} from "@reduxjs/toolkit";
import {getAllTodos} from '../../services/apiService'
import {ITodoList} from "../../interfaces/ITodo";
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
            .addCase(getApiData.fulfilled, (state, action) => {
                console.log(123)
                            // state.todo = [...action.payload.filter(todo => todo.status === 'todo')]
                            // state.doing = [...action.payload.filter(todo => todo.status === 'doing')]
                            // state.done = [...action.payload.filter(todo => todo.status === 'done')]
            })
    })

})

// export const {getAllTodos} = todoListSlice.actions;
// export const selectGetAllTodos = (state:RootState) =>  state.todoList.todos;
// export const selectGetAllDoing = (state:RootState) =>  state.todoList.doing;
// export const selectGetAllDone = (state:RootState) =>  state.todoList.done;
export default todoListSlice.reducer;

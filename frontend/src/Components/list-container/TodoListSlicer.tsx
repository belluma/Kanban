import {createAsyncThunk, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {getAllTodos} from '../../services/apiService'
import {ITodo, ITodoList, ITodoListState, ITodoStatus} from "../../interfaces/ITodo";
import {RootState} from "../../app/store";
import {AxiosResponse} from "axios";


const initialState: ITodoListState = {
    TODO: [],
    DOING: [],
    DONE: [],
    status: 200,
    message: "",
    error: false,
    
}

export const getApiData = createAsyncThunk(
    'todoList/fetchTodos'
    , async () => {
        const {data, status, statusText} = await (getAllTodos());
        return {data, status, statusText}
    }
)
interface IResponseData {
    data:ITodo[],
    status:number,
    statusText:string
}
const handleErrors = (state: ITodoListState, action: PayloadAction<IResponseData>): boolean => {
    if(action.payload.status !== 200){
        state.status = action.payload.status;
        state.message = action.payload.statusText;
        state.error = true;
        if(action.payload.status === 204) state.TODO = state.DOING = state.DONE = []
        return true
    }
    return false
}


export const todoListSlice = createSlice({
    name: 'todoList',
    initialState,
    reducers: {
        closeError: (state) =>{
            state.error = false;
            state.status = 200;
            state.message = "";
    }
    },
    extraReducers: (builder => {
        builder
            .addCase(getApiData.pending, state => {
            })
            .addCase(getApiData.fulfilled, (state, action: PayloadAction<IResponseData>) => {
                if (handleErrors(state, action)) return
                const allTodos: ITodo[] = action.payload.data;
                state.TODO = [...allTodos.filter(todo => todo.status === ITodoStatus.TODO)];
                state.DOING = [...allTodos.filter(todo => todo.status === ITodoStatus.DOING)];
                state.DONE = [...allTodos.filter(todo => todo.status === ITodoStatus.DONE)];
                state.status = 200;
                state.message = "";
                state.error = false;
            })
    })
})

const selectGetAllTodos = (state: RootState) => state.todoList.TODO;
const selectGetAllDoing = (state: RootState) => state.todoList.DOING;
const selectGetAllDone = (state: RootState) => state.todoList.DONE;
export const selectErrorStatus = (state: RootState) => state.todoList.status;
export const selectErrorMessage = (state: RootState) => state.todoList.message;
export const selectError = (state: RootState) => state.todoList.error;
export const todosByStatus = {
    TODO: selectGetAllTodos,
    DOING: selectGetAllDoing,
    DONE: selectGetAllDone,
}
export const {closeError}= todoListSlice.actions;
export default todoListSlice.reducer;

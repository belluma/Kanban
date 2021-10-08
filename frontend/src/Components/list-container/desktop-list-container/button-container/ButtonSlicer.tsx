import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../../../../app/store";
import {
    ITodoChecked,
    ITodoStatus
} from "../../../../interfaces/ITodo";


const initialState: ITodoChecked = {
    TODO: [],
    DOING: [],
    DONE: [],
}

interface IPayload {
    id: number,
    status: ITodoStatus
}

export const testSlicer = () => console.log(123);


export const checkedSlice = createSlice({
    name: 'checked',
    initialState,
    reducers: {
        checkTodos: (state, action: PayloadAction<IPayload>) => {
            Object.keys(initialState).forEach(key => {
                if (action.payload.status === key) {
                    const currentIndex = state[key].indexOf(action.payload.id)
                    if (currentIndex === -1) {
                        state[key].push(action.payload.id);
                    } else {
                        state[key].splice(currentIndex, 1);
                    }
                }
            })
        }
    },

})

export const {checkTodos} = checkedSlice.actions;

export const selectCheckedTodo = (state: RootState) => state.checked.TODO;
export const selectCheckedDoing = (state: RootState) => state.checked.DOING;
export const selectCheckedDone = (state: RootState) => state.checked.DONE;

export default checkedSlice.reducer;

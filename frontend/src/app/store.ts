import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import todoListReducer from '../Components/list-container/TodoListSlicer'
import checkedReducer from '../Components/list-container/desktop-list-container/button-container/ButtonSlicer'

export const store = configureStore({
  reducer: {
    todoList: todoListReducer,
    checked: checkedReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
